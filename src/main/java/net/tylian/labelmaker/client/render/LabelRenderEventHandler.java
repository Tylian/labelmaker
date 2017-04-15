package net.tylian.labelmaker.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tylian.labelmaker.common.capability.label.LabelProvider;
import net.tylian.labelmaker.common.labels.Label;

import java.util.LinkedList;

/**
 * Created by Tylian on 4/15/2017.
 */
public class LabelRenderEventHandler {
    private float frames = 0;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SideOnly(Side.CLIENT)
    public void onRender(RenderWorldLastEvent event) {
        World world = Minecraft.getMinecraft().world;
        if(world.hasCapability(LabelProvider.LABEL_CAPABILITY, null)) {
            LinkedList<Label> labels = world.getCapability(LabelProvider.LABEL_CAPABILITY, null).getAll();
            for(Label label : labels) {
                if(label.shouldRender()) {
                    label.render(event.getPartialTicks());
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderLiving(RenderLivingEvent.Post<EntityLivingBase> event) {
        if(event.getEntity().hasCapability(LabelProvider.LABEL_CAPABILITY, null)) {
            LinkedList<Label> labels = event.getEntity().getCapability(LabelProvider.LABEL_CAPABILITY, null).getAll();
            for(Label label : labels) {
                if(label.shouldRender()) {
                    label.render(0.0f);
                }
            }
        }
    }
}
