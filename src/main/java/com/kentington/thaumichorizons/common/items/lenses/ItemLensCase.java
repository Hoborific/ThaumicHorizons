//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items.lenses;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLensCase extends Item implements IBauble {

    private IIcon icon;

    public ItemLensCase() {
        this.setMaxStackSize(1);
        this.setHasSubtypes(false);
        this.setMaxDamage(0);
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister par1IconRegister) {
        this.icon = par1IconRegister.registerIcon("thaumichorizons:lenscase");
    }

    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }

    public boolean getShareTag() {
        return true;
    }

    public EnumRarity getRarity(final ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public boolean hasEffect(final ItemStack par1ItemStack) {
        return false;
    }

    public ItemStack onItemRightClick(final ItemStack par1ItemStack, final World par2World,
            final EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            par3EntityPlayer.openGui(
                    (Object) ThaumicHorizons.instance,
                    8,
                    par2World,
                    MathHelper.floor_double(par3EntityPlayer.posX),
                    MathHelper.floor_double(par3EntityPlayer.posY),
                    MathHelper.floor_double(par3EntityPlayer.posZ));
        }
        return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack item, final EntityPlayer par2EntityPlayer, final List list,
            final boolean par4) {
        if (item.hasTagCompound()) {}
    }

    public ItemStack[] getInventory(final ItemStack item) {
        final ItemStack[] stackList = new ItemStack[18];
        if (item.hasTagCompound()) {
            final NBTTagList var2 = item.stackTagCompound.getTagList("Inventory", 10);
            for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
                final NBTTagCompound var4 = var2.getCompoundTagAt(var3);
                final int var5 = var4.getByte("Slot") & 0xFF;
                if (var5 >= 0 && var5 < stackList.length) {
                    stackList[var5] = ItemStack.loadItemStackFromNBT(var4);
                }
            }
        }
        return stackList;
    }

    public void setInventory(final ItemStack item, final ItemStack[] stackList) {
        final NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < stackList.length; ++var3) {
            if (stackList[var3] != null) {
                final NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                stackList[var3].writeToNBT(var4);
                var2.appendTag((NBTBase) var4);
            }
        }
        item.setTagInfo("Inventory", (NBTBase) var2);
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.lensCase";
    }

    public BaubleType getBaubleType(final ItemStack itemstack) {
        return BaubleType.BELT;
    }

    public void onWornTick(final ItemStack itemstack, final EntityLivingBase player) {}

    public void onEquipped(final ItemStack itemstack, final EntityLivingBase player) {}

    public void onUnequipped(final ItemStack itemstack, final EntityLivingBase player) {}

    public boolean canEquip(final ItemStack itemstack, final EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(final ItemStack itemstack, final EntityLivingBase player) {
        return true;
    }
}
