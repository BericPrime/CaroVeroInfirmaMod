package net.dengusprime.caroveroinfirma.worldgen.structure;

import net.dengusprime.caroveroinfirma.CaroVeroInfirma;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructurePieceTypes {
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_PIECE, CaroVeroInfirma.MODID);

//    public static final RegistryObject<StructurePieceType> BUNKER_PIECE =
//            STRUCTURE_PIECE_TYPES.register("bunker_piece",
//                    () -> BunkerPiece::new);
//
//    public static final RegistryObject<StructurePieceType> BUNKER_RUIN_PIECE =
//            STRUCTURE_PIECE_TYPES.register("bunker_ruin_piece",
//                    () -> BunkerRuinPiece::new);
    //notch code ahh

    public static final RegistryObject<StructurePieceType> RUINED_TANK_PIECE =
            STRUCTURE_PIECE_TYPES.register("ruined_tank_piece",
                    () -> RuinedTankPiece::new);

    public static final RegistryObject<StructurePieceType> SMALL_RUIN_PIECE =
            STRUCTURE_PIECE_TYPES.register("small_ruin_piece",
                    () -> SmallRuinPiece::new);

    public static final RegistryObject<StructurePieceType> SMOKESTACKS_PIECE =
            STRUCTURE_PIECE_TYPES.register("smokestacks_piece",
                    () -> SmokestacksPiece::new);
}