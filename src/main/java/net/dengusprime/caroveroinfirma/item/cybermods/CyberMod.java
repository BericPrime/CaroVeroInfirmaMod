package net.dengusprime.caroveroinfirma.item.cybermods;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CyberMod extends Item {

    private final boolean isDirty;
    private final boolean isUnsterilized;

    public CyberMod(Properties pProperties, boolean isDirty, boolean isUnsterilized) {
        super(pProperties.rarity((!isDirty || !isUnsterilized ? Rarity.RARE : Rarity.UNCOMMON)));
        this.isDirty = isDirty;
        this.isUnsterilized = isUnsterilized;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        // Don't clear tooltip - let the item name stay
        if (!isDirty)
            tooltip.add(Component.literal("dirty").withStyle(ChatFormatting.YELLOW));
        else
            tooltip.add(Component.literal("clean").withStyle(ChatFormatting.AQUA));
        if (!isUnsterilized)
            tooltip.add(Component.literal("un-sterile").withStyle(ChatFormatting.DARK_GREEN));
        else
            tooltip.add(Component.literal("sterile").withStyle(ChatFormatting.GREEN));
    }
}