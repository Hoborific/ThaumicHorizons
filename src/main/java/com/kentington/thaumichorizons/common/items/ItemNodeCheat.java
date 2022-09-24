//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVortex;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.tiles.TileNode;

public class ItemNodeCheat extends Item {
    @SideOnly(Side.CLIENT)
    public IIcon icon1;

    public ItemNodeCheat() {
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setUnlocalizedName("nodeCheat");
    }

    public boolean onItemUse(
            final ItemStack p_77648_1_,
            final EntityPlayer p_77648_2_,
            final World p_77648_3_,
            final int p_77648_4_,
            final int p_77648_5_,
            final int p_77648_6_,
            final int p_77648_7_,
            final float p_77648_8_,
            final float p_77648_9_,
            final float p_77648_10_) {
        final TileEntity te = p_77648_3_.getTileEntity(p_77648_4_, p_77648_5_, p_77648_6_);
        if (te != null && te instanceof TileNode) {
            final AspectList aspects = ((TileNode) te).getAspectsBase();
            p_77648_3_.setBlock(p_77648_4_, p_77648_5_, p_77648_6_, ThaumicHorizons.blockVortex);
            final TileVortex tco = (TileVortex) p_77648_3_.getTileEntity(p_77648_4_, p_77648_5_, p_77648_6_);
            tco.aspects = aspects.copy();
            return tco.cheat = true;
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon1 = ir.registerIcon("thaumichorizons:nodecheat");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon1;
    }

    public void addInformation(
            final ItemStack par1ItemStack,
            final EntityPlayer par2EntityPlayer,
            final List par3List,
            final boolean par4) {
        par3List.add("Use on a node to transform it");
        par3List.add("into a self-stabilized vortex");
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
    }
}
