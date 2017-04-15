package net.tylian.labelmaker.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Created by Tylian on 4/15/2017.
 */
public class LabelRenderEventHandler {
    private float frames = 0;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SideOnly(Side.CLIENT)
    public void onRender(RenderWorldLastEvent event) {
        frames++;

        String s = "I have NO idea what I'm doing lmao";
        renderLabel(new Vec3d(0.0d, 9.0d, 0.0d), new Vec3d(0.0d, (frames / 300.0d), 0.0d), s, event.getPartialTicks());
    }

    public static void renderLabel(Vec3d pos, Vec3d rotation, String text, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();

        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks;
        double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks;
        double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks;
        GlStateManager.translate(-x, -y, -z);

        int textWidth = fontRenderer.getStringWidth(text);

        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();

        GlStateManager.depthMask(false);

        GlStateManager.translate(pos.xCoord, pos.yCoord, pos.zCoord);
        GlStateManager.rotate((float)rotation.xCoord * 360.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate((float)rotation.yCoord * 360.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate((float)rotation.zCoord * 360.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-0.025F, -0.025F, 0.025F);

        // Center everything
        GlStateManager.translate(-textWidth / 2 - 1, -5.5f, -0.001f);

        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.disableTexture2D();


        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(0.0f, 11.0f, 0.0f).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
        buffer.pos(0.0f, 0.0f, 0.0f).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
        buffer.pos(textWidth + 4.0f, 0.0f, 0.0f).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
        buffer.pos(textWidth + 4.0f, 11.0f, 0.0f).color(0.0f, 0.0f, 0.0f, 1.0f).endVertex();
        tessellator.draw();

        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        buffer.pos(1.0f, 10.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        buffer.pos(1.0f, 1.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        buffer.pos(textWidth + 3.0f, 1.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        buffer.pos(textWidth + 3.0f, 10.0f, 0.0f).color(1.0f, 1.0f, 1.0f, 1.0f).endVertex();
        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);

        fontRenderer.drawString(text, 2, 2, 0x000000);

        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }
}
