//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import thaumcraft.api.nodes.INode;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileNodeMonitor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNodeMonitor extends BlockContainer {

    IIcon icon;

    public BlockNodeMonitor() {
        super(Material.glass);
        this.setHardness(0.7f);
        this.setResistance(1.0f);
        this.setLightLevel(0.5f);
        this.setBlockName("ThaumicHorizons_nodeMonitor");
        this.setBlockTextureName("ThaumicHorizons:nodeMonitor");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(final World world, final int metadata) {
        final TileNodeMonitor node = new TileNodeMonitor();
        return node;
    }

    public boolean canPlaceBlockAt(final World p_149742_1_, final int p_149742_2_, final int p_149742_3_,
            final int p_149742_4_) {
        return p_149742_1_.getTileEntity(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof INode
                || p_149742_1_.getTileEntity(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof INode
                || p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof INode
                || p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof INode
                || p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof INode
                || p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof INode;
    }

    public boolean canPlaceBlockOnSide(final World p_149742_1_, final int p_149742_2_, final int p_149742_3_,
            final int p_149742_4_, final int p_149742_5_) {
        final ForgeDirection dir = ForgeDirection.getOrientation(p_149742_5_);
        return (dir == ForgeDirection.DOWN
                && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof INode)
                || (dir == ForgeDirection.UP
                        && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof INode)
                || (dir == ForgeDirection.NORTH
                        && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof INode)
                || (dir == ForgeDirection.SOUTH
                        && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof INode)
                || (dir == ForgeDirection.WEST
                        && p_149742_1_.getTileEntity(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof INode)
                || (dir == ForgeDirection.EAST
                        && p_149742_1_.getTileEntity(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof INode);
    }

    public int onBlockPlaced(final World p_149742_1_, final int p_149742_2_, final int p_149742_3_,
            final int p_149742_4_, final int p_149660_5_, final float p_149660_6_, final float p_149660_7_,
            final float p_149660_8_, final int p_149660_9_) {
        if (p_149660_5_ == 0 && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof INode) {
            return 0;
        }
        if (p_149660_5_ == 1 && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof INode) {
            return 1;
        }
        if (p_149660_5_ == 2 && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof INode) {
            return 2;
        }
        if (p_149660_5_ == 3 && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof INode) {
            return 3;
        }
        if (p_149660_5_ == 4 && p_149742_1_.getTileEntity(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof INode) {
            return 4;
        }
        if (p_149660_5_ == 5 && p_149742_1_.getTileEntity(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof INode) {
            return 5;
        }
        return -1;
    }

    public boolean canProvidePower() {
        return true;
    }

    public int isProvidingStrongPower(final IBlockAccess p_149748_1_, final int p_149748_2_, final int p_149748_3_,
            final int p_149748_4_, final int p_149748_5_) {
        return ((TileNodeMonitor) p_149748_1_.getTileEntity(p_149748_2_, p_149748_3_, p_149748_4_)).activated ? 15 : 0;
    }

    public int isProvidingWeakPower(final IBlockAccess p_149709_1_, final int p_149709_2_, final int p_149709_3_,
            final int p_149709_4_, final int p_149709_5_) {
        return ((TileNodeMonitor) p_149709_1_.getTileEntity(p_149709_2_, p_149709_3_, p_149709_4_)).activated ? 15 : 0;
    }

    public void killMe(final World world, final int x, final int y, final int z) {
        this.dropBlockAsItem(world, x, y, z, 0, 0);
        world.setBlockToAir(x, y, z);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int getRenderType() {
        return ThaumicHorizons.blockNodeMonRI;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("gold_block");
    }

    public IIcon getIcon(final int par1, final int par2) {
        return this.icon;
    }
}
