//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;

import com.kentington.thaumichorizons.common.entities.ai.EntityAIEatStone;

public class EntityOrePig extends EntityPig {

    int nuggetPercent;

    public EntityOrePig(final World p_i1689_1_) {
        super(p_i1689_1_);
        this.tasks.addTask(9, (EntityAIBase) new EntityAIEatStone(this));
        this.nuggetPercent = 0;
    }

    public void eatStone() {
        this.nuggetPercent += this.worldObj.rand.nextInt(15) + 1;
        if (this.nuggetPercent >= 100) {
            this.nuggetPercent -= 100;
            this.excreteNugget();
        }
    }

    void excreteNugget() {
        final int type = this.worldObj.rand.nextInt(75);
        if (type < 6) {
            this.entityDropItem(new ItemStack(Items.gold_nugget), 0.3f);
        } else if (type < 12) {
            if (Config.foundSilverIngot) {
                this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 3), 0.3f);
            } else {
                this.excreteNugget();
            }
        } else if (type < 20) {
            if (Config.foundCopperIngot) {
                this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 1), 0.3f);
            } else {
                this.excreteNugget();
            }
        } else if (type < 30) {
            if (Config.foundTinIngot) {
                this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 2), 0.3f);
            } else {
                this.excreteNugget();
            }
        } else if (type < 40) {
            if (Config.foundLeadIngot) {
                this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 4), 0.3f);
            } else {
                this.excreteNugget();
            }
        } else if (type < 50) {
            this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 5), 0.3f);
        } else {
            this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 0), 0.3f);
        }
    }

    protected void dropFewItems(final boolean p_70628_1_, final int p_70628_2_) {
        for (int j = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + p_70628_2_), k = 0; k < j; ++k) {
            this.entityDropItem(new ItemStack(Blocks.stone), 1.0f);
        }
        if (this.getSaddled()) {
            this.dropItem(Items.saddle, 1);
        }
    }

    public void writeEntityToNBT(final NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setInteger("percent", this.nuggetPercent);
    }

    public void readEntityFromNBT(final NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        this.nuggetPercent = p_70037_1_.getInteger("percent");
    }
}
