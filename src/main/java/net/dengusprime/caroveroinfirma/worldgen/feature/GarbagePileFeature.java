package net.dengusprime.caroveroinfirma.worldgen.feature;

import com.mojang.serialization.Codec;
import net.dengusprime.caroveroinfirma.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GarbagePileFeature extends Feature<NoneFeatureConfiguration> {

    public GarbagePileFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        BlockState garbage = ModBlocks.GARBAGE_BLOCK.get().defaultBlockState();

        boolean placed = false;

        //garbage placement

        for (int dx = -random.nextInt(3); dx <= random.nextInt(3); dx++) {
            for (int dz = -random.nextInt(3); dz <= random.nextInt(3); dz++) {
                int height = random.nextInt(6) + 1;
                for (int dy = 0; dy < height; dy++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    if (level.isEmptyBlock(pos)) {
                        BlockState below = level.getBlockState(pos.below());
                        if (below.isSolid() && !below.isAir()) {
                            level.setBlock(pos, garbage, 2);
                            placed = true;
                        }
                    }
                }
            }
        }


        for (int dx = -4; dx <= 4; dx++) {
            for (int dz = -4; dz <= 4; dz++) {
                for (int dy = -2; dy <= 8; dy++) {
                    BlockPos pos = origin.offset(dx, dy, dz);
                    BlockState state = level.getBlockState(pos);

                    if (!state.is(ModBlocks.GARBAGE_BLOCK.get()))
                        continue;

                    for (Direction dir : Direction.values()) {
                        BlockPos sidePos = pos.relative(dir);

                        if (level.isEmptyBlock(sidePos) && random.nextFloat() < 0.2f) {
                            BlockState nodeState = ModBlocks.GARBAGE_NODE.get().defaultBlockState();
                            if (nodeState.hasProperty(BlockStateProperties.FACING)) {
                                nodeState = nodeState.setValue(BlockStateProperties.FACING, dir);
                            }

                            // Check if node can actually survive (uses facing)
                            if (nodeState.canSurvive(level, sidePos)) {
                                level.setBlock(sidePos, nodeState, 2);
                            }
                        }
                    }
                }
            }
        }

        return placed;
    }
}