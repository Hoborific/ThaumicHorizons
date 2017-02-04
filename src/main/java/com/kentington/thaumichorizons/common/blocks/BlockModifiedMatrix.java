// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import com.kentington.thaumichorizons.common.tiles.TileVatMatrix;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockContainer;

public class BlockModifiedMatrix extends BlockContainer
{
    IIcon icon;
    
    public BlockModifiedMatrix() {
        super(Material.rock);
        this.setHardness(2.0f);
        this.setResistance(2.0f);
        this.setBlockName("ThaumicHorizons_modMatrix");
        this.setBlockTextureName("ThaumicHorizons:modMatrix");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }
    
    public TileEntity createTileEntity(final World world, final int metadata) {
        final TileVatMatrix te = new TileVatMatrix();
        return te;
    }
    
    public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return world.getTileEntity(x, y - 1, z) instanceof TileVat;
    }
    
    public void breakBlock(final World p_149749_1_, final int p_149749_2_, final int p_149749_3_, final int p_149749_4_, final Block p_149749_5_, final int p_149749_6_) {
        final TileVat tile = (TileVat)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_ - 1, p_149749_4_);
        if (tile != null && tile.mode == 2) {
            tile.killSubject();
        }
        super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
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
        return ThaumicHorizons.blockVatMatrixRI;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumcraft:arcane_stone");
    }
    
    public IIcon getIcon(final int par1, final int par2) {
        return this.icon;
    }
}
