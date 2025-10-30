package net.dengusprime.caroveroinfirma.worldgen.biome;

import net.dengusprime.caroveroinfirma.CaroVeroInfirma;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes {
    public static final ResourceKey<Biome> INDUSTRIAL_WASTELAND = ResourceKey.create(
            Registries.BIOME,
            new ResourceLocation(CaroVeroInfirma.MODID, "industrial_wasteland")
    );

    public static void bootstrap(BootstapContext<Biome> context){
        context.register(INDUSTRIAL_WASTELAND, industrialWasteland(context));
    }

    private static Biome industrialWasteland(BootstapContext<Biome> context){
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Add features in the correct order (only ONCE!)
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);

        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeatures.getOrThrow(ModPlacedFeatures.DEAD_TREE_PLACED));
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeatures.getOrThrow(ModPlacedFeatures.GARBAGE_PILE_PLACED));
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
               ModPlacedFeatures.FLOATING_GARBAGE_PLACED);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0F)
                .temperature(1.5F)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x33cccc)
                        .waterFogColor(0x00ffff)
                        .skyColor(0xdaffb3)
                        .grassColorOverride(0xe8db6b)
                        .foliageColorOverride(0xe8db6b)
                        .fogColor(0x00ff99)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.GAME).build()
                ).build();
    }
}