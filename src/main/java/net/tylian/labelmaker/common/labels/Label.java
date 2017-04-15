package net.tylian.labelmaker.common.labels;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

/**
 * Created by Tylian on 4/15/2017.
 */
public abstract class Label {
    public String text;
    public Vec3d rotation;

    Label(Vec3d rotation, String text) {
        this.text = text;
        this.rotation = rotation;
    }

    public abstract Vec3d getWorldPosition();

    @SideOnly(Side.CLIENT  )
    public boolean shouldRender() {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        Vec3d pos = this.getWorldPosition();
        return (player.getDistanceSq(pos.xCoord, pos.yCoord, pos.zCoord) < 64.0d);
    }

    @SideOnly(Side.CLIENT)
    public void render(int partialTicks) {
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

            Vec3d pos = this.getWorldPosition();

            GlStateManager.translate(pos.xCoord, pos.yCoord, pos.zCoord);
            GlStateManager.rotate((float)this.rotation.xCoord * 360.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate((float)this.rotation.yCoord * 360.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((float)this.rotation.zCoord * 360.0f, 0.0f, 0.0f, 1.0f);
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
