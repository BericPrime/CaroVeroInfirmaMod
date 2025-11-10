package net.dengusprime.caroveroinfirma.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class AutodocXIMenu extends AbstractContainerMenu {
    private final BlockPos pos;

    // client-side constructor (Forge uses this)
    public AutodocXIMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv, buf.readBlockPos());
    }

    // server-side constructor
    public AutodocXIMenu(int id, Inventory inv, BlockPos pos) {
        super(ModMenus.AUTODOC_XI.get(), id);
        this.pos = pos;

        // add your slots here
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}