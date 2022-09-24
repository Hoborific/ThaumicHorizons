//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBucketChocolate extends ItemBucketMilk {
    @SideOnly(Side.CLIENT)
    public IIcon icon;

    public ItemBucketChocolate() {
        this.setMaxStackSize(1);
        this.setContainerItem(Items.bucket);
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public ItemStack onEaten(final ItemStack p_77654_1_, final World p_77654_2_, final EntityPlayer p_77654_3_) {
        if (!p_77654_3_.capabilities.isCreativeMode) {
            --p_77654_1_.stackSize;
        }
        if (!p_77654_2_.isRemote) {
            p_77654_3_.curePotionEffects(new ItemStack(Items.milk_bucket));
        }
        return (p_77654_1_.stackSize <= 0) ? new ItemStack(Items.bucket) : p_77654_1_;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:bucket_chocolatemilk");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.chocolateMilk";
    }
}
