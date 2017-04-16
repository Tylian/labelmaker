package net.tylian.labelmaker.common.labels;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Created by Tylian on 4/15/2017.
 */
public class EntityLabel extends Label {
    private int entityId;

    public EntityLabel(Entity entity, Vec3d offset, Vec3d rotation, String text) {
        this(entity.getEntityId(), offset, rotation, text);
    }

    public EntityLabel(int entityId, Vec3d offset, Vec3d rotation, String text) {
        super(offset, rotation, text);
        this.entityId = entityId;
    }

    public EntityLabel(NBTTagCompound nbt) {
        super(nbt);
    }

    @Override
    public boolean shouldRender(World worldIn) {s
        Entity entity = worldIn.getEntityByID(this.entityId);
        return entity != null && super.shouldRender(worldIn);
    }

    @Override
    public Vec3d getParentPosition(World worldIn) {
        Entity entity = worldIn.getEntityByID(this.entityId);
        return entity == null ? Vec3d.ZERO : entity.getPositionVector();
    }

    @Override
    public Vec3d getParentRotation(World worldIn) {
        Entity entity = worldIn.getEntityByID(this.entityId);
        return entity == null ? Vec3d.ZERO : Vec3d.fromPitchYawVector(entity.getPitchYaw());
    }


    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = super.serializeNBT();
        nbt.setInteger("Entity", this.entityId);
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        super.deserializeNBT(nbt);
        this.entityId = nbt.getInteger("Entity");
    }

}
