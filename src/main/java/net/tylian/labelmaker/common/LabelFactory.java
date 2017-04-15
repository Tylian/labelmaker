package net.tylian.labelmaker.common;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tylian.labelmaker.common.labels.EntityLabel;
import net.tylian.labelmaker.common.labels.Label;
import net.tylian.labelmaker.common.labels.WorldLabel;

import java.util.LinkedList;

/**
 * Created by Tylian on 4/15/2017.
 */
public class LabelFactory {
    static LinkedList<Label> labels = new LinkedList<Label>();

    static WorldLabel createLabel(World world, Vec3d offset, Vec3d rotation, String text) {
        return new WorldLabel(world, offset, rotation, text);
    }
    static EntityLabel createLabel(Entity entity, Vec3d offset, Vec3d rotation, String text) {
        return new EntityLabel(entity, offset, rotation, text);
    }
}
