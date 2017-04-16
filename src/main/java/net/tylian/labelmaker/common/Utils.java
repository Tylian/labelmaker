package net.tylian.labelmaker.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

/**
 * Created by Tylian on 4/16/2017.
 */
public class Utils {
    public static Vec3d nbtReadVec3d(NBTTagCompound nbt) {
        return new Vec3d(
                nbt.getDouble("X"),
                nbt.getDouble("Y"),
                nbt.getDouble("Z")
        );
    }

    public static NBTTagCompound nbtWriteVec3d(Vec3d vec) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setDouble("X", vec.xCoord);
        nbt.setDouble("Y", vec.yCoord);
        nbt.setDouble("Z", vec.zCoord);
        return nbt;
    }
}
