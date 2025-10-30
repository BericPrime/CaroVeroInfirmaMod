package net.dengusprime.caroveroinfirma.worldgen.biome;

import net.dengusprime.caroveroinfirma.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> DEAD_TREE_PLACED =
            registerKey("dead_tree_placed");

    public static final ResourceKey<PlacedFeature> GARBAGE_PILE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE,
                    new ResourceLocation("caroveroinfirma", "garbage_pile_placed"));

    public static final ResourceKey<PlacedFeature> FLOATING_GARBAGE_PLACED =
            ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation("caroveroinfirma", "floating_garbage_placed"));


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var configured = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, DEAD_TREE_PLACED,
                configured.getOrThrow(ModConfiguredFeatures.DEAD_TREE),
                List.of(
                        PlacementUtils.countExtra(0, 0.2f, 1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        // only place when block below is bleached dirt or grass
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.matchesBlocks(
                                        new BlockPos(0, -1, 0),
                                        ModBlocks.BLEACHED_DIRT.get(),
                                        ModBlocks.DEAD_GRASS_BLOCK.get()
                                )
                        ),
                        BiomeFilter.biome()
                ));

        register(context, GARBAGE_PILE_PLACED,
                configured.getOrThrow(ModConfiguredFeatures.GARBAGE_PILE),
                List.of(
                        RarityFilter.onAverageOnceEvery(3),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_TOP_SOLID,
                        BiomeFilter.biome()
                ));

        register(context, FLOATING_GARBAGE_PLACED,
                configured.getOrThrow(ModConfiguredFeatures.FLOATING_GARBAGE),
                List.of(
                        RarityFilter.onAverageOnceEvery(20),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()
                ));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                new ResourceLocation("caroveroinfirma", name));
    }

    private static void register(BootstapContext<PlacedFeature> context,
                                 ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}