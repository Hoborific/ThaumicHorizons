// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.item.Item;
import net.minecraft.creativetab.CreativeTabs;

public final class CreativeTabTH extends CreativeTabs
{
    public CreativeTabTH(final int par1, final String par2Str) {
        super(par1, par2Str);
    }
    
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return ThaumicHorizons.itemDummyVat;
    }
}
