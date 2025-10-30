package net.dengusprime.caroveroinfirma.power;

import net.dengusprime.caroveroinfirma.network.CyberPowerNetwork;
import net.dengusprime.caroveroinfirma.network.CyberPowerSyncS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class CyberPower {
    private int power = 20;
    private int maxPower = 1000; // configurable later if needed

    // Core API


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power >= maxPower)
            this.power = maxPower;
        else if (power <= 0)
            this.power = 0;
        else
            this.power = power;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    // Save/load for persistence
    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("cyberPower", power);
        nbt.putInt("maxCyberPower", maxPower);
    }

    public void loadNBTData(CompoundTag nbt) {
        this.power = nbt.getInt("cyberPower");
        this.maxPower = nbt.getInt("maxCyberPower");
    }

    public void sync(ServerPlayer player) {
        CyberPowerNetwork.INSTANCE.send(
                PacketDistributor.PLAYER.with(() -> player),
                new CyberPowerSyncS2CPacket(this.power, this.maxPower)
        );
    }
}