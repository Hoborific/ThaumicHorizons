//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;

public class EntityGravekeeper extends EntityOcelot {
    public EntityGravekeeper(final World p_i1688_1_) {
        super(p_i1688_1_);
        List<EntityAITasks.EntityAITaskEntry> task = tasks.taskEntries;
        this.tasks.removeTask(task.get(6).action);
        this.targetTasks.addTask(2, (EntityAIBase)
                new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntitySkeleton.class, 0, false));
        this.targetTasks.addTask(3, (EntityAIBase)
                new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityZombie.class, 0, false));
        this.targetTasks.addTask(4, (EntityAIBase)
                new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityWither.class, 0, false));
        this.targetTasks.addTask(5, (EntityAIBase)
                new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityPigZombie.class, 0, false));
        this.targetTasks.addTask(6, (EntityAIBase)
                new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityGiantZombie.class, 0, false));
        this.targetTasks.addTask(7, (EntityAIBase)
                new EntityAINearestAttackableTarget((EntityCreature) this, (Class) EntityBrainyZombie.class, 0, false));
        this.targetTasks.addTask(8, (EntityAIBase) new EntityAINearestAttackableTarget(
                (EntityCreature) this, (Class) EntityEldritchGuardian.class, 0, false));
        this.targetTasks.addTask(9, (EntityAIBase) new EntityAINearestAttackableTarget(
                (EntityCreature) this, (Class) EntityGiantBrainyZombie.class, 0, false));
        this.targetTasks.addTask(10, (EntityAIBase) new EntityAINearestAttackableTarget(
                (EntityCreature) this, (Class) EntityInhabitedZombie.class, 0, false));
    }

    public boolean attackEntityAsMob(final Entity p_70652_1_) {
        return (p_70652_1_ instanceof EntityLivingBase && ((EntityLivingBase) p_70652_1_).isEntityUndead())
                || p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase) this), 2.0f);
    }

    public String getCommandSenderName() {
        return this.hasCustomNameTag()
                ? this.getCustomNameTag()
                : (this.isTamed()
                        ? StatCollector.translateToLocal("entity.ThaumicHorizons.Gravekeeper.name")
                        : super.getCommandSenderName());
    }

    public void updateAITick() {
        super.updateAITick();
        final List<EntityLivingBase> critters = (List<EntityLivingBase>) this.worldObj.getEntitiesWithinAABB(
                (Class) EntityLivingBase.class,
                AxisAlignedBB.getBoundingBox(
                        this.posX - 5.0,
                        this.posY - 5.0,
                        this.posZ - 5.0,
                        this.posX + 5.0,
                        this.posY + 5.0,
                        this.posZ + 5.0));
        for (final EntityLivingBase ent : critters) {
            if (ent.isEntityUndead()) {
                ent.setFire(1);
                Thaumcraft.proxy.beam(
                        this.worldObj,
                        this.posX,
                        this.posY + this.height / 2.0f,
                        this.posZ,
                        ent.posX,
                        ent.posY + ent.height / 2.0f,
                        ent.posZ,
                        0,
                        16773444,
                        false,
                        2.5f,
                        1);
            }
        }
    }

    protected void entityInit() {
        super.entityInit();
        final byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        this.dataWatcher.updateObject(16, (Object) (byte) (b0 | 0x4));
    }

    public boolean isTamed() {
        return true;
    }
}
