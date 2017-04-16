package net.tylian.labelmaker.common.capability.label;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.util.Constants;
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

    public Label create(NBTTagCompound nbt) {
        return new Label(nbt);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        for(Label label : labels) {
            list.appendTag(label.serializeNBT());
        }
        nbt.setTag("Labels", list);

        return new NBTTagCompound();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        NBTTagList items = nbt.getTagList("Labels", Constants.NBT.TAG_COMPOUND); // 10 = COMPOUND
        for(int i = 0; i < items.tagCount(); i++) {
            NBTTagCompound item = items.getCompoundTagAt(i);
            this.labels.add(this.create(item));
        }
    }
}
