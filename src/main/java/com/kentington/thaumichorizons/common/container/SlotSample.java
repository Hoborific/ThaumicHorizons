//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVat;

public class SlotSample extends Slot {

    public SlotSample(final TileVat p_i1824_1_, final int p_i1824_2_, final int p_i1824_3_, final int p_i1824_4_) {
        super((IInventory) p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    public boolean isItemValid(final ItemStack what) {
        return what.getItem() == ThaumicHorizons.itemSyringeBloodSample
                || what.getItem() == ThaumicHorizons.itemCorpseEffigy
                || ThaumicHorizons.incarnationItems.containsKey(what.getItem());
    }
}
