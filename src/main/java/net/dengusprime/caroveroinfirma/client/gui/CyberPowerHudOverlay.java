package net.dengusprime.caroveroinfirma.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.dengusprime.caroveroinfirma.power.CyberPowerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = net.minecraftforge.api.distmarker.Dist.CLIENT)
public class CyberPowerHudOverlay {
    private static final ResourceLocation BAR_TEXTURE =
            new ResourceLocation("caroveroinfirma", "textures/gui/battery_gui.png");

    @SubscribeEvent
    public static void onRenderGui(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        var pose = event.getGuiGraphics();
        mc.player.getCapability(CyberPowerProvider.CYBER_POWER).ifPresent(power -> {
            int current = power.getPower();
            int max = power.getMaxPower();

            if (max <= 0) return;
            float ratio = (float) current / max;
            drawCyberPowerBar(pose, ratio);
        });
    }

    private static void drawCyberPowerBar(GuiGraphics gui, float ratio) {
        int texWidth = 25; // 25 left + 1 spacing + 25 right = 51 total width
        int texHeight = 64;

        float scale = 1.0f;
        int drawWidth = (int)(25 * scale);
        int drawHeight = (int)(64 * scale);

        int screenWidth = gui.guiWidth();
        int screenHeight = gui.guiHeight();
        int x = (int)(screenWidth - drawWidth - 6);
        int y = (int)(screenHeight - drawHeight - 6);

        int fillTop = 15;
        int fillBottom = 2;
        int fillHeight = texHeight - fillTop - fillBottom; // 47px fillable

        var pose = gui.pose();
        pose.pushPose();
        pose.translate(x, y, 0);
        pose.scale(scale, scale, 1f);

        RenderSystem.setShaderTexture(0, BAR_TEXTURE);

        // draw empty (left half)
        gui.blit(BAR_TEXTURE, 0, 0, 0, 0, 12, texHeight, texWidth, texHeight);

        // compute filled height (only the active region)
        int filledPixels = (int)(fillHeight * ratio);

        // vStart = where to start copying from (bottom of active region)
        int vStart = texHeight - fillBottom - filledPixels;

        // yOffset = where to draw on screen
        int yOffset = vStart;

        // draw filled (right half, with 1px spacing)
        gui.blit(BAR_TEXTURE,
                0, yOffset,           // draw position
                13, vStart,           // texture UV (skip 25px + 1px spacing)
                12, filledPixels,     // width, height of filled section
                texWidth, texHeight);

        pose.popPose();
    }
}