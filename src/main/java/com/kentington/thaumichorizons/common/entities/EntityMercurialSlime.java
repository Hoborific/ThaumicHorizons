//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityMercurialSlime extends EntitySlime {

    public EntityMercurialSlime(final World p_i1742_1_) {
        super(p_i1742_1_);
    }

    protected String getSlimeParticle() {
        return "snowshovel";
    }

    protected Item getDropItem() {
        return Item.getItemById(0);
    }

    public void setDead() {
        final int i = this.getSlimeSize();
        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0f) {
            for (int j = 2 + this.rand.nextInt(3), k = 0; k < j; ++k) {
                final float f = (k % 2 - 0.5f) * i / 4.0f;
                final float f2 = (k / 2 - 0.5f) * i / 4.0f;
                final EntityMercurialSlime entityslime = this.createInstance();
                entityslime.setSlimeSize(i / 2);
                entityslime.setLocationAndAngles(
                        this.posX + f,
                        this.posY + 0.5,
                        this.posZ + f2,
                        this.rand.nextFloat() * 360.0f,
                        0.0f);
                this.worldObj.spawnEntityInWorld(entityslime);
            }
        }
        super.setDead();
    }

    protected EntityMercurialSlime createInstance() {
        return new EntityMercurialSlime(this.worldObj);
    }
}
