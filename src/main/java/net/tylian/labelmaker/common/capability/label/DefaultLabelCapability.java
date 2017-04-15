package net.tylian.labelmaker.common.capability.label;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.tylian.labelmaker.common.labels.Label;

import java.util.LinkedList;

/**
 * Created by Tylian on 4/15/2017.
 */
public class DefaultLabelCapability implements ILabelCapability {
    private LinkedList<Label> labels = new LinkedList<Label>();;

    public void add(Label label) {
        labels.add(label);
    }

    public void remove(Label label) {
        labels.remove(label);
    }

    public LinkedList<Label> getAll() {
        return labels;
    }

    public Label create(Vec3d offset, Vec3d rotation, String text) {
        return new Label(offset, rotation, text);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return new NBTTagCompound();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        //
    }

}
