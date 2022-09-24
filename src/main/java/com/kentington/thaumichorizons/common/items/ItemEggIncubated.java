//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityEggIncubated;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemEggIncubated extends ItemEgg {
    @SideOnly(Side.CLIENT)
    public IIcon icon;

    public ItemEggIncubated() {
        this.maxStackSize = 16;
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public ItemStack onItemRightClick(
            final ItemStack p_77659_1_, final World p_77659_2_, final EntityPlayer p_77659_3_) {
        if (!p_77659_3_.capabilities.isCreativeMode) {
            --p_77659_1_.stackSize;
        }
        p_77659_2_.playSoundAtEntity(
                (Entity) p_77659_3_, "random.bow", 0.5f, 0.4f / (ItemEggIncubated.itemRand.nextFloat() * 0.4f + 0.8f));
        if (!p_77659_2_.isRemote) {
            p_77659_2_.spawnEntityInWorld((Entity) new EntityEggIncubated(p_77659_2_, (EntityLivingBase) p_77659_3_));
        }
        return p_77659_1_;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:eggincubated");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.eggIncubated";
    }
}
