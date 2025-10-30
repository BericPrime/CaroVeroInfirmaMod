package net.dengusprime.caroveroinfirma.network;

import net.dengusprime.caroveroinfirma.power.CyberPowerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CyberPowerSyncS2CPacket {
    private final int cyberPower;
    private final int maxCyberPower;

    public CyberPowerSyncS2CPacket(int cyberPower, int maxCyberPower) {
        this.cyberPower = cyberPower;
        this.maxCyberPower = maxCyberPower;
    }

    // Serialize the packet data to the buffer (server -> client)
    public static void encode(CyberPowerSyncS2CPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.cyberPower);
        buf.writeInt(msg.maxCyberPower);
    }

    // Deserialize the packet data from the buffer (client)
    public static CyberPowerSyncS2CPacket decode(FriendlyByteBuf buf) {
        int cyberPower = buf.readInt();
        int maxCyberPower = buf.readInt();
        return new CyberPowerSyncS2CPacket(cyberPower, maxCyberPower);
    }

    // Handle the received packet on the client side
    public static void handle(CyberPowerSyncS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var player = Minecraft.getInstance().player;
            if (player != null) {
                player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> {
                    power.setPower(msg.cyberPower);
                    power.setMaxPower(msg.maxCyberPower);
                });
            }
        });
        ctx.get().setPacketHandled(true);
    }
}