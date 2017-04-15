package net.tylian.labelmaker.common.labels;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

/**
 * Created by Tylian on 4/15/2017.
 */
public class EntityLabel extends Label {
    private Vec3d offset;
    private Entity parent;

    public EntityLabel(Entity entity, Vec3d offset, Vec3d rotation, String text) {
        super(offset, rotation, text);
    }

    @Override
    public Vec3d getParentPosition() {
        return this.parent.getPositionVector();
    }

    @Override
    public Vec3d getParentRotation() {
        return new Vec3d(this.parent.rotationPitch, this.parent.rotationYaw, 0.0d);
    }

}
