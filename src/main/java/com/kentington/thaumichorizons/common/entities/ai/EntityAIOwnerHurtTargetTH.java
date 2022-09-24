//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities.ai;

import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;

public class EntityAIOwnerHurtTargetTH extends EntityAITarget {
    EntityLiving theEntityTameable;
    EntityLivingBase theTarget;
    private int field_142050_e;
    private static final String __OBFID = "CL_00001625";

    public EntityAIOwnerHurtTargetTH(final EntityLiving p_i1668_1_) {
        super((EntityCreature) p_i1668_1_, false);
        this.theEntityTameable = p_i1668_1_;
        this.setMutexBits(1);
    }

    public boolean shouldExecute() {
        final EntityInfusionProperties prop =
                (EntityInfusionProperties) this.theEntityTameable.getExtendedProperties("CreatureInfusion");
        final EntityLivingBase entitylivingbase =
                (EntityLivingBase) this.theEntityTameable.worldObj.getPlayerEntityByName(prop.getOwner());
        if (entitylivingbase == null) {
            return false;
        }
        this.theTarget = entitylivingbase.getLastAttacker();
        final int i = entitylivingbase.getLastAttackerTime();
        return i != this.field_142050_e && this.isSuitableTarget(this.theTarget, false);
    }

    public void startExecuting() {
        this.taskOwner.setAttackTarget(this.theTarget);
        final EntityInfusionProperties prop =
                (EntityInfusionProperties) this.theEntityTameable.getExtendedProperties("CreatureInfusion");
        final EntityLivingBase entitylivingbase =
                (EntityLivingBase) this.theEntityTameable.worldObj.getPlayerEntityByName(prop.getOwner());
        if (entitylivingbase != null) {
            this.field_142050_e = entitylivingbase.getLastAttackerTime();
        }
        super.startExecuting();
    }
}
