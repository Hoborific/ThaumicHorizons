// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import com.kentington.thaumichorizons.common.tiles.TileSoulforge;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockContainer;

public class BlockSoulforge extends BlockContainer
{
    public IIcon iconLiquid;
    public IIcon iconJarBottom;
    public IIcon iconJarSide;
    public IIcon iconJarTop;
    
    public BlockSoulforge() {
        super(Material.glass);
        this.setHardness(0.7f);
        this.setResistance(1.0f);
        this.setBlockName("ThaumicHorizons_soulforge");
        this.setBlockTextureName("ThaumicHorizons:soulforge");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }
    
    public TileEntity createTileEntity(final World world, final int metadata) {
        final TileSoulforge te = new TileSoulforge();
        return te;
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int p_149727_6_, final float p_149727_7_, final float p_149727_8_, final float p_149727_9_) {
        final TileEntity tile = world.getTileEntity(x, y, z);
        return tile instanceof TileSoulforge && ((TileSoulforge)tile).activate(world, player);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.iconJarSide = ir.registerIcon("thaumichorizons:jar_side");
        this.iconJarTop = ir.registerIcon("thaumichorizons:jar_top");
        this.iconJarBottom = ir.registerIcon("thaumichorizons:jar_bottom");
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
        return ThaumicHorizons.blockSoulforgeRI;
    }
}
