package net.dengusprime.caroveroinfirma.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class CyberPowerNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("caroveroinfirma", "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;
    private static int nextId() { return packetId++; }

    public static void register() {
        INSTANCE.registerMessage(nextId(),
                net.dengusprime.caroveroinfirma.network.CyberPowerSyncS2CPacket.class,
                net.dengusprime.caroveroinfirma.network.CyberPowerSyncS2CPacket::encode,
                net.dengusprime.caroveroinfirma.network.CyberPowerSyncS2CPacket::decode,
                net.dengusprime.caroveroinfirma.network.CyberPowerSyncS2CPacket::handle);
        // future packets register here
    }
}