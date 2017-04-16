package net.tylian.labelmaker.common.capability.label.impl;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.tylian.labelmaker.common.capability.label.DefaultLabelCapability;
import net.tylian.labelmaker.common.labels.EntityLabel;
import net.tylian.labelmaker.common.labels.Label;

/**
 * Created by Tylian on 4/15/2017.
 */
public class EntityLabelCapability extends DefaultLabelCapability {
    private Entity parent;

    public EntityLabelCapability(Entity parent) {
        this.parent = parent;
    }

    public Label create(Vec3d offset, Vec3d rotation, String text) {
        return new EntityLabel(this.parent, offset, rotation, text);
    }

    public Label create(NBTTagCompound nbt) {
        return new EntityLabel(nbt);
    }

}
