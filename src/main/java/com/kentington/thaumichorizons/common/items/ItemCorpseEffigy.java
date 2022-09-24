//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCorpseEffigy extends Item {
    public ItemCorpseEffigy() {
        this.setMaxStackSize(1);
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setTextureName("thaumcraft:brain");
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.corpseEffigy";
    }
}
