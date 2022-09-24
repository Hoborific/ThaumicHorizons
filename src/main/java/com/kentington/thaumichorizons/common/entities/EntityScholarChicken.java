//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityScholarChicken extends EntityChicken {
    public int timeUntilNextFeather;

    public EntityScholarChicken(final World p_i1682_1_) {
        super(p_i1682_1_);
        this.timeUntilNextFeather = this.rand.nextInt(4000) + 4000;
    }

    public void onLivingUpdate() {
        final int notTime = this.timeUntilNextEgg;
        this.timeUntilNextEgg = Integer.MAX_VALUE;
        super.onLivingUpdate();
        this.timeUntilNextEgg = notTime;
        this.field_70888_h = this.field_70886_e;
        this.field_70884_g = this.destPos;
        this.destPos += (float) ((this.onGround ? -1 : 4) * 0.3);
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
            this.dropItem(ThaumicHorizons.itemInkEgg, 1);
            this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        }
        if (!this.worldObj.isRemote && !this.isChild() && !this.func_152116_bZ() && --this.timeUntilNextFeather <= 0) {
            this.playSound("mob.chicken.plop", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            this.dropItem(Items.feather, 1);
            this.timeUntilNextFeather = this.rand.nextInt(4000) + 4000;
        }
    }

    public void writeEntityToNBT(final NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setInteger("egg", this.timeUntilNextEgg);
        p_70014_1_.setInteger("feather", this.timeUntilNextFeather);
    }

    public void readEntityFromNBT(final NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        this.timeUntilNextEgg = p_70037_1_.getInteger("egg");
        this.timeUntilNextFeather = p_70037_1_.getInteger("feather");
    }
}
