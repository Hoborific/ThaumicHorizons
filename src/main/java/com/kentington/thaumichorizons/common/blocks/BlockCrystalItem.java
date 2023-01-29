//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockCrystalItem extends ItemBlock {

    public BlockCrystalItem(final Block block) {
        super(block);
        this.setHasSubtypes(true);
    }

    public int getMetadata(final int par1) {
        return par1;
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }
}
