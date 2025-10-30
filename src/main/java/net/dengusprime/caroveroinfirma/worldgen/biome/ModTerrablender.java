package net.dengusprime.caroveroinfirma.worldgen.biome;

import net.dengusprime.caroveroinfirma.CaroVeroInfirma;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(CaroVeroInfirma.MODID, "overworld"), 1)); //rarity, higher is more common, lower is rare
    }
}
