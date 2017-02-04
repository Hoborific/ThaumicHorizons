// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.nbt.NBTTagCompound;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.world.World;
import net.minecraft.entity.passive.EntityChicken;

public class EntityGoldChicken extends EntityChicken
{
    public EntityGoldChicken(final World p_i1682_1_) {
        super(p_i1682_1_);
    }
    
    public void onLivingUpdate() {
        final int notTime = this.timeUntilNextEgg;
        this.timeUntilNextEgg = Integer.MAX_VALUE;
        super.onLivingUpdate();
        this.timeUntilNextEgg = notTime;
        this.field_70888_h = this.field_70886_e;
        this.field_70884_g = this.destPos;
        this.destPos += (float)((this.onGround ? -1 : 4) * 0.3);
        if (this.destPos < 0.0f) {
            this.destPos = 0.0f;
        }
        if (this.destPos > 1.0f) {
            this.destPos = 1.0f;
        }
        if (!this.onGround && this.field_70889_i < 1.0f) {
            this.field_70889_i = 1.0f;
        }
        this.field_70889_i *= 0.9;
        if (!this.onGround && this.motionY < 0.0) {
            this.motionY *= 0.6;
        }
        this.field_70886_e += this.field_70889_i * 2.0f;
        if (!this.worldObj.isRemote && !this.isChild() && !this.func_152116_bZ() && --this.timeUntilNextEgg <= 0) {
            this.playSound("mob.chicken.plop", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            this.dropItem(ThaumicHorizons.itemGoldEgg, 1);
            this.timeUntilNextEgg = this.rand.nextInt(8000) + 8000;
        }
    }
    
    public void writeEntityToNBT(final NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setInteger("egg", this.timeUntilNextEgg);
    }
    
    public void readEntityFromNBT(final NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        this.timeUntilNextEgg = p_70037_1_.getInteger("egg");
    }
}
