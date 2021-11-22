// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.items;

import java.util.List;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.Block;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.DimensionManager;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import thaumcraft.common.tiles.TileMirror;
import thaumcraft.common.config.ConfigBlocks;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import baubles.api.BaubleType;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.util.IIcon;
import thaumcraft.api.IRunicArmor;
import baubles.api.IBauble;
import net.minecraft.item.Item;

public class ItemAmuletMirror extends Item implements IBauble, IRunicArmor
{
    public IIcon icon;
    
    public ItemAmuletMirror() {
        this.maxStackSize = 1;
        this.canRepair = false;
        this.setMaxDamage(0);
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setHasSubtypes(true);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:amuletmirror");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }
    
    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.amuletMirror";
    }
    
    public EnumRarity getRarity(final ItemStack itemstack) {
        return EnumRarity.rare;
    }
    
    public BaubleType getBaubleType(final ItemStack itemstack) {
        return BaubleType.AMULET;
    }
    
    public int getRunicCharge(final ItemStack itemstack) {
        return 0;
    }
    
    public boolean canEquip(final ItemStack arg0, final EntityLivingBase arg1) {
        return true;
    }
    
    public boolean canUnequip(final ItemStack arg0, final EntityLivingBase arg1) {
        return true;
    }
    
    public void onEquipped(final ItemStack arg0, final EntityLivingBase arg1) {
    }
    
    public void onUnequipped(final ItemStack arg0, final EntityLivingBase arg1) {
    }
    
    public void onWornTick(final ItemStack arg0, final EntityLivingBase arg1) {
    }
    
    public boolean getShareTag() {
        return true;
    }
    
    public boolean hasEffect(final ItemStack par1ItemStack) {
        return par1ItemStack.hasTagCompound();
    }
    
    public boolean onItemUseFirst(final ItemStack itemstack, final EntityPlayer player, final World world, final int x, final int y, final int z, final int par7, final float par8, final float par9, final float par10) {
        final Block bi = world.getBlock(x, y, z);
        if (bi != ConfigBlocks.blockMirror) {
            return false;
        }
        if (world.isRemote) {
            player.swingItem();
            return super.onItemUseFirst(itemstack, player, world, x, y, z, par7, par8, par9, par10);
        }
        final TileEntity tm = world.getTileEntity(x, y, z);
        if (tm != null && tm instanceof TileMirror) {
            itemstack.setTagInfo("linkX", (NBTBase)new NBTTagInt(tm.xCoord));
            itemstack.setTagInfo("linkY", (NBTBase)new NBTTagInt(tm.yCoord));
            itemstack.setTagInfo("linkZ", (NBTBase)new NBTTagInt(tm.zCoord));
            itemstack.setTagInfo("linkDim", (NBTBase)new NBTTagInt(world.provider.dimensionId));
            itemstack.setTagInfo("dimname", (NBTBase)new NBTTagString(DimensionManager.getProvider(world.provider.dimensionId).getDimensionName()));
            world.playSoundEffect((double)x, (double)y, (double)z, "thaumcraft:jar", 1.0f, 2.0f);
            player.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + EnumChatFormatting.GRAY + StatCollector.translateToLocal("tc.handmirrorlinked")));
            player.inventoryContainer.detectAndSendChanges();
        }
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack item, final EntityPlayer par2EntityPlayer, final List list, final boolean par4) {
        if (item.hasTagCompound()) {
            final int lx = item.stackTagCompound.getInteger("linkX");
            final int ly = item.stackTagCompound.getInteger("linkY");
            final int lz = item.stackTagCompound.getInteger("linkZ");
            final int ldim = item.stackTagCompound.getInteger("linkDim");
            final String dimname = item.stackTagCompound.getString("dimname");
            list.add(StatCollector.translateToLocal("tc.handmirrorlinkedto") + " " + lx + "," + ly + "," + lz + " in " + dimname);
        }
    }
}
