//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.lib.ExplosionAlchemite;

public class EntityAlchemitePrimed extends Entity {

    public EntityLivingBase alchemitePlacedBy;
    public int fuse;

    public EntityAlchemitePrimed(final World p_i1729_1_) {
        super(p_i1729_1_);
        this.fuse = 80;
        this.preventEntitySpawning = true;
        this.setSize(0.98f, 0.98f);
        this.yOffset = this.height / 2.0f;
    }

    public EntityAlchemitePrimed(final World p_i1730_1_, final double p_i1730_2_, final double p_i1730_4_,
            final double p_i1730_6_, final EntityLivingBase p_i1730_8_) {
        this(p_i1730_1_);
        this.setPosition(p_i1730_2_, p_i1730_4_, p_i1730_6_);
        final float f = (float) (Math.random() * 3.141592653589793 * 2.0);
        this.setSize(0.98f, 0.98f);
        this.motionX = -(float) Math.sin(f) * 0.02f;
        this.motionY = 0.20000000298023224;
        this.motionZ = -(float) Math.cos(f) * 0.02f;
        this.fuse = 80;
        this.prevPosX = p_i1730_2_;
        this.prevPosY = p_i1730_4_;
        this.prevPosZ = p_i1730_6_;
        this.alchemitePlacedBy = p_i1730_8_;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863;
        this.motionY *= 0.9800000190734863;
        this.motionZ *= 0.9800000190734863;
        if (this.onGround) {
            this.motionX *= 0.699999988079071;
            this.motionZ *= 0.699999988079071;
            this.motionY *= -0.5;
        }
        if (this.fuse-- <= 0) {
            this.setDead();
            if (!this.worldObj.isRemote) {
                this.explode();
            }
        } else {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0);
        }
    }

    private void explode() {
        final ExplosionAlchemite explosion = new ExplosionAlchemite(
                this.worldObj,
                (Entity) this.alchemitePlacedBy,
                this.posX,
                this.posY,
                this.posZ,
                5.0f);
        explosion.isFlaming = false;
        explosion.isSmoking = true;
        explosion.doExplosionA();
        explosion.doExplosionB(true);
    }

    protected void writeEntityToNBT(final NBTTagCompound p_70014_1_) {
        p_70014_1_.setByte("Fuse", (byte) this.fuse);
    }

    protected void readEntityFromNBT(final NBTTagCompound p_70037_1_) {
        this.fuse = p_70037_1_.getByte("Fuse");
    }

    protected void entityInit() {}

    public float getShadowSize() {
        return 0.0f;
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    public boolean canBeCollidedWith() {
        return !this.isDead;
    }
}
