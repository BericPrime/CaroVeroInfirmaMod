package net.dengusprime.caroveroinfirma.block.entity;

import net.dengusprime.caroveroinfirma.CaroVeroInfirma;
import net.dengusprime.caroveroinfirma.block.ModBlocks;
import net.dengusprime.caroveroinfirma.power.CyberPowerUtil;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CaroVeroInfirma.MODID);
    public static final RegistryObject<BlockEntityType<net.dengusprime.caroveroinfirma.block.entity.AutodocXIBlockEntity>> AUTODOC_XI =
            BLOCK_ENTITIES.register("autodoc_xi",
                    () -> BlockEntityType.Builder.of(AutodocXIBlockEntity::new, ModBlocks.AUTODOC_XI.get())
                            .build(null));
}