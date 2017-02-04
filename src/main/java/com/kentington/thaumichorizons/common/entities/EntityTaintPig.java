// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.entities;

import thaumcraft.common.config.Config;
import net.minecraft.potion.Potion;
import net.minecraft.entity.ai.EntityAIBase;
import com.kentington.thaumichorizons.common.entities.ai.EntityAIEatTaint;
import net.minecraft.world.World;
import net.minecraft.entity.passive.EntityPig;

public class EntityTaintPig extends EntityPig
{
    public EntityTaintPig(final World p_i1689_1_) {
        super(p_i1689_1_);
        this.tasks.addTask(9, (EntityAIBase)new EntityAIEatTaint(this));
    }
    
    public void updateAITick() {
        super.updateAITick();
        if (this.getActivePotionEffect(Potion.potionTypes[Config.potionTaintPoisonID]) != null) {
            this.removePotionEffect(Config.potionTaintPoisonID);
        }
        if (this.getActivePotionEffect(Potion.potionTypes[Config.potionInfVisExhaustID]) != null) {
            this.removePotionEffect(Config.potionInfVisExhaustID);
        }
        if (this.getActivePotionEffect(Potion.potionTypes[Config.potionVisExhaustID]) != null) {
            this.removePotionEffect(Config.potionVisExhaustID);
        }
    }
}
