package net.dengusprime.caroveroinfirma.worldgen.biome.surface;

import net.dengusprime.caroveroinfirma.block.ModBlocks;
import net.dengusprime.caroveroinfirma.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource BLEACHED_DIRT = makeStateRule(ModBlocks.BLEACHED_DIRT.get());
    private static final SurfaceRules.RuleSource BLEACHED_COARSE_DIRT = makeStateRule(ModBlocks.BLEACHED_COARSE_DIRT.get());
    private static final SurfaceRules.RuleSource DEAD_GRASS_BLOCK = makeStateRule(ModBlocks.DEAD_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource GARBAGE_BLOCK = makeStateRule(ModBlocks.GARBAGE_BLOCK.get());
    private static final SurfaceRules.RuleSource GARBAGE_NODE = makeStateRule(ModBlocks.GARBAGE_NODE.get());

    private static final SurfaceRules.ConditionSource PATCH_NOISE = SurfaceRules.noiseCondition(Noises.PATCH, -0.8, 1);

    private static final SurfaceRules.RuleSource INDUSTRIAL_SURFACE = SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -0.2, 0), DEAD_GRASS_BLOCK),
            SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PATCH, -1.0, -0.95), BLEACHED_COARSE_DIRT),
            SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PATCH, 0.2, 0.35), GRAVEL),
            SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PATCH, 0.35, 0.45), SAND),
            // Fallback (most common)
            BLEACHED_DIRT
    );

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.INDUSTRIAL_WASTELAND),
                        SurfaceRules.sequence(
                                // Surface (on top)
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, INDUSTRIAL_SURFACE),

                                // Under the surface
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(PATCH_NOISE, BLEACHED_COARSE_DIRT),
                                                BLEACHED_DIRT
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, DEAD_GRASS_BLOCK),
                                BLEACHED_DIRT
                        )
                )
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}