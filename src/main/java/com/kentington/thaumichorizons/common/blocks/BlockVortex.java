//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVortex;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.Config;

public class BlockVortex extends BlockContainer {

    IIcon icon;

    public BlockVortex() {
        super(Config.airyMaterial);
        this.setHardness(-1.0f);
        this.setResistance(20000.0f);
        this.setBlockName("ThaumicHorizons_vortex");
        this.setStepSound(new Block.SoundType("cloth", 0.0f, 1.0f));
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public float getBlockHardness(final World world, final int x, final int y, final int z) {
        return -1.0f;
    }

    public float getExplosionResistance(final Entity par1Entity, final World world, final int x, final int y,
            final int z, final double explosionX, final double explosionY, final double explosionZ) {
        return 20000.0f;
    }

    public int getLightValue(final IBlockAccess world, final int x, final int y, final int z) {
        return 15;
    }

    public void setBlockBoundsBasedOnState(final IBlockAccess ba, final int x, final int y, final int z) {
        this.setBlockBounds(0.3f, 0.3f, 0.3f, 0.7f, 0.7f, 0.7f);
    }

    public boolean getBlocksMovement(final IBlockAccess world, final int x, final int y, final int z) {
        return false;
    }

    public int getRenderType() {
        return ThaumicHorizons.blockVortexRI;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public Item getItemDropped(final int par1, final Random par2Random, final int par3) {
        return Item.getItemById(0);
    }

    public TileEntity createTileEntity(final World world, final int metadata) {
        return new TileVortex();
    }

    public TileEntity createNewTileEntity(final World var1, final int md) {
        return this.createTileEntity(var1, md);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        return null;
    }

    public void registerBlockIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:vortex");
    }

    public IIcon getIcon(final int par1, final int par2) {
        return this.icon;
    }

    public void onBlockPlacedBy(final World world, final int x, final int y, final int z, final EntityLivingBase entity,
            final ItemStack stack) {
        if (entity instanceof EntityPlayer) {
            final TileVortex tile = (TileVortex) world.getTileEntity(x, y, z);
            tile.aspects = new AspectList();
            for (int numAspects = world.rand.nextInt(4) + 1, a = 0; a < numAspects; ++a) {
                if (world.rand.nextInt(3) == 0) {
                    tile.aspects.add(
                            Aspect.getCompoundAspects().get(world.rand.nextInt(Aspect.getCompoundAspects().size())),
                            world.rand.nextInt(30));
                } else {
                    tile.aspects.add(
                            Aspect.getPrimalAspects().get(world.rand.nextInt(Aspect.getPrimalAspects().size())),
                            world.rand.nextInt(30));
                }
            }
        }
        if (stack.getItemDamage() == 1) {
            final TileVortex tile = (TileVortex) world.getTileEntity(x, y, z);
            tile.cheat = true;
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(final Item par1, final CreativeTabs par2CreativeTabs, final List par3List) {
        par3List.add(new ItemStack(this, 1, 0));
        par3List.add(new ItemStack(this, 1, 1));
    }

    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int md) {
        final TileVortex tco = (TileVortex) world.getTileEntity(x, y, z);
        MinecraftServer.getServer().worldServerForDimension(ThaumicHorizons.dimensionPocketId)
                .setBlockToAir(0, 129, tco.dimensionID * 256);
    }
}
