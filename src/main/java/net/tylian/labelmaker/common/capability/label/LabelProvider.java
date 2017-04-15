package net.tylian.labelmaker.common.capability.label;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * Created by Tylian on 4/15/2017.
 */
public class LabelProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound> {
    private ILabelCapability labelCapability;

    @CapabilityInject(ILabelCapability.class)
    public static final Capability<ILabelCapability> LABEL_CAPABILITY = null;

    public LabelProvider(ILabelCapability capability) {
        labelCapability = capability;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing){
        return capability == LABEL_CAPABILITY;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing){
        if(capability == LABEL_CAPABILITY) {
            return (T)labelCapability;
        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return labelCapability.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        labelCapability.deserializeNBT(nbt);
    }
}
