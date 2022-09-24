//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileLight;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.config.Config;

public class BlockLight extends BlockContainer {
    public IIcon blankIcon;

    public BlockLight() {
        super(Config.airyMaterial);
        this.setBlockName("ThaumicHorizons_light");
        this.setStepSound(new Block.SoundType("cloth", 0.0f, 1.0f));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.blankIcon = ir.registerIcon("thaumcraft:blank");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return this.blankIcon;
    }

    public float getBlockHardness(final World world, final int x, final int y, final int z) {
        return 0.0f;
    }

    public float getExplosionResistance(
            final Entity par1Entity,
            final World world,
            final int x,
            final int y,
            final int z,
            final double explosionX,
            final double explosionY,
            final double explosionZ) {
        return 0.0f;
    }

    public int getLightValue(final IBlockAccess world, final int x, final int y, final int z) {
        return 14;
    }

    public void setBlockBoundsBasedOnState(final IBlockAccess ba, final int x, final int y, final int z) {
        this.setBlockBounds(0.3f, 0.3f, 0.3f, 0.7f, 0.7f, 0.7f);
    }

    public boolean getBlocksMovement(final IBlockAccess world, final int x, final int y, final int z) {
        return false;
    }

    public int getRenderType() {
        return ThaumicHorizons.blockLightRI;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public Item getItemDropped(final int par1, final Random par2Random, final int par3) {
        return Item.getItemById(0);
    }

    public TileEntity createTileEntity(final World world, final int metadata) {
        return new TileLight();
    }

    public TileEntity createNewTileEntity(final World var1, final int md) {
        return new TileLight();
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        return null;
    }
}
