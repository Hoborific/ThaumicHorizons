//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileTransductionAmplifier;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.tiles.TileNodeEnergized;

public class BlockTransductionAmplifier extends BlockContainer {

    IIcon icon;

    public BlockTransductionAmplifier() {
        super(Material.rock);
        this.setBlockName("ThaumicHorizons_transductionAmplifier");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(final World world, final int metadata) {
        return new TileTransductionAmplifier();
    }

    public boolean canPlaceBlockAt(final World p_149742_1_, final int p_149742_2_, final int p_149742_3_,
            final int p_149742_4_) {
        return p_149742_1_.getTileEntity(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized
                || p_149742_1_.getTileEntity(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized
                || p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof TileNodeEnergized
                || p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof TileNodeEnergized
                || p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof TileNodeEnergized
                || p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof TileNodeEnergized;
    }

    public boolean canPlaceBlockOnSide(final World p_149742_1_, final int p_149742_2_, final int p_149742_3_,
            final int p_149742_4_, final int p_149742_5_) {
        final ForgeDirection dir = ForgeDirection.getOrientation(p_149742_5_);
        return (dir == ForgeDirection.NORTH
                && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof TileNodeEnergized)
                || (dir == ForgeDirection.SOUTH && p_149742_1_
                        .getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof TileNodeEnergized)
                || (dir == ForgeDirection.WEST && p_149742_1_
                        .getTileEntity(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized)
                || (dir == ForgeDirection.EAST && p_149742_1_
                        .getTileEntity(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized);
    }

    public int onBlockPlaced(final World p_149742_1_, final int p_149742_2_, final int p_149742_3_,
            final int p_149742_4_, final int p_149660_5_, final float p_149660_6_, final float p_149660_7_,
            final float p_149660_8_, final int p_149660_9_) {
        if (p_149660_5_ == 0
                && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ + 1, p_149742_4_) instanceof TileNodeEnergized) {
            return 0;
        }
        if (p_149660_5_ == 1
                && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_ - 1, p_149742_4_) instanceof TileNodeEnergized) {
            return 1;
        }
        if (p_149660_5_ == 2
                && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ + 1) instanceof TileNodeEnergized) {
            return 2;
        }
        if (p_149660_5_ == 3
                && p_149742_1_.getTileEntity(p_149742_2_, p_149742_3_, p_149742_4_ - 1) instanceof TileNodeEnergized) {
            return 3;
        }
        if (p_149660_5_ == 4
                && p_149742_1_.getTileEntity(p_149742_2_ + 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized) {
            return 4;
        }
        if (p_149660_5_ == 5
                && p_149742_1_.getTileEntity(p_149742_2_ - 1, p_149742_3_, p_149742_4_) instanceof TileNodeEnergized) {
            return 5;
        }
        return -1;
    }

    public void killMe(final World world, final int x, final int y, final int z, final boolean drop) {
        if (((TileTransductionAmplifier) world.getTileEntity(x, y, z)).activated) {
            ((TileTransductionAmplifier) world.getTileEntity(x, y, z)).unBoostNode(x, y, z);
        }
        if (drop) {
            this.dropBlockAsItem(world, x, y, z, 0, 0);
        }
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
        return ThaumicHorizons.blockTransducerRI;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("iron_block");
    }

    public IIcon getIcon(final int par1, final int par2) {
        return this.icon;
    }

    public void onBlockPreDestroy(final World world, final int x, final int y, final int z, final int md) {
        this.killMe(world, x, y, z, false);
    }

    public void onNeighborBlockChange(final World world, final int x, final int y, final int z, final Block nbid) {
        final TileTransductionAmplifier tile = (TileTransductionAmplifier) world.getTileEntity(x, y, z);
        if (tile.activated && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
            tile.shouldActivate = false;
        } else if (!tile.activated && world.isBlockIndirectlyGettingPowered(x, y, z)) {
            tile.shouldActivate = true;
        }
    }

    public boolean isSideSolid(final IBlockAccess world, final int x, final int y, final int z,
            final ForgeDirection side) {
        switch (world.getBlockMetadata(x, y, z)) {
            case 0 -> {
                return side == ForgeDirection.DOWN;
            }
            case 1 -> {
                return side == ForgeDirection.UP;
            }
            case 2 -> {
                return side == ForgeDirection.NORTH;
            }
            case 3 -> {
                return side == ForgeDirection.SOUTH;
            }
            case 4 -> {
                return side == ForgeDirection.WEST;
            }
            case 5 -> {
                return side == ForgeDirection.EAST;
            }
            default -> {
                return false;
            }
        }
    }
}
