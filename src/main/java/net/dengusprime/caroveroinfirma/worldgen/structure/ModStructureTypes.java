package net.dengusprime.caroveroinfirma.worldgen.structure;

import net.dengusprime.caroveroinfirma.CaroVeroInfirma;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, CaroVeroInfirma.MODID);

    public static final RegistryObject<StructureType<BunkerStructure>> BUNKER =
            STRUCTURE_TYPES.register("bunker",
                    () -> () -> BunkerStructure.CODEC);
    public static final RegistryObject<StructureType<BunkerRuinStructure>> BUNKER_RUIN =
            STRUCTURE_TYPES.register("bunker_ruin",
                    () -> () -> BunkerRuinStructure.CODEC);
    public static final RegistryObject<StructureType<RuinedTankStructure>> RUINED_TANK =
            STRUCTURE_TYPES.register("ruined_tank",
                    () -> () -> RuinedTankStructure.CODEC);
    public static final RegistryObject<StructureType<SmallRuinStructure>> SMALL_RUIN =
            STRUCTURE_TYPES.register("small_ruin",
                    () -> () -> SmallRuinStructure.CODEC);
    public static final RegistryObject<StructureType<SmokestacksStructure>> SMOKESTACKS =
            STRUCTURE_TYPES.register("smokestacks",
                    () -> () -> SmokestacksStructure.CODEC);
}