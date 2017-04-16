package net.tylian.labelmaker.common.labels;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tylian.labelmaker.common.Utils;
import org.lwjgl.opengl.GL11;

/**
 * Created by Tylian on 4/15/2017.
 */
public class Label {
    private Vec3d offset;
    private Vec3d rotation;
    private String text;
    private float scale = 1.0f;

    public Label(Vec3d offset, Vec3d rotation, String text) {
        this.offset = offset;
        this.text = text;
        this.rotation = rotation;
    }

    public Label(NBTTagCompound nbt) {
        this.deserializeNBT(nbt);
    }


    public Vec3d getParentPosition(World worldIn) { return Vec3d.ZERO; }
    public Vec3d getParentRotation(World worldIn) { return Vec3d.ZERO; }

    protected String getType() {
        return "label";
    }

    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("Type", this.getType());
        nbt.setTag("Offset", Utils.nbtWriteVec3d(this.offset));
        nbt.setTag("Rotation", Utils.nbtWriteVec3d(this.rotation));
        nbt.setString("Text", this.text);
        nbt.setFloat("Scale", this.scale);
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        this.offset = Utils.nbtReadVec3d(nbt.getCompoundTag("Offset"));
        this.rotation = Utils.nbtReadVec3d(nbt.getCompoundTag("Rotation"));
        this.text = nbt.getString("Text");
        this.scale = nbt.getFloat("Scale");
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRender(World worldIn) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        Vec3d pos = this.getParentPosition(worldIn).add(offset);
        return (player.getDistanceSq(pos.xCoord, pos.yCoord, pos.zCoord) < 4096.0d);
    }

    @SideOnly(Side.CLIENT)
    public void render(World worldIn, float partialTicks) {
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();

            Vec3d rot = this.getParentRotation(worldIn).add(rotation);

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

            Vec3d pos = this.getParentPosition(worldIn).add(offset);

            GlStateManager.translate(pos.xCoord, pos.yCoord, pos.zCoord);
            GlStateManager.rotate((float)rot.xCoord * 360.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate((float)rot.yCoord * 360.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate((float)rot.zCoord * 360.0f, 0.0f, 0.0f, 1.0f);
            GlStateManager.glNormal3f(0.0F, 1.0F, 0.0F);
            GlStateManager.scale(-0.0625f * this.scale, -0.0625f * this.scale, 0.0625f * this.scale);

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
