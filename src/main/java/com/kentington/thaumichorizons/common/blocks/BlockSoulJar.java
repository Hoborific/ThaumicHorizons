//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSoulJar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSoulJar extends BlockContainer {
    public IIcon iconJarBottom;
    public IIcon iconJarSide;
    public IIcon iconJarTop;

    public BlockSoulJar() {
        super(Material.glass);
        this.setHardness(0.3f);
        this.setResistance(1.0f);
        this.setBlockName("ThaumicHorizons_soulJar");
        this.setBlockTextureName("ThaumicHorizons:soulJar");
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setLightLevel(0.66f);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.iconJarSide = ir.registerIcon("thaumcraft:jar_side");
        this.iconJarTop = ir.registerIcon("thaumcraft:jar_top");
        this.iconJarBottom = ir.registerIcon("thaumcraft:jar_bottom");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return (side == 1) ? this.iconJarTop : ((side == 0) ? this.iconJarBottom : this.iconJarSide);
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return ThaumicHorizons.blockJarRI;
    }

    public void setBlockBoundsBasedOnState(final IBlockAccess world, final int i, final int j, final int k) {
        this.setBlockBounds(0.1875f, 0.0f, 0.1875f, 0.8125f, 0.75f, 0.8125f);
        super.setBlockBoundsBasedOnState(world, i, j, k);
    }

    public void addCollisionBoxesToList(
            final World world,
            final int i,
            final int j,
            final int k,
            final AxisAlignedBB axisalignedbb,
            final List arraylist,
            final Entity par7Entity) {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
    }

    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(final World world, final int metadata) {
        final TileSoulJar te = new TileSoulJar();
        return te;
    }

    public void onBlockHarvested(
            final World par1World,
            final int par2,
            final int par3,
            final int par4,
            final int par5,
            final EntityPlayer par6EntityPlayer) {
        this.dropBlockAsItem(par1World, par2, par3, par4, par5, 0);
        super.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    public ArrayList<ItemStack> getDrops(
            final World world, final int x, final int y, final int z, final int metadata, final int fortune) {
        final ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te != null && te instanceof TileSoulJar) {
            final ItemStack drop = new ItemStack((Block) this);
            drop.setTagCompound(((TileSoulJar) te).jarTag);
            drops.add(drop);
        }
        return drops;
    }
}
