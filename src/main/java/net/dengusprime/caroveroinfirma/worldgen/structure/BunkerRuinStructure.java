package net.dengusprime.caroveroinfirma.worldgen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;

public class BunkerRuinStructure extends Structure {
    public static final Codec<BunkerRuinStructure> CODEC =
            simpleCodec(BunkerRuinStructure::new);

    public BunkerRuinStructure(StructureSettings settings) {
        super(settings);
    }


    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        BlockPos chunkPos = context.chunkPos().getWorldPosition();
        int x = chunkPos.getX() + 8;
        int z = chunkPos.getZ() + 8;

        int groundY = context.chunkGenerator().getFirstOccupiedHeight(
                x,
                z,
                Heightmap.Types.OCEAN_FLOOR_WG,
                context.heightAccessor(),
                context.randomState()
        );

        int surfaceY = context.chunkGenerator().getFirstOccupiedHeight(
                x,
                z,
                Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(),
                context.randomState()
        );
        if(!FlatChecker.isTerrainFlat(context,x,z,surfaceY,8,7))
            return Optional.empty();

        if (surfaceY != groundY) {
            return Optional.empty();
        }

        BlockPos pos = new BlockPos(x, groundY - 2, z);

        Optional<Holder.Reference<StructureTemplatePool>> poolHolder =
                context.registryAccess()
                        .registryOrThrow(Registries.TEMPLATE_POOL)
                        .getHolder(ModStructurePools.BUNKER_RUIN);

        if (poolHolder.isEmpty()) {
            return Optional.empty();
        }

        return JigsawPlacement.addPieces(
                context,
                poolHolder.get(),
                Optional.empty(),
                1,
                pos,
                false,
                Optional.empty(),
                80
        );
    }

    @Override
    public StructureType<?> type() {
        return ModStructureTypes.BUNKER_RUIN.get();
    }
}