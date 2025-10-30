package net.dengusprime.caroveroinfirma.worldgen.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class ModStructurePools {
    public static final ResourceKey<StructureTemplatePool> BUNKER =
            createKey("bunker");

    public static final ResourceKey<StructureTemplatePool> BUNKER_RUIN =
            createKey("bunker_ruin");

    public static final ResourceKey<StructureTemplatePool> RUINED_TANK =
            createKey("ruined_tank");

    public static final ResourceKey<StructureTemplatePool> SMALL_RUIN =
            createKey("small_ruin");

    public static final ResourceKey<StructureTemplatePool> SMOKESTACKS =
            createKey("smokestacks");

    public static void bootstrap(BootstapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> pools = context.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> empty = pools.getOrThrow(Pools.EMPTY);

        context.register(BUNKER, new StructureTemplatePool(
                empty,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("caroveroinfirma:bunker"), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));

        context.register(BUNKER_RUIN, new StructureTemplatePool(
                empty,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("caroveroinfirma:bunker_ruin"), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));

        context.register(RUINED_TANK, new StructureTemplatePool(
                empty,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("caroveroinfirma:ruined_tank"), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));

        context.register(SMALL_RUIN, new StructureTemplatePool(
                empty,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("caroveroinfirma:small_ruin"), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));

        context.register(SMOKESTACKS, new StructureTemplatePool(
                empty,
                ImmutableList.of(
                        Pair.of(StructurePoolElement.single("caroveroinfirma:smokestacks"), 1)
                ),
                StructureTemplatePool.Projection.RIGID
        ));
    }

    private static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL,
                new ResourceLocation("caroveroinfirma", name));
    }
}