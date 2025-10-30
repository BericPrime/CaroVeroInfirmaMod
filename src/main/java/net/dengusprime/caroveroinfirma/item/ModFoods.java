package net.dengusprime.caroveroinfirma.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModFoods {

    //monster foods
    public static final FoodProperties MONSTER_JERKY = new FoodProperties.Builder().nutrition(2).saturationMod(0.6f).meat().build();
    public static final FoodProperties SPIDER_FALSE_CAVIAR = new FoodProperties.Builder().nutrition(4).saturationMod(1.2f).meat().effect(() -> new MobEffectInstance(MobEffects.POISON,100,0),0.01f).build();
    public static final FoodProperties PURGED_MEMBRANE = new FoodProperties.Builder().nutrition(2).saturationMod(0.6f).meat().effect(() -> new MobEffectInstance(MobEffects.CONFUSION,100,0),0.6f).build();
    public static final FoodProperties SPICED_MEMBRANE = new FoodProperties.Builder().nutrition(4).saturationMod(1.8f).meat().effect(() -> new MobEffectInstance(MobEffects.CONFUSION,100,0),0.1f).build();
    public static final FoodProperties MONSTER_BURGER = new FoodProperties.Builder().nutrition(8).saturationMod(3.0f).meat().build();

}
