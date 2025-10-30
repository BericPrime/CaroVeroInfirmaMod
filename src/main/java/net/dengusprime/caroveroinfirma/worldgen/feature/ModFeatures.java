package net.dengusprime.caroveroinfirma.worldgen.feature;

import net.dengusprime.caroveroinfirma.CaroVeroInfirma;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, CaroVeroInfirma.MODID);

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> GARBAGE_PILE =
            FEATURES.register("garbage_pile", () -> new GarbagePileFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FLOATING_GARBAGE =
            FEATURES.register("floating_garbage", () -> new FloatingGarbageFeature(NoneFeatureConfiguration.CODEC));
    public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

}