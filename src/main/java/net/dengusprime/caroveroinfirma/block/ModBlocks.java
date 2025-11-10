package net.dengusprime.caroveroinfirma.block;

import net.dengusprime.caroveroinfirma.CaroVeroInfirma;
import net.dengusprime.caroveroinfirma.item.ModItems;
import net.dengusprime.caroveroinfirma.block.autodoc.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CaroVeroInfirma.MODID);

    public static final RegistryObject<Block> BIOBRASS_BLOCK = registerBlock("biobrass_block",()-> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).instrument(NoteBlockInstrument.PLING).requiresCorrectToolForDrops().strength(5.0F,12.0F).sound(SoundType.CALCITE)));
    public static final RegistryObject<Block> GARBAGE_BLOCK = registerBlock("garbage_block",()-> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BIT).requiresCorrectToolForDrops().strength(2.0F,1.0F).sound(SoundType.GRAVEL)));
    public static final RegistryObject<Block> GARBAGE_NODE = registerBlock("garbage_node",()-> new NodeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).instrument(NoteBlockInstrument.BIT).requiresCorrectToolForDrops().strength(2.0F,1.0F).sound(SoundType.GRAVEL).noOcclusion()));

    public static final RegistryObject<Block> BLEACHED_LOG = registerBlock("bleached_log",()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> BLEACHED_WOOD = registerBlock("bleached_wood",()-> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> BLEACHED_DIRT = registerBlock("bleached_dirt",()-> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> BLEACHED_COARSE_DIRT = registerBlock("bleached_coarse_dirt",()-> new Block(BlockBehaviour.Properties.copy(Blocks.COARSE_DIRT)));
    public static final RegistryObject<Block> DEAD_GRASS_BLOCK = registerBlock("dead_grass_block",()-> new DeadGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));

    public static final RegistryObject<Block> AUTODOC_XI = BLOCKS.register("autodoc_xi", () -> new AutodocXIBlock(BlockBehaviour.Properties.of().strength(2.5F).noOcclusion()));
    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <Type extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<Type> block){
        return ModItems.ITEMS.register(name,() -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
