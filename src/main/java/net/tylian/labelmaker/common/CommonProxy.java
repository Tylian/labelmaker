package net.tylian.labelmaker.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.tylian.labelmaker.common.capability.ModCapabilities;
import net.tylian.labelmaker.common.capability.label.LabelProvider;
import net.tylian.labelmaker.common.capability.label.impl.EntityLabelCapability;
import net.tylian.labelmaker.common.capability.label.impl.WorldLabelCapability;

/**
 * Created by Tylian on 4/15/2017.
 */
public class CommonProxy {


    public void preInit(FMLPreInitializationEvent event) {
        ModCapabilities.Init();
    }

    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public void onAttachCapabilityWorld(AttachCapabilitiesEvent<World> event) {
        if(!event.getObject().hasCapability(LabelProvider.LABEL_CAPABILITY, null)) {
            event.addCapability(new ResourceLocation("labelmaker:labelCapability"), new LabelProvider(new WorldLabelCapability(event.getObject())));
        }
    }

    @SubscribeEvent
    public void onAttachCapabilityEntity(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof EntityLivingBase && !event.getObject().hasCapability(LabelProvider.LABEL_CAPABILITY, null)) {
            event.addCapability(new ResourceLocation("labelmaker:labelCapability"), new LabelProvider(new EntityLabelCapability(event.getObject())));
        }
    }
}
