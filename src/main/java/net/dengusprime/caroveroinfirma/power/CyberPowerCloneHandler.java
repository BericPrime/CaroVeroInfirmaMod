package net.dengusprime.caroveroinfirma.power;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CyberPowerCloneHandler {

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        // make sure we run this on server only
        if (event.getEntity().level().isClientSide) return;

        // old player data
        var original = event.getOriginal();
        var newPlayer = event.getEntity();

        original.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(oldPower -> {
            newPlayer.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(newPower -> {
                // copy max only
                newPower.setMaxPower(oldPower.getMaxPower());
                // reset current to 0
                newPower.setPower(20);

                // optional: immediately sync so HUD updates after respawn
                newPower.sync((ServerPlayer)newPlayer);
            });
        });
    }
}