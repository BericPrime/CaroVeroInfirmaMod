package net.dengusprime.caroveroinfirma.event;

import net.dengusprime.caroveroinfirma.power.CyberPowerProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.resources.ResourceLocation;
import net.dengusprime.caroveroinfirma.CaroVeroInfirma;

@Mod.EventBusSubscriber(modid = "caroveroinfirma")
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(
                    new ResourceLocation("caroveroinfirma", "cyber_power"),
                    new CyberPowerProvider()
            );
        }
    }
}