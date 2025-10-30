package net.dengusprime.caroveroinfirma.power;

import net.minecraft.world.entity.player.Player;

public class CyberPowerUtil {

    public static int get(Player player) {
        return player.getCapability(CyberPowerProvider.CYBER_POWER)
                .map(CyberPower::getPower).orElse(0);
    }

    public static void add(Player player, int amount) {
        player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> power.setPower(power.getPower()+amount));
    }

    public static void addMax(Player player, int amount) {
        player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> power.setMaxPower(power.getMaxPower()+amount));
    }

    public static void sexMax(Player player, int amount) {
        player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> power.setMaxPower(amount));
    }
}