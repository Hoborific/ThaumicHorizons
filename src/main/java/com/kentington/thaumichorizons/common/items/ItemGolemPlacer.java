// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.items;

import net.minecraft.nbt.NBTTagList;
import java.util.ArrayList;
import thaumcraft.common.entities.golems.ItemGolemBell;
import net.minecraft.entity.Entity;
import thaumcraft.codechicken.lib.math.MathHelper;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import net.minecraft.world.World;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class ItemGolemPlacer extends thaumcraft.common.entities.golems.ItemGolemPlacer
{
    public IIcon icon;
    public IIcon newBell;
    
    public ItemGolemPlacer() {
        this.setCreativeTab((CreativeTabs)null);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        super.registerIcons(ir);
        this.icon = ir.registerIcon("thaumichorizons:golem");
        this.newBell = ir.registerIcon("thaumichorizons:newbell");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }
    
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(final ItemStack stack, final int p_82790_2_) {
        if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey("block")) {
            return 0;
        }
        final int[] block = stack.getTagCompound().getIntArray("block");
        if (Block.getBlockById(block[0]) == Blocks.air) {
            return 0;
        }
        final int color = Block.getBlockById(block[0]).getMapColor(block[1]).colorValue;
        if (color != 0) {
            return color;
        }
        return -1;
    }
    
    public void addInformation(final ItemStack stack, final EntityPlayer par2EntityPlayer, final List list, final boolean par4) {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("block")) {
            final int[] block = stack.getTagCompound().getIntArray("block");
            final String name = "?";
            final ItemStack blockStack = new ItemStack(Block.getBlockById(block[0]), 1, block[1]);
            if (blockStack.getItem() != null) {
                list.add(blockStack.getDisplayName());
            }
            else if (Block.getBlockById(block[0]) == Blocks.air) {
                list.add("Voidling");
            }
            else {
                list.add(Block.getBlockById(block[0]).getLocalizedName());
            }
        }
        super.addInformation(stack, par2EntityPlayer, list, par4);
    }
    
    public boolean spawnCreature(final World par0World, final double par2, final double par4, final double par6, final int side, final ItemStack stack, final EntityPlayer player) {
        boolean adv = false;
        if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("advanced")) {
            adv = true;
        }
        final EntityGolemTH golem = new EntityGolemTH(par0World);
        if (golem != null) {
            golem.setLocationAndAngles(par2, par4, par6, par0World.rand.nextFloat() * 360.0f, 0.0f);
            golem.playLivingSound();
            golem.setHomeArea(MathHelper.floor_double(par2), MathHelper.floor_double(par4), MathHelper.floor_double(par6), 32);
            int[] block = { 0, 0 };
            if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("core")) {
                golem.setCore(stack.stackTagCompound.getByte("core"));
            }
            String deco = "";
            if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("deco")) {
                deco = stack.stackTagCompound.getString("deco");
                golem.decoration = deco;
            }
            if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("block")) {
                block = stack.stackTagCompound.getIntArray("block");
            }
            golem.setup(side);
            golem.loadGolem(golem.posX, golem.posY, golem.posZ, Block.getBlockById(block[0]), block[1], 600, adv, stack.stackTagCompound.getBoolean("berserk"), stack.stackTagCompound.getBoolean("explosive"));
            if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("upgrades")) {
                final int ul = golem.upgrades.length;
                golem.upgrades = stack.stackTagCompound.getByteArray("upgrades");
                if (ul != golem.upgrades.length) {
                    final byte[] tt = new byte[ul];
                    for (int a = 0; a < ul; ++a) {
                        tt[a] = -1;
                    }
                    for (int a = 0; a < golem.upgrades.length; ++a) {
                        if (a < ul) {
                            tt[a] = golem.upgrades[a];
                        }
                    }
                    golem.upgrades = tt;
                }
            }
            par0World.spawnEntityInWorld((Entity)golem);
            golem.setGolemDecoration(deco);
            golem.setOwner(player.getCommandSenderName());
            golem.setMarkers((ArrayList)ItemGolemBell.getMarkers(stack));
            int a2 = 0;
            for (final byte b : golem.upgrades) {
                golem.setUpgrade(a2, b);
                ++a2;
            }
            if (stack.hasDisplayName()) {
                golem.setCustomNameTag(stack.getDisplayName());
                golem.func_110163_bv();
            }
            if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("Inventory")) {
                final NBTTagList nbttaglist2 = stack.stackTagCompound.getTagList("Inventory", 10);
                golem.inventory.readFromNBT(nbttaglist2);
            }
        }
        return golem != null;
    }
    
    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.golemPlacer";
    }
}
