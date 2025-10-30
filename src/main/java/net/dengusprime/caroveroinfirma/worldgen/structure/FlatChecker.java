package net.dengusprime.caroveroinfirma.worldgen.structure;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
public class FlatChecker {
    public static boolean isTerrainFlat(Structure.GenerationContext context, int centerX, int centerZ,
                                 int centerY, int checkRadius, int maxHeightDifference) {
        // Check points in a grid around the center
        for (int xOffset = -checkRadius; xOffset <= checkRadius; xOffset += 4) {
            for (int zOffset = -checkRadius; zOffset <= checkRadius; zOffset += 4) {
                int checkY = context.chunkGenerator().getFirstOccupiedHeight(
                        centerX + xOffset,
                        centerZ + zOffset,
                        Heightmap.Types.OCEAN_FLOOR_WG,
                        context.heightAccessor(),
                        context.randomState()
                );

                // If any point is too different in height, terrain is not flat
                if (Math.abs(checkY - centerY) > maxHeightDifference) {
                    return false;
                }
            }
        }
        return true;
    }
}
