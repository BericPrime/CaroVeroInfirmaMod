package net.dengusprime.caroveroinfirma.worldgen.feature;

import com.mojang.serialization.Codec;
import net.dengusprime.caroveroinfirma.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.util.RandomSource;

public class FloatingGarbageFeature extends Feature<NoneFeatureConfiguration> {

    public FloatingGarbageFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();

        boolean placedAny = false;

        for (int attempt = 0; attempt < 10; attempt++) {
            int dx = random.nextInt(16);
            int dz = random.nextInt(16);
            int x = origin.getX() + dx;
            int z = origin.getZ() + dz;

            BlockPos waterPos = new BlockPos(x, 62, z);

            if (!level.getFluidState(waterPos).is(Fluids.WATER)) continue;

            int patchRadius = 20 + random.nextInt(15);
            int pileCount = 80 + random.nextInt(60);

            for (int i = 0; i < pileCount; i++) {
                int offsetX = random.nextInt(patchRadius * 2 + 1) - patchRadius;
                int offsetZ = random.nextInt(patchRadius * 2 + 1) - patchRadius;

                double distance = Math.sqrt(offsetX * offsetX + offsetZ * offsetZ);
                double normalizedDistance = distance / patchRadius;

                if (normalizedDistance > 1.0) continue;

                if (random.nextFloat() > 1.0 - normalizedDistance * 0.4) continue;

                int px = waterPos.getX() + offsetX;
                int pz = waterPos.getZ() + offsetZ;
                BlockPos pileWater = new BlockPos(px, 62, pz);

                if (!level.getFluidState(pileWater).is(Fluids.WATER)) continue;

                BlockPos pilePos = pileWater;

                int pileRadius = 1 + random.nextInt(2);
                int pileHeight = 1 + random.nextInt(2);
                BlockState garbage = ModBlocks.GARBAGE_BLOCK.get().defaultBlockState();

                for (int xOffset = -pileRadius; xOffset <= pileRadius; xOffset++) {
                    for (int zOffset = -pileRadius; zOffset <= pileRadius; zOffset++) {
                        for (int yOffset = 0; yOffset < pileHeight; yOffset++) {
                            if (random.nextFloat() < 0.9f) {
                                BlockPos gPos = pilePos.offset(xOffset, yOffset, zOffset);
                                if (level.isEmptyBlock(gPos) || level.getFluidState(gPos).is(Fluids.WATER)) {
                                    level.setBlock(gPos, garbage, 2);
                                    placedAny = true;

                                    if (random.nextFloat() < 0.3f) {
                                        placeGarbageNodes(level, random, gPos);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (placedAny) break;
        }

        return placedAny;
    }

    private void placeGarbageNodes(WorldGenLevel level, RandomSource random, BlockPos garbagePos) {
        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.UP, Direction.DOWN};

        for (Direction dir : directions) {
            if (random.nextFloat() < 0.4f) {
                BlockPos nodePos = garbagePos.relative(dir);

                boolean isWater = level.getFluidState(nodePos).is(Fluids.WATER);
                if (level.isEmptyBlock(nodePos) || isWater) {
                    BlockState nodeState = ModBlocks.GARBAGE_NODE.get().defaultBlockState()
                            .setValue(net.minecraft.world.level.block.AmethystClusterBlock.FACING, dir)
                            .setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED, isWater);
                    level.setBlock(nodePos, nodeState, 2);
                }
            }
        }
    }
}