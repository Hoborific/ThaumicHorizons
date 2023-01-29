//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;

public class EntitySeawolf extends EntityWolf {

    public EntitySeawolf(final World p_i1696_1_) {
        super(p_i1696_1_);
        this.getNavigator().setAvoidsWater(false);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.isInWater()) {
            final float bonus = 0.025f;
            this.moveFlying(0.0f, 1.0f, bonus);
            this.setAir(300);
        }
    }
}
