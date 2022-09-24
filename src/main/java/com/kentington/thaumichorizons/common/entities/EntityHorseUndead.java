//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;

public class EntityHorseUndead extends EntityHorse {
    public EntityHorseUndead(final World p_i1685_1_) {
        super(p_i1685_1_);
        this.setHorseType(3);
    }

    public int getHorseType() {
        return 3;
    }

    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    public boolean func_110256_cu() {
        return false;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
                .setBaseValue(this.getEntityAttribute(SharedMonsterAttributes.maxHealth)
                                .getBaseValue()
                        + 20.0);
    }
}
