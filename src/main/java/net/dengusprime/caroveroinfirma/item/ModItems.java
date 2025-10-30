package net.dengusprime.caroveroinfirma.item;

import net.dengusprime.caroveroinfirma.CaroVeroInfirma;
import net.dengusprime.caroveroinfirma.item.cybermods.CyberMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CaroVeroInfirma.MODID);

    public static final RegistryObject<Item> BIOBRASS = ITEMS.register("biobrass",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BIOBRASS_FRAGMENT = ITEMS.register("biobrass_fragment",() -> new Item(new Item.Properties()));

    //titanium
//    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TITANIUM_DUST = ITEMS.register("titanium_dust",() -> new Item(new Item.Properties()));

    //tungsten
//    public static final RegistryObject<Item> OXIDIZED_TUNGSTEN_SHARD = ITEMS.register("oxidized_tungsten_shard",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TUNGSTEN_SHARD = ITEMS.register("tungsten_shard",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> RAW_TUNGSTEN = ITEMS.register("raw_tungsten",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TUNGSTEN_DUST = ITEMS.register("tungsten_dust",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TUNGSTEN_CARBIDE_INGOT = ITEMS.register("tungsten_carbide_ingot",() -> new Item(new Item.Properties()));

    //titanium carbide
//    public static final RegistryObject<Item> TITANIUM_ALLOY_MIX = ITEMS.register("titanium_alloy_mix",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TITANIUM_ALLOY_DUST = ITEMS.register("titanium_alloy_dust",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TITANIUM_ALLOY_INGOT = ITEMS.register("titanium_alloy_ingot",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TITANIUM_ALLOY_NUGGET = ITEMS.register("titanium_alloy_nugget",() -> new Item(new Item.Properties()));

    //aluminum
//    public static final RegistryObject<Item> OXIDIZED_ALUMINIUM_SHARD = ITEMS.register("oxidized_aluminium_shard",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> ALUMINIUM_SHARD = ITEMS.register("aluminium_shard",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> RAW_ALUMINIUM = ITEMS.register("raw_aluminium",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> ALUMINIUM_DUST = ITEMS.register("aluminium_dust",() -> new Item(new Item.Properties()));

    //misc dusts
//    public static final RegistryObject<Item> LITHIUM_DUST = ITEMS.register("lithium_dust",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> TINY_SALT_DUST = ITEMS.register("tiny_salt_dust",() -> new Item(new Item.Properties()));
//    public static final RegistryObject<Item> SALT_DUST = ITEMS.register("salt_dust",() -> new Item(new Item.Properties()));

    //cybermods
    public  static  final RegistryObject<Item> TEST_STERILE_CYBERMOD = ITEMS.register("test_sterile_cybermod",() -> new CyberMod(new Item.Properties().stacksTo(8),true,true));
    public  static  final RegistryObject<Item> TEST_DIRTY_CYBERMOD = ITEMS.register("test_dirty_cybermod",() -> new CyberMod(new Item.Properties().stacksTo(8),true,true));
    //foods
    public static final RegistryObject<Item> MONSTER_JERKY = ITEMS.register("monster_jerky",() -> new Item(new Item.Properties().food(ModFoods.MONSTER_JERKY)));

    public static final RegistryObject<Item> SPIDER_FALSE_CAVIAR = ITEMS.register("spider_false_caviar",() -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(ModFoods.SPIDER_FALSE_CAVIAR)));
    public static final RegistryObject<Item> PURGED_MEMBRANE = ITEMS.register("purged_membrane",() -> new Item(new Item.Properties().food(ModFoods.PURGED_MEMBRANE)));
    public static final RegistryObject<Item> SPICED_MEMBRANE = ITEMS.register("spiced_membrane",() -> new Item(new Item.Properties().food(ModFoods.SPICED_MEMBRANE)));
    public static final RegistryObject<Item> MONSTER_BURGER = ITEMS.register("monster_burger",() -> new Item(new Item.Properties().food(ModFoods.MONSTER_BURGER)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
