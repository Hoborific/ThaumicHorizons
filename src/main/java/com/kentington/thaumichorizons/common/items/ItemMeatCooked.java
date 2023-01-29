//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMeatCooked extends ItemFood {

    @SideOnly(Side.CLIENT)
    public IIcon icon;

    public ItemMeatCooked() {
        super(7, 0.7f, false);
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:meatcooked");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.meatcookedTH";
    }
}
