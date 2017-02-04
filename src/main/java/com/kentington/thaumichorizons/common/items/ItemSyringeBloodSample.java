// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.items;

import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.common.Thaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import net.minecraft.entity.player.EntityPlayer;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.StatCollector;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.texture.IIconRegister;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;

public class ItemSyringeBloodSample extends Item
{
    @SideOnly(Side.CLIENT)
    public IIcon icon;
    
    public ItemSyringeBloodSample() {
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:syringeBlood");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }
    
    public String getItemStackDisplayName(final ItemStack stack) {
        if (stack.hasTagCompound()) {
            return StatCollector.translateToLocal("item.syringeSample.name") + ": " + stack.getTagCompound().getString("critterName");
        }
        return StatCollector.translateToLocal("item.syringeSample.name") + ": INVALID";
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item par1, final CreativeTabs par2CreativeTabs, final List par3List) {
    }
    
    public void addInformation(final ItemStack sample, final EntityPlayer player, final List list, final boolean par4) {
        list.add("Essentia required to clone:");
        final AspectList asp = new AspectList().add(Aspect.LIFE, 4);
        if (sample.hasTagCompound() && sample.stackTagCompound.getCompoundTag("critter") != null && sample.stackTagCompound.getCompoundTag("critter").getCompoundTag("CreatureInfusion") != null) {
            final NBTTagCompound tlist = sample.stackTagCompound.getCompoundTag("critter").getCompoundTag("CreatureInfusion").getCompoundTag("InfusionCosts");
            if (tlist != null && tlist.hasKey("Aspects")) {
                final NBTTagList aspex = tlist.getTagList("Aspects", 10);
                for (int j = 0; j < aspex.tagCount(); ++j) {
                    final NBTTagCompound rs = aspex.getCompoundTagAt(j);
                    if (rs.hasKey("key")) {
                        asp.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
                    }
                }
            }
        }
        for (final Aspect tag : asp.getAspectsSorted()) {
            if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.getCommandSenderName(), tag)) {
                list.add(tag.getName() + " x" + asp.getAmount(tag));
            }
            else {
                list.add(StatCollector.translateToLocal("tc.aspect.unknown"));
            }
        }
    }
}
