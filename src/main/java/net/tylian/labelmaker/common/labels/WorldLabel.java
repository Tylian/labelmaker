package net.tylian.labelmaker.common.labels;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Created by Tylian on 4/15/2017.
 */
public class WorldLabel extends Label {
    private Vec3d offset;
    private World parent;

    public WorldLabel(World world, Vec3d offset, Vec3d rotation, String text) {
        super(rotation, text);

        this.parent = world;
        this.offset = offset;
    }

    public Vec3d getWorldPosition() {
        return offset;
    }
}
