package net.dengusprime.caroveroinfirma.menu;

import net.dengusprime.caroveroinfirma.CaroVeroInfirma;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, CaroVeroInfirma.MODID);

    public static final RegistryObject<MenuType<AutodocXIMenu>> AUTODOC_XI =
            MENUS.register("autodoc_xi",
                    () -> IForgeMenuType.create((id, inv, data) -> new AutodocXIMenu(id, inv, data)));
}
