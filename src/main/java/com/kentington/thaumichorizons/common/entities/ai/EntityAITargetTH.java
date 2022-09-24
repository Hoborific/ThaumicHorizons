//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;

public abstract class EntityAITargetTH extends EntityAIBase {
    protected EntityLiving taskOwner;
    protected boolean shouldCheckSight;
    private boolean nearbyOnly;
    private int targetSearchStatus;
    private int targetSearchDelay;
    private int field_75298_g;
    private static final String __OBFID = "CL_00001626";

    public EntityAITargetTH(final EntityLiving p_i1669_1_, final boolean p_i1669_2_) {
        this(p_i1669_1_, p_i1669_2_, false);
    }

    public EntityAITargetTH(final EntityLiving p_i1670_1_, final boolean p_i1670_2_, final boolean p_i1670_3_) {
        this.taskOwner = p_i1670_1_;
        this.shouldCheckSight = p_i1670_2_;
        this.nearbyOnly = p_i1670_3_;
    }

    public boolean continueExecuting() {
        final EntityLivingBase entitylivingbase = this.taskOwner.getAttackTarget();
        if (entitylivingbase == null) {
            return false;
        }
        if (!entitylivingbase.isEntityAlive()) {
            return false;
        }
        final double d0 = this.getTargetDistance();
        if (this.taskOwner.getDistanceSqToEntity((Entity) entitylivingbase) > d0 * d0) {
            return false;
        }
        if (this.shouldCheckSight) {
            if (this.taskOwner.getEntitySenses().canSee((Entity) entitylivingbase)) {
                this.field_75298_g = 0;
            } else if (++this.field_75298_g > 60) {
                return false;
            }
        }
        return !(entitylivingbase instanceof EntityPlayerMP)
                || !((EntityPlayerMP) entitylivingbase).theItemInWorldManager.isCreative();
    }

    protected double getTargetDistance() {
        final IAttributeInstance iattributeinstance =
                this.taskOwner.getEntityAttribute(SharedMonsterAttributes.followRange);
        return (iattributeinstance == null) ? 16.0 : iattributeinstance.getAttributeValue();
    }

    public void startExecuting() {
        this.targetSearchStatus = 0;
        this.targetSearchDelay = 0;
        this.field_75298_g = 0;
    }

    public void resetTask() {
        this.taskOwner.setAttackTarget((EntityLivingBase) null);
    }

    protected boolean isSuitableTarget(final EntityLivingBase p_75296_1_, final boolean p_75296_2_) {
        if (p_75296_1_ == null) {
            return false;
        }
        if (p_75296_1_ == this.taskOwner) {
            return false;
        }
        if (!p_75296_1_.isEntityAlive()) {
            return false;
        }
        if (!this.taskOwner.canAttackClass((Class) p_75296_1_.getClass())) {
            return false;
        }
        if (this.taskOwner instanceof IEntityOwnable
                && StringUtils.isNotEmpty((CharSequence) ((IEntityOwnable) this.taskOwner).func_152113_b())) {
            if (p_75296_1_ instanceof IEntityOwnable
                    && ((IEntityOwnable) this.taskOwner)
                            .func_152113_b()
                            .equals(((IEntityOwnable) p_75296_1_).func_152113_b())) {
                return false;
            }
            if (p_75296_1_ == ((IEntityOwnable) this.taskOwner).getOwner()) {
                return false;
            }
        } else if (p_75296_1_ instanceof EntityPlayer
                && !p_75296_2_
                && ((EntityPlayer) p_75296_1_).capabilities.disableDamage) {
            return false;
        }
        if (this.shouldCheckSight && !this.taskOwner.getEntitySenses().canSee((Entity) p_75296_1_)) {
            return false;
        }
        if (this.nearbyOnly) {
            if (--this.targetSearchDelay <= 0) {
                this.targetSearchStatus = 0;
            }
            if (this.targetSearchStatus == 0) {
                this.targetSearchStatus = (this.canEasilyReach(p_75296_1_) ? 1 : 2);
            }
            if (this.targetSearchStatus == 2) {
                return false;
            }
        }
        return true;
    }

    private boolean canEasilyReach(final EntityLivingBase p_75295_1_) {
        this.targetSearchDelay = 10 + this.taskOwner.getRNG().nextInt(5);
        final PathEntity pathentity = this.taskOwner.getNavigator().getPathToEntityLiving((Entity) p_75295_1_);
        if (pathentity == null) {
            return false;
        }
        final PathPoint pathpoint = pathentity.getFinalPathPoint();
        if (pathpoint == null) {
            return false;
        }
        final int i = pathpoint.xCoord - MathHelper.floor_double(p_75295_1_.posX);
        final int j = pathpoint.zCoord - MathHelper.floor_double(p_75295_1_.posZ);
        return i * i + j * j <= 2.25;
    }
}
