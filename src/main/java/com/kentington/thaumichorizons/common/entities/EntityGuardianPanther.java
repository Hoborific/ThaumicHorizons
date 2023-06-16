//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityGuardianPanther extends EntityOcelot implements IEntityInfusedStats {

    public EntityGuardianPanther(final World p_i1688_1_) {
        super(p_i1688_1_);
        this.setSize(1.2f, 1.6f);
        final ArrayList<EntityAITasks.EntityAITaskEntry> toRemove = new ArrayList<EntityAITasks.EntityAITaskEntry>();
        List<EntityAITasks.EntityAITaskEntry> list4 = this.tasks.taskEntries;
        for (final EntityAITasks.EntityAITaskEntry task : list4) {
            toRemove.add(task);
        }
        for (final EntityAITasks.EntityAITaskEntry task : toRemove) {
            this.tasks.removeTask(task.action);
        }
        this.tasks.addTask(1, (EntityAIBase) new EntityAISwimming((EntityLiving) this));
        this.tasks.addTask(2, (EntityAIBase) this.aiSit);
        this.tasks.addTask(3, (EntityAIBase) new EntityAILeapAtTarget((EntityLiving) this, 0.5f));
        this.tasks.addTask(4, (EntityAIBase) new EntityAIAttackOnCollide((EntityCreature) this, 1.0, true));
        this.tasks.addTask(5, (EntityAIBase) new EntityAIFollowOwner((EntityTameable) this, 1.0, 10.0f, 5.0f));
        this.tasks.addTask(6, (EntityAIBase) new EntityAIMate((EntityAnimal) this, 0.8));
        this.tasks.addTask(7, (EntityAIBase) new EntityAIWander((EntityCreature) this, 0.8));
        this.tasks.addTask(
                8,
                (EntityAIBase) new EntityAIWatchClosest((EntityLiving) this, (Class) EntityPlayer.class, 10.0f));
        this.targetTasks.addTask(1, (EntityAIBase) new EntityAIOwnerHurtByTarget((EntityTameable) this));
        this.targetTasks.addTask(2, (EntityAIBase) new EntityAIOwnerHurtTarget((EntityTameable) this));
        this.targetTasks.addTask(3, (EntityAIBase) new EntityAIHurtByTarget((EntityCreature) this, true));
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5000000119209289);
    }

    public boolean interact(final EntityPlayer p_70085_1_) {
        final ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() instanceof ItemFood) {
            final ItemFood itemfood = (ItemFood) itemstack.getItem();
            if (this.getHealth() < this.getMaxHealth()) {
                --itemstack.stackSize;
                this.heal((float) itemfood.func_150905_g(itemstack));
                if (itemstack.stackSize <= 0) {
                    p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack) null);
                }
                return true;
            }
        }
        return super.interact(p_70085_1_);
    }

    public boolean attackEntityAsMob(final Entity p_70652_1_) {
        return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase) this), 6.0f);
    }

    public String getCommandSenderName() {
        return this.hasCustomNameTag() ? this.getCustomNameTag()
                : (this.isTamed() ? StatCollector.translateToLocal("entity.ThaumicHorizons.GuardianPanther.name")
                        : super.getCommandSenderName());
    }

    public void updateAITick() {
        super.updateAITick();
    }

    protected void entityInit() {
        super.entityInit();
        final byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        this.dataWatcher.updateObject(16, (Object) (byte) (b0 | 0x4));
    }

    public boolean isTamed() {
        return true;
    }

    public void resetStats() {
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24.0);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5000000119209289);
    }
}
