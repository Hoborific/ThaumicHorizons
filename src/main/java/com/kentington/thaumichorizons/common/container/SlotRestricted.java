//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRestricted extends Slot {

    ItemStack what;

    public SlotRestricted(final IInventory p_i1824_1_, final int p_i1824_2_, final int p_i1824_3_, final int p_i1824_4_,
            final ItemStack restriction) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.what = restriction;
    }

    public boolean isItemValid(final ItemStack p_75214_1_) {
        return this.what.getItem() == p_75214_1_.getItem();
    }
}
