package net.dengusprime.caroveroinfirma.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // This builder defines the CLIMATE RANGE where your biome will appear.
        ParameterUtils.ParameterPointListBuilder builder = new ParameterUtils.ParameterPointListBuilder();

        // Warm and dry climate
        builder.temperature(ParameterUtils.Temperature.WARM);
        builder.humidity(ParameterUtils.Humidity.ARID);

        // Inland and flat terrain
        builder.continentalness(ParameterUtils.Continentalness.MID_INLAND);
        builder.erosion(ParameterUtils.Erosion.EROSION_3); // smooth terrain
        builder.depth(ParameterUtils.Depth.SURFACE);

        for (var point : builder.build()) {
            this.addBiome(mapper, point, ModBiomes.INDUSTRIAL_WASTELAND);
        }
    }
}