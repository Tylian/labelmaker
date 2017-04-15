package net.tylian.labelmaker.common.capability.label;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.tylian.labelmaker.common.labels.Label;

import java.util.LinkedList;

/**
 * Created by Tylian on 4/15/2017.
 */
public interface ILabelCapability {
    void add(Label label);
    void remove(Label label);
    Label create(Vec3d offset, Vec3d rotation, String text);

    LinkedList<Label> getAll();

    NBTTagCompound serializeNBT();
    void deserializeNBT(NBTTagCompound nbt);
}
