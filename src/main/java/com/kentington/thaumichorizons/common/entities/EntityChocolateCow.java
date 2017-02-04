// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.item.ItemStack;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.init.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.entity.passive.EntityCow;

public class EntityChocolateCow extends EntityCow
{
    public EntityChocolateCow(final World p_i1683_1_) {
        super(p_i1683_1_);
    }
    
    public boolean interact(final EntityPlayer p_70085_1_) {
        final ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() == Items.bucket) {
            if (itemstack.stackSize-- == 1) {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, new ItemStack(ThaumicHorizons.itemBucketChocolate));
            }
            else if (!p_70085_1_.inventory.addItemStackToInventory(new ItemStack(ThaumicHorizons.itemBucketChocolate))) {
                p_70085_1_.dropPlayerItemWithRandomChoice(new ItemStack(ThaumicHorizons.itemBucketChocolate, 1, 0), false);
            }
            return true;
        }
        return super.interact(p_70085_1_);
    }
}
