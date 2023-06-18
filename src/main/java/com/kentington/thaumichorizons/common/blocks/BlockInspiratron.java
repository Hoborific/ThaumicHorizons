//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileInspiratron;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockInspiratron extends BlockContainer {

    public IIcon iconLiquid;
    public IIcon iconJarBottom;
    public IIcon iconJarSide;
    public IIcon iconJarTop;

    public BlockInspiratron() {
        super(Material.glass);
        this.setHardness(0.7f);
        this.setResistance(1.0f);
        this.setBlockName("ThaumicHorizons_inspiratron");
        this.setBlockTextureName("ThaumicHorizons:inspiratron");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(final World world, final int metadata) {
        return new TileInspiratron();
    }

    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player,
            final int p_149727_6_, final float p_149727_7_, final float p_149727_8_, final float p_149727_9_) {
        player.openGui(ThaumicHorizons.instance, 3, world, x, y, z);
        return true;
    }

    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int md) {
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof final TileInspiratron tile) {
            if (tile.paper != null) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, tile.paper));
            }
            if (tile.knowledge != null) {
                world.spawnEntityInWorld(new EntityItem(world, x, y, z, tile.knowledge));
            }
        }
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
        return ThaumicHorizons.blockInspiratronRI;
    }
}
