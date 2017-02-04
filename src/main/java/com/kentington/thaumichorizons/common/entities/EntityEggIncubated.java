// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.EntityEgg;

public class EntityEggIncubated extends EntityEgg
{
    public EntityEggIncubated(final World p_i1779_1_) {
        super(p_i1779_1_);
    }
    
    public EntityEggIncubated(final World p_i1780_1_, final EntityLivingBase p_i1780_2_) {
        super(p_i1780_1_, p_i1780_2_);
    }
    
    public EntityEggIncubated(final World p_i1781_1_, final double p_i1781_2_, final double p_i1781_4_, final double p_i1781_6_) {
        super(p_i1781_1_, p_i1781_2_, p_i1781_4_, p_i1781_6_);
    }
    
    protected void onImpact(final MovingObjectPosition p_70184_1_) {
        if (p_70184_1_.entityHit != null) {
            p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((Entity)this, (Entity)this.getThrower()), 0.0f);
        }
        if (!this.worldObj.isRemote) {
            final EntityChicken entitychicken = new EntityChicken(this.worldObj);
            entitychicken.setGrowingAge(-24000);
            entitychicken.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0f);
            this.worldObj.spawnEntityInWorld((Entity)entitychicken);
        }
        for (int j = 0; j < 8; ++j) {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
