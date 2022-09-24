//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemSyringeBlood extends Item {
    @SideOnly(Side.CLIENT)
    public IIcon icon;

    @SideOnly(Side.CLIENT)
    public IIcon phial;

    public ItemSyringeBlood() {
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item par1, final CreativeTabs par2CreativeTabs, final List par3List) {
        par3List.add(new ItemStack((Item) this, 1, 0));
        par3List.add(new ItemStack((Item) this, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:syringeBlood");
        this.phial = ir.registerIcon("thaumichorizons:phialBlood");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        if (par1 == 0) {
            return this.icon;
        }
        return this.phial;
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.syringeBlood." + par1ItemStack.getItemDamage();
    }
}
