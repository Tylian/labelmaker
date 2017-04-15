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
        super(rotation, text);

        this.parent = entity;
        this.offset = offset;
    }

    public Vec3d getWorldPosition() {
        return this.parent.getPositionVector().add(offset);
    }
}
