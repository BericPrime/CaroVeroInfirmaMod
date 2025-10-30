package net.dengusprime.caroveroinfirma.worldgen.structure;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;

public class SmokestacksStructure extends Structure {
    public static final Codec<SmokestacksStructure> CODEC =
            simpleCodec(SmokestacksStructure::new);

    public SmokestacksStructure(StructureSettings settings) {
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
        if(!FlatChecker.isTerrainFlat(context,x,z,surfaceY,15,5))
            return Optional.empty();

        if (surfaceY != groundY) {
            return Optional.empty();
        }

        BlockPos pos = new BlockPos(x, surfaceY, z);

        return Optional.of(new GenerationStub(pos, (builder) -> {
            generatePieces(builder, context, pos);
        }));
    }

    private void generatePieces(StructurePiecesBuilder builder, GenerationContext context, BlockPos pos) {
        StructureTemplateManager templateManager = context.structureTemplateManager();
        ResourceLocation templateLocation = new ResourceLocation("caroveroinfirma", "smokestacks");

        builder.addPiece(new SmokestacksPiece(
                templateManager,
                templateLocation,
                pos,
                context.random()
        ));
    }

    @Override
    public StructureType<?> type() {
        return ModStructureTypes.SMOKESTACKS.get();
    }
}