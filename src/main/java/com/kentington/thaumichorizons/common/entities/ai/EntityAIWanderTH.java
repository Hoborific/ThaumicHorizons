//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;

public class EntityAIWanderTH extends EntityAIBase {

    private EntityLiving entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;

    public EntityAIWanderTH(final EntityLiving p_i1648_1_, final double p_i1648_2_) {
        this.entity = p_i1648_1_;
        this.speed = p_i1648_2_;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        if (this.entity.getAge() >= 100) {
            return false;
        }
        if (this.entity.getRNG().nextInt(120) != 0) {
            return false;
        }
        final Vec3 vec3 = RandomPositionGeneratorTH.findRandomTarget(this.entity, 10, 7);
        if (vec3 == null) {
            return false;
        }
        this.xPosition = vec3.xCoord;
        this.yPosition = vec3.yCoord;
        this.zPosition = vec3.zCoord;
        return true;
    }

    public boolean continueExecuting() {
        return !this.entity.getNavigator().noPath();
    }

    public void startExecuting() {
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}
