//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class EntityThunderhorse extends EntityHorse {

    boolean initialized;
    boolean flying;

    public EntityThunderhorse(final World p_i1685_1_) {
        super(p_i1685_1_);
        this.initialized = false;
        this.flying = false;
    }

    public void readEntityFromNBT(final NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        if (!(this.initialized = p_70037_1_.getBoolean("initialized"))) {
            final Multimap map = (Multimap) HashMultimap.create();
            map.put((Object) "generic.movementSpeed", (Object) new AttributeModifier("generic.movementSpeed", 0.1, 1));
            map.put((Object) "horse.jumpStrength", (Object) new AttributeModifier("horse.jumpStrength", 0.25, 1));
            map.put((Object) "generic.maxHealth", (Object) new AttributeModifier("generic.maxHealth", 4.0, 1));
            this.getAttributeMap().applyAttributeModifiers(map);
            this.initialized = true;
        }
        this.flying = p_70037_1_.getBoolean("flying");
    }

    public void writeEntityToNBT(final NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("initialized", this.initialized);
        p_70014_1_.setBoolean("flying", this.flying);
    }

    protected void fall(final float p_70069_1_) {
        if (p_70069_1_ > 1.0f) {
            this.playSound("mob.horse.land", 0.4f, 1.0f);
        }
        final int i = MathHelper.ceiling_float_int(p_70069_1_ * 0.5f - 3.0f);
        if (i > 0) {
            final Block block = this.worldObj.getBlock(
                    MathHelper.floor_double(this.posX),
                    MathHelper.floor_double(this.posY - 0.2 - this.prevRotationYaw),
                    MathHelper.floor_double(this.posZ));
            if (block.getMaterial() != Material.air) {
                final Block.SoundType soundtype = block.stepSound;
                this.worldObj.playSoundAtEntity(
                        (Entity) this,
                        soundtype.getStepResourcePath(),
                        soundtype.getVolume() * 0.5f,
                        soundtype.getPitch() * 0.75f);
            }
        }
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    public void toggleFlying() {
        if (this.riddenByEntity == null || !(this.riddenByEntity instanceof EntityPlayer)) {
            return;
        }
        if (!this.flying) {
            this.flying = true;
            ((EntityPlayer) this.riddenByEntity).capabilities.isFlying = true;
        } else {
            this.flying = false;
            ((EntityPlayer) this.riddenByEntity).capabilities.isFlying = false;
        }
    }

    public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase && this.isHorseSaddled()) {
            final float rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationYaw = rotationYaw;
            this.prevRotationYaw = rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5f;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            final float rotationYaw2 = this.rotationYaw;
            this.renderYawOffset = rotationYaw2;
            this.rotationYawHead = rotationYaw2;
            p_70612_1_ = ((EntityLivingBase) this.riddenByEntity).moveStrafing * 0.5f;
            p_70612_2_ = ((EntityLivingBase) this.riddenByEntity).moveForward;
            if (p_70612_2_ <= 0.0f) {
                p_70612_2_ *= 0.25f;
            }
            if (this.motionY > 0.0 || this.motionY < 0.0) {
                this.motionY *= 0.8999999761581421;
            }
            this.stepHeight = 1.0f;
            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1f;
            if (!this.worldObj.isRemote) {
                this.setAIMoveSpeed(
                        (float) this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
            }
            if (this.onGround) {
                this.jumpPower = 0.0f;
                this.setHorseJumping(false);
            }
            this.prevLimbSwingAmount = this.limbSwingAmount;
            final double d1 = this.posX - this.prevPosX;
            final double d2 = this.posZ - this.prevPosZ;
            float f4 = MathHelper.sqrt_double(d1 * d1 + d2 * d2) * 4.0f;
            if (f4 > 1.0f) {
                f4 = 1.0f;
            }
            this.limbSwingAmount += (f4 - this.limbSwingAmount) * 0.4f;
            this.limbSwing += this.limbSwingAmount;
        } else {
            this.stepHeight = 0.5f;
            this.jumpMovementFactor = 0.02f;
            super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
        }
    }
}
