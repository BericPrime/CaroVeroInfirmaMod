package net.dengusprime.caroveroinfirma.power;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CyberPowerProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {

    public static Capability<CyberPower> CYBER_POWER =
            CapabilityManager.get(new CapabilityToken<>() {});

    private CyberPower cyberPower = null;
    private final LazyOptional<CyberPower> optional = LazyOptional.of(this::createCyberPower);

    private CyberPower createCyberPower() {
        if (this.cyberPower == null) {
            this.cyberPower = new CyberPower();
        }
        return this.cyberPower;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == CYBER_POWER ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createCyberPower().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createCyberPower().loadNBTData(nbt);
    }
}