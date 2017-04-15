package net.tylian.labelmaker.client;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.tylian.labelmaker.client.render.LabelRenderEventHandler;
import net.tylian.labelmaker.common.CommonProxy;

/**
 * Created by Tylian on 4/15/2017.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForge.EVENT_BUS.register(new LabelRenderEventHandler());
    }
}
