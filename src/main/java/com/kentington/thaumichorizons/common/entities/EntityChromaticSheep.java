//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.world.World;

public class EntityChromaticSheep extends EntitySheep {
    public EntityChromaticSheep(final World p_i1691_1_) {
        super(p_i1691_1_);
    }

    public void onLivingUpdate() {
        if (!this.worldObj.isRemote && this.ticksExisted % 30 == 0) {
            int color = this.getFleeceColor();
            if (color >= 15) {
                color = 0;
            } else {
                ++color;
            }
            this.setFleeceColor(color);
        }
        super.onLivingUpdate();
    }
}
