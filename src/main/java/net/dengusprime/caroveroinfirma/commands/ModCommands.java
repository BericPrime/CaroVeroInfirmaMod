package net.dengusprime.caroveroinfirma.commands;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModCommands {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        net.dengusprime.caroveroinfirma.common.commands.CyberPowerCommands.register(event.getDispatcher());
    }
}