package net.tylian.labelmaker.common.capability.label;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Created by Tylian on 4/15/2017.
 */
public class LabelCapabilityStorage implements IStorage<ILabelCapability> {
    public static final LabelCapabilityStorage INSTANCE = new LabelCapabilityStorage();

    @Override
    public NBTBase writeNBT(Capability<ILabelCapability> capability, ILabelCapability instance, EnumFacing side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<ILabelCapability> capability, ILabelCapability instance, EnumFacing side, NBTBase nbt) {
        instance.deserializeNBT((NBTTagCompound) nbt);
    }
}
