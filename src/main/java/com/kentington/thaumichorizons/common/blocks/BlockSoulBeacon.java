// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import com.kentington.thaumichorizons.common.tiles.TileSoulBeacon;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockContainer;

public class BlockSoulBeacon extends BlockContainer
{
    IIcon icon;
    
    public BlockSoulBeacon() {
        super(Material.iron);
        this.setHardness(7.0f);
        this.setResistance(7.0f);
        this.setBlockName("ThaumicHorizons_soulBeacon");
        this.setBlockTextureName("ThaumicHorizons:soulBeacon");
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setLightLevel(1.0f);
    }
    
    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }
    
    public TileEntity createTileEntity(final World world, final int metadata) {
        final TileSoulBeacon te = new TileSoulBeacon();
        return te;
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int p_149727_6_, final float p_149727_7_, final float p_149727_8_, final float p_149727_9_) {
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileSoulBeacon) {
            final TileSoulBeacon tile = (TileSoulBeacon)te;
            return tile.activate(player);
        }
        return false;
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
        return ThaumicHorizons.blockSoulBeaconRI;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("endframe_eye");
    }
    
    public IIcon getIcon(final int par1, final int par2) {
        return this.icon;
    }
}
