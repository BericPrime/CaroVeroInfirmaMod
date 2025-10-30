package net.dengusprime.caroveroinfirma.common.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.dengusprime.caroveroinfirma.power.CyberPowerProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class CyberPowerCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("cyberpower")
                        .requires(src -> src.hasPermission(2)) // ops only
                        // === SET COMMANDS ===
                        .then(Commands.literal("setpower")
                                .then(Commands.argument("value", IntegerArgumentType.integer(0))
                                        .executes(CyberPowerCommands::setPower)))
                        .then(Commands.literal("setmax")
                                .then(Commands.argument("value", IntegerArgumentType.integer(0))
                                        .executes(CyberPowerCommands::setMaxPower)))
                        // === ADD COMMANDS ===
                        .then(Commands.literal("addpower")
                                .then(Commands.argument("value", IntegerArgumentType.integer())
                                        .executes(CyberPowerCommands::addPower)))
                        .then(Commands.literal("addmax")
                                .then(Commands.argument("value", IntegerArgumentType.integer())
                                        .executes(CyberPowerCommands::addMaxPower)))
                        // === GET COMMAND ===
                        .then(Commands.literal("get")
                                .executes(CyberPowerCommands::getPower))
        );
    }

    // === SET POWER ===
    private static int setPower(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        int value = IntegerArgumentType.getInteger(ctx, "value");

        player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> {
            power.setPower(value);
            power.sync(player);
        });

        ctx.getSource().sendSuccess(() ->
                Component.literal("Set Power to " + value), true);
        return Command.SINGLE_SUCCESS;
    }

    // === SET MAX ===
    private static int setMaxPower(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        int value = IntegerArgumentType.getInteger(ctx, "value");

        player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> {
            power.setMaxPower(value);
            // optional: clamp current if it's now above max
            if (power.getPower() > power.getMaxPower()) {
                power.setPower(power.getMaxPower());
            }
            power.sync(player);
        });

        ctx.getSource().sendSuccess(() ->
                Component.literal("Set Max Power to " + value), true);
        return Command.SINGLE_SUCCESS;
    }

    // === ADD POWER ===
    private static int addPower(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        int value = IntegerArgumentType.getInteger(ctx, "value");

        player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> {
            power.setPower(power.getPower()+value);
            power.sync(player);
        });

        ctx.getSource().sendSuccess(() ->
                Component.literal("Added " + value + " to Power"), true);
        return Command.SINGLE_SUCCESS;
    }

    // === ADD MAX POWER ===
    private static int addMaxPower(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();
        int value = IntegerArgumentType.getInteger(ctx, "value");

        player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> {
            power.setMaxPower(power.getMaxPower() + value);
            power.sync(player);
        });

        ctx.getSource().sendSuccess(() ->
                Component.literal("Added " + value + " to Max Power"), true);
        return Command.SINGLE_SUCCESS;
    }

    // === GET ===
    private static int getPower(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();

        player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> {
            ctx.getSource().sendSuccess(() ->
                    Component.literal("Power: " +
                            power.getPower() + " / " + power.getMaxPower()), false);
        });

        return Command.SINGLE_SUCCESS;
    }
}