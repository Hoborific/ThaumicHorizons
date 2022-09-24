//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities.ai;

import java.util.Random;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RandomPositionGeneratorTH {
    private static Vec3 staticVector;
    private static final String __OBFID = "CL_00001629";

    public static Vec3 findRandomTarget(final EntityLiving p_75463_0_, final int p_75463_1_, final int p_75463_2_) {
        return findRandomTargetBlock(p_75463_0_, p_75463_1_, p_75463_2_, null);
    }

    public static Vec3 findRandomTargetBlockTowards(
            final EntityLiving p_75464_0_, final int p_75464_1_, final int p_75464_2_, final Vec3 p_75464_3_) {
        RandomPositionGeneratorTH.staticVector.xCoord = p_75464_3_.xCoord - p_75464_0_.posX;
        RandomPositionGeneratorTH.staticVector.yCoord = p_75464_3_.yCoord - p_75464_0_.posY;
        RandomPositionGeneratorTH.staticVector.zCoord = p_75464_3_.zCoord - p_75464_0_.posZ;
        return findRandomTargetBlock(p_75464_0_, p_75464_1_, p_75464_2_, RandomPositionGeneratorTH.staticVector);
    }

    public static Vec3 findRandomTargetBlockAwayFrom(
            final EntityLiving p_75461_0_, final int p_75461_1_, final int p_75461_2_, final Vec3 p_75461_3_) {
        RandomPositionGeneratorTH.staticVector.xCoord = p_75461_0_.posX - p_75461_3_.xCoord;
        RandomPositionGeneratorTH.staticVector.yCoord = p_75461_0_.posY - p_75461_3_.yCoord;
        RandomPositionGeneratorTH.staticVector.zCoord = p_75461_0_.posZ - p_75461_3_.zCoord;
        return findRandomTargetBlock(p_75461_0_, p_75461_1_, p_75461_2_, RandomPositionGeneratorTH.staticVector);
    }

    private static Vec3 findRandomTargetBlock(
            final EntityLiving p_75462_0_, final int p_75462_1_, final int p_75462_2_, final Vec3 p_75462_3_) {
        final Random random = p_75462_0_.getRNG();
        boolean flag = false;
        int k = 0;
        int l = 0;
        int i1 = 0;
        final float f = -99999.0f;
        final boolean flag2 = false;
        for (int l2 = 0; l2 < 10; ++l2) {
            int j1 = random.nextInt(2 * p_75462_1_) - p_75462_1_;
            int i2 = random.nextInt(2 * p_75462_2_) - p_75462_2_;
            int k2 = random.nextInt(2 * p_75462_1_) - p_75462_1_;
            if (p_75462_3_ == null || j1 * p_75462_3_.xCoord + k2 * p_75462_3_.zCoord >= 0.0) {
                j1 += MathHelper.floor_double(p_75462_0_.posX);
                i2 += MathHelper.floor_double(p_75462_0_.posY);
                k2 += MathHelper.floor_double(p_75462_0_.posZ);
                if (!flag2) {
                    k = j1;
                    l = i2;
                    i1 = k2;
                    flag = true;
                }
            }
        }
        if (flag) {
            return Vec3.createVectorHelper((double) k, (double) l, (double) i1);
        }
        return null;
    }

    static {
        RandomPositionGeneratorTH.staticVector = Vec3.createVectorHelper(0.0, 0.0, 0.0);
    }
}
