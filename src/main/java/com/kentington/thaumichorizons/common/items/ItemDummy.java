// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.items;

import net.minecraft.util.StatCollector;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;

public class ItemDummy extends Item
{
    @SideOnly(Side.CLIENT)
    public IIcon iconCow;
    public IIcon iconPig;
    public IIcon iconSheep;
    public IIcon iconChicken;
    public IIcon iconCat;
    public IIcon iconDog;
    public IIcon iconHuman;
    public IIcon iconHorse;
    public IIcon iconSpider;
    
    public ItemDummy() {
        this.setCreativeTab((CreativeTabs)null);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.iconCow = ir.registerIcon("thaumichorizons:cow");
        this.iconPig = ir.registerIcon("thaumichorizons:pig");
        this.iconSheep = ir.registerIcon("thaumichorizons:sheep");
        this.iconChicken = ir.registerIcon("thaumichorizons:chicken");
        this.iconCat = ir.registerIcon("thaumichorizons:cat");
        this.iconDog = ir.registerIcon("thaumichorizons:wolf");
        this.iconHuman = ir.registerIcon("thaumichorizons:human");
        this.iconHorse = ir.registerIcon("thaumichorizons:horse");
        this.iconSpider = ir.registerIcon("thaumichorizons:spider");
    }
    
    public String getItemStackDisplayName(final ItemStack stack) {
        if (stack.getTagCompound() != null) {
            return StatCollector.translateToLocal(stack.getTagCompound().getString("infName"));
        }
        final int md = stack.getItemDamage();
        switch (md) {
            case 0: {
                return StatCollector.translateToLocal("entity.Cow.name");
            }
            case 1: {
                return StatCollector.translateToLocal("entity.Pig.name");
            }
            case 2: {
                return StatCollector.translateToLocal("entity.Sheep.name");
            }
            case 3: {
                return StatCollector.translateToLocal("entity.Chicken.name");
            }
            case 4: {
                return StatCollector.translateToLocal("entity.Cat.name");
            }
            case 5: {
                return StatCollector.translateToLocal("entity.Wolf.name");
            }
            case 6: {
                return StatCollector.translateToLocal("entity.horse.name");
            }
            case 8: {
                return StatCollector.translateToLocal("entity.Spider.name");
            }
            case 15: {
                return StatCollector.translateToLocal("thaumichorizons.revived");
            }
            default: {
                return "Creature";
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int md) {
        switch (md) {
            case 0: {
                return this.iconCow;
            }
            case 1: {
                return this.iconPig;
            }
            case 2: {
                return this.iconSheep;
            }
            case 3: {
                return this.iconChicken;
            }
            case 4: {
                return this.iconCat;
            }
            case 5: {
                return this.iconDog;
            }
            case 6: {
                return this.iconHorse;
            }
            case 8: {
                return this.iconSpider;
            }
            case 15: {
                return this.iconHuman;
            }
            default: {
                return this.iconCat;
            }
        }
    }
}
