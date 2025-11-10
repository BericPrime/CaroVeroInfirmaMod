package net.dengusprime.caroveroinfirma.block.entity;

import net.dengusprime.caroveroinfirma.menu.AutodocXIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AutodocXIBlockEntity extends BlockEntity implements MenuProvider {
    public AutodocXIBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.AUTODOC_XI.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.caroveroinfirma.autodoc_xi");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new AutodocXIMenu(id, inv, this.worldPosition);
    }
}