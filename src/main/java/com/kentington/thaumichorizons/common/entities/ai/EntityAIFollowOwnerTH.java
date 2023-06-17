//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;

public class EntityAIFollowOwnerTH extends EntityAIBase {

    private final EntityLiving thePet;
    private EntityLivingBase theOwner;
    World theWorld;
    private final double moveSpeed;
    private final PathNavigate petPathfinder;
    private int field_75343_h;
    float maxDist;
    float minDist;
    private boolean field_75344_i;

    public EntityAIFollowOwnerTH(final EntityLiving p_i1625_1_, final double p_i1625_2_, final float p_i1625_4_,
            final float p_i1625_5_) {
        this.thePet = p_i1625_1_;
        this.theWorld = p_i1625_1_.worldObj;
        this.moveSpeed = p_i1625_2_;
        this.petPathfinder = p_i1625_1_.getNavigator();
        this.minDist = p_i1625_4_;
        this.maxDist = p_i1625_5_;
        this.setMutexBits(3);
    }

    public boolean shouldExecute() {
        final EntityInfusionProperties prop = (EntityInfusionProperties) this.thePet
                .getExtendedProperties("CreatureInfusion");
        final EntityLivingBase entitylivingbase = this.thePet.worldObj.getPlayerEntityByName(prop.getOwner());
        if (entitylivingbase == null) {
            return false;
        }
        if (prop.isSitting()) {
            return false;
        }
        if (this.thePet.getDistanceSqToEntity(entitylivingbase) < this.minDist * this.minDist) {
            return false;
        }
        this.theOwner = entitylivingbase;
        return true;
    }

    public boolean continueExecuting() {
        final EntityInfusionProperties prop = (EntityInfusionProperties) this.thePet
                .getExtendedProperties("CreatureInfusion");
        return !this.petPathfinder.noPath()
                && this.thePet.getDistanceSqToEntity(this.theOwner) > this.maxDist * this.maxDist
                && !prop.isSitting();
    }

    public void startExecuting() {
        this.field_75343_h = 0;
        this.field_75344_i = this.thePet.getNavigator().getAvoidsWater();
        this.thePet.getNavigator().setAvoidsWater(false);
    }

    public void resetTask() {
        this.theOwner = null;
        this.petPathfinder.clearPathEntity();
        this.thePet.getNavigator().setAvoidsWater(this.field_75344_i);
    }

    public void updateTask() {
        this.thePet.getLookHelper()
                .setLookPositionWithEntity(this.theOwner, 10.0f, (float) this.thePet.getVerticalFaceSpeed());
        final EntityInfusionProperties prop = (EntityInfusionProperties) this.thePet
                .getExtendedProperties("CreatureInfusion");
        if (!prop.isSitting() && --this.field_75343_h <= 0) {
            this.field_75343_h = 10;
            if (!this.petPathfinder.tryMoveToEntityLiving(this.theOwner, this.moveSpeed) && !this.thePet.getLeashed()
                    && this.thePet.getDistanceSqToEntity(this.theOwner) >= 144.0) {
                final int i = MathHelper.floor_double(this.theOwner.posX) - 2;
                final int j = MathHelper.floor_double(this.theOwner.posZ) - 2;
                final int k = MathHelper.floor_double(this.theOwner.boundingBox.minY);
                for (int l = 0; l <= 4; ++l) {
                    for (int i2 = 0; i2 <= 4; ++i2) {
                        if ((l < 1 || i2 < 1 || l > 3 || i2 > 3)
                                && World.doesBlockHaveSolidTopSurface(this.theWorld, i + l, k - 1, j + i2)
                                && !this.theWorld.getBlock(i + l, k, j + i2).isNormalCube()
                                && !this.theWorld.getBlock(i + l, k + 1, j + i2).isNormalCube()) {
                            this.thePet.setLocationAndAngles(
                                    i + l + 0.5f,
                                    k,
                                    j + i2 + 0.5f,
                                    this.thePet.rotationYaw,
                                    this.thePet.rotationPitch);
                            this.petPathfinder.clearPathEntity();
                            return;
                        }
                    }
                }
            }
        }
    }
}
