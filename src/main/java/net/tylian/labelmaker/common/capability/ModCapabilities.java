package net.tylian.labelmaker.common.capability;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.tylian.labelmaker.common.capability.label.ILabelCapability;
import net.tylian.labelmaker.common.capability.label.LabelCapabilityStorage;

import java.util.concurrent.Callable;

/**
 * Created by Tylian on 4/15/2017.
 */
public class ModCapabilities {
    public static void Init() {
        CapabilityManager.INSTANCE.register(ILabelCapability.class, new LabelCapabilityStorage(), new Callable<ILabelCapability>()
        {
            @Override
            public ILabelCapability call() throws Exception
            {
                return null;
            }
        });
    }
}
