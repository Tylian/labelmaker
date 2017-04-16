package net.tylian.labelmaker.common.capability.label.impl;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tylian.labelmaker.common.capability.label.DefaultLabelCapability;
import net.tylian.labelmaker.common.labels.Label;

/**
 * Created by Tylian on 4/15/2017.
 */
public class WorldLabelCapability extends DefaultLabelCapability {
    private World world;

    public WorldLabelCapability(World worldIn) {
        this.world = worldIn;
    }

    public Label create(Vec3d offset, Vec3d rotation, String text) {
        return new Label(offset, rotation, text);
    }

}
