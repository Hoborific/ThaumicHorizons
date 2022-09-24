//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockCrystal extends BlockBreakable {
    public IIcon[] icon;

    public BlockCrystal() {
        super("thaumichorizons:blockCrystalRed", Material.glass, false);
        this.icon = new IIcon[16];
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setHardness(1.75f);
        this.setResistance(0.5f);
        this.setStepSound(Block.soundTypeGlass);
        this.setLightOpacity(1);
        this.setBlockName("ThaumicHorizons_crystal");
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.icon[0] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalWhite");
        this.icon[1] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalOrange");
        this.icon[2] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalMagenta");
        this.icon[3] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalLightBlue");
        this.icon[4] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalYellow");
        this.icon[5] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalLime");
        this.icon[6] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalPink");
        this.icon[7] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalGray");
        this.icon[8] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalLightGray");
        this.icon[9] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalCyan");
        this.icon[10] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalPurple");
        this.icon[11] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalBlue");
        this.icon[12] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalBrown");
        this.icon[13] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalGreen");
        this.icon[14] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalRed");
        this.icon[15] = par1IconRegister.registerIcon("thaumichorizons:blockCrystalBlack");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int par1, final int par2) {
        return this.icon[par2];
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(final Item par1, final CreativeTabs par2CreativeTabs, final List par3List) {
        for (int var4 = 0; var4 < 16; ++var4) {
            par3List.add(new ItemStack((Block) this, 1, var4));
        }
    }

    public int damageDropped(final int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public MapColor getMapColor(final int p_149728_1_) {
        return MapColor.getMapColorForBlockColored(p_149728_1_);
    }
}
