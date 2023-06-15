//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;

public class EntityAISitTH extends EntityAIBase {

    private final EntityLiving theEntity;
    private boolean isSitting;

    public EntityAISitTH(final EntityLiving p_i1654_1_) {
        this.theEntity = p_i1654_1_;
        this.setMutexBits(5);
    }

    public boolean shouldExecute() {
        if (this.theEntity.isInWater()) {
            return false;
        }
        if (!this.theEntity.onGround) {
            return false;
        }
        final EntityInfusionProperties prop = (EntityInfusionProperties) this.theEntity
                .getExtendedProperties("CreatureInfusion");
        final EntityLivingBase entitylivingbase = (EntityLivingBase) this.theEntity.worldObj
                .getPlayerEntityByName(prop.getOwner());
        return entitylivingbase == null || ((this.theEntity.getDistanceSqToEntity((Entity) entitylivingbase) >= 144.0
                || entitylivingbase.getAITarget() == null) && this.isSitting);
    }

    public void startExecuting() {
        final EntityInfusionProperties prop = (EntityInfusionProperties) this.theEntity
                .getExtendedProperties("CreatureInfusion");
        this.theEntity.getNavigator().clearPathEntity();
        prop.setSitting(true);
    }

    public void resetTask() {
        final EntityInfusionProperties prop = (EntityInfusionProperties) this.theEntity
                .getExtendedProperties("CreatureInfusion");
        prop.setSitting(false);
    }

    public void setSitting(final boolean p_75270_1_) {
        this.isSitting = p_75270_1_;
    }
}
