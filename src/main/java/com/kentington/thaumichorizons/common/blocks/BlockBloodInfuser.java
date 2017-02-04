// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import com.kentington.thaumichorizons.common.tiles.TileBloodInfuser;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockContainer;

public class BlockBloodInfuser extends BlockContainer
{
    IIcon icon;
    
    public BlockBloodInfuser() {
        super(Material.iron);
        this.setHardness(1.0f);
        this.setResistance(1.0f);
        this.setBlockName("ThaumicHorizons_bloodInfuser");
        this.setBlockTextureName("ThaumicHorizons:bloodInfuser");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }
    
    public TileEntity createTileEntity(final World world, final int metadata) {
        final TileBloodInfuser te = new TileBloodInfuser();
        return te;
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int p_149727_6_, final float p_149727_7_, final float p_149727_8_, final float p_149727_9_) {
        player.openGui((Object)ThaumicHorizons.instance, 5, world, x, y, z);
        return true;
    }
    
    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int md) {
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileBloodInfuser) {
            final TileBloodInfuser tile = (TileBloodInfuser)te;
            if (tile.syringe != null) {
                world.spawnEntityInWorld((Entity)new EntityItem(world, (double)x, (double)y, (double)z, tile.syringe));
            }
            for (int i = 0; i < 9; ++i) {
                if (tile.output[i] != null) {
                    world.spawnEntityInWorld((Entity)new EntityItem(world, (double)x, (double)y, (double)z, tile.output[i]));
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 0;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public int getRenderType() {
        return ThaumicHorizons.blockBloodInfuserRI;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("iron_block");
    }
    
    public IIcon getIcon(final int par1, final int par2) {
        return this.icon;
    }
}
