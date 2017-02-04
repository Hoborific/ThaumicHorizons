// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.items;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.texture.IIconRegister;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;

public class ItemKeystone extends Item
{
    @SideOnly(Side.CLIENT)
    public IIcon icon;
    
    public ItemKeystone() {
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setMaxStackSize(1);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:keystone");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }
    
    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        if (par1ItemStack.getTagCompound() != null && par1ItemStack.getTagCompound().getInteger("dimension") != 0) {
            return "item.keystoneTH";
        }
        return "item.keystoneBlank";
    }
    
    public void addInformation(final ItemStack par1ItemStack, final EntityPlayer par2EntityPlayer, final List par3List, final boolean par4) {
        if (par1ItemStack.getTagCompound() != null && par1ItemStack.getTagCompound().getInteger("dimension") != 0) {
            par3List.add(PocketPlaneData.planes.get(par1ItemStack.getTagCompound().getInteger("dimension")).name);
        }
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
    }
    
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(final ItemStack par1ItemStack, final int p_82790_2_) {
        if (par1ItemStack.getTagCompound() != null) {
            return PocketPlaneData.planes.get(par1ItemStack.getTagCompound().getInteger("dimension")).color;
        }
        return 16777215;
    }
    
    public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer p) {
        if (stack.getTagCompound() == null && p.dimension == ThaumicHorizons.dimensionPocketId) {
            final ItemStack newStack = new ItemStack(ThaumicHorizons.itemKeystone);
            (newStack.stackTagCompound = new NBTTagCompound()).setInteger("dimension", ((int)p.posZ + 128) / 256);
            return newStack;
        }
        return stack;
    }
}
