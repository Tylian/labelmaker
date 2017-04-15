package net.tylian.labelmaker;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.tylian.labelmaker.common.CommonProxy;

@Mod(modid = LabelMaker.MODID, version = LabelMaker.VERSION)
public class LabelMaker
{
    public static final String MODID = "labelmaker";
    public static final String VERSION = "0.1";

    @SidedProxy(clientSide = "net.tylian.labelmaker.client.ClientProxy", serverSide = "net.tylian.labelmaker.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
            proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
