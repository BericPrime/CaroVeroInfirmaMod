package net.dengusprime.caroveroinfirma.worldgen.biome;

import net.dengusprime.caroveroinfirma.worldgen.feature.GarbagePileFeature;
import net.dengusprime.caroveroinfirma.worldgen.feature.ModFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.dengusprime.caroveroinfirma.block.ModBlocks;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_TREE =
            registerKey("dead_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GARBAGE_PILE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE,
                    new ResourceLocation("caroveroinfirma", "garbage_pile"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOATING_GARBAGE =
            ResourceKey.create(Registries.CONFIGURED_FEATURE,
                    new ResourceLocation("caroveroinfirma", "floating_garbage"));


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context, DEAD_TREE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.BLEACHED_LOG.get()),
                        new FancyTrunkPlacer(4, 2, 5),
                        BlockStateProvider.simple(Blocks.AIR),
                        new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 1)
                ).ignoreVines().dirt(BlockStateProvider.simple(ModBlocks.BLEACHED_DIRT.get())).build());

        context.register(GARBAGE_PILE,
                new ConfiguredFeature<>(ModFeatures.GARBAGE_PILE.get(), NoneFeatureConfiguration.INSTANCE));

        context.register(FLOATING_GARBAGE,
                new ConfiguredFeature<>(ModFeatures.FLOATING_GARBAGE.get(), NoneFeatureConfiguration.INSTANCE));
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                new ResourceLocation("caroveroinfirma", name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}