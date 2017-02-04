// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.item.EntityItem;

public class EntityItemInvulnerable extends EntityItem
{
    public EntityItemInvulnerable(final World p_i1710_1_, final double p_i1710_2_, final double p_i1710_4_, final double p_i1710_6_, final ItemStack p_i1710_8_) {
        super(p_i1710_1_, p_i1710_2_, p_i1710_4_, p_i1710_6_, p_i1710_8_);
    }
    
    public boolean attackEntityFrom(final DamageSource source, final float num) {
        return false;
    }
}
