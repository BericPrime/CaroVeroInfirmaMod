package net.dengusprime.caroveroinfirma.worldgen.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class RuinedTankPiece extends TemplateStructurePiece {

    public RuinedTankPiece(StructureTemplateManager templateManager,
                           ResourceLocation templateLocation,
                           BlockPos pos,
                           RandomSource random) {
        super(ModStructurePieceTypes.RUINED_TANK_PIECE.get(), 0, templateManager,
                templateLocation, templateLocation.toString(),
                makeSettings(random), pos);
    }

    public RuinedTankPiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(ModStructurePieceTypes.RUINED_TANK_PIECE.get(), tag, context.structureTemplateManager(),
                (resourceLocation) -> makeSettings(RandomSource.create()));
    }

    private static StructurePlaceSettings makeSettings(RandomSource random) {
        return new StructurePlaceSettings()
                .setRotation(Rotation.getRandom(random))
                .setMirror(Mirror.NONE)
                .setRotationPivot(BlockPos.ZERO);
    }

    @Override
    protected void handleDataMarker(String name, BlockPos pos, ServerLevelAccessor level,
                                    RandomSource random, BoundingBox box) {
        // Handle any data markers in your structure template
        // For example, if you have chests or spawners marked with data blocks
    }
}