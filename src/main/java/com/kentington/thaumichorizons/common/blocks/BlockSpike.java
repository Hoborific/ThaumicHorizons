// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.util.AxisAlignedBB;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.ForgeDirection;
import com.kentington.thaumichorizons.common.tiles.TileSpike;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockContainer;

public class BlockSpike extends BlockContainer
{
    int type;
    IIcon icon;
    IIcon iconWood;
    IIcon iconTooth;
    
    public BlockSpike(final int spikeType, final Material mat, final String name) {
        super(mat);
        this.type = spikeType;
        this.setHardness(3.0f);
        this.setResistance(3.0f);
        this.setBlockName("ThaumicHorizons_" + name);
        this.setBlockTextureName("ThaumicHorizons:spike");
        this.setCreativeTab(ThaumicHorizons.tabTH);
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
        return ThaumicHorizons.blockSpikeRI;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("iron_block");
        this.iconWood = ir.registerIcon("thaumcraft:greatwoodtop");
        this.iconTooth = ir.registerIcon("thaumichorizons:bone");
    }
    
    public IIcon getIcon(final int par1, final int par2) {
        switch (this.type) {
            case 1: {
                return this.iconWood;
            }
            case 2: {
                return this.iconTooth;
            }
            default: {
                return this.icon;
            }
        }
    }
    
    public void onEntityCollidedWithBlock(final World p_149670_1_, final int p_149670_2_, final int p_149670_3_, final int p_149670_4_, final Entity p_149670_5_) {
        p_149670_5_.attackEntityFrom(DamageSource.cactus, 1.0f);
    }
    
    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }
    
    public TileEntity createTileEntity(final World world, final int metadata) {
        final TileSpike spike = new TileSpike((byte)metadata, (byte)this.type);
        return spike;
    }
    
    public boolean canPlaceBlockOnSide(final World p_149742_1_, final int p_149742_2_, final int p_149742_3_, final int p_149742_4_, final int p_149742_5_) {
        final ForgeDirection dir = ForgeDirection.getOrientation(p_149742_5_);
        return (dir == ForgeDirection.DOWN && p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ + 1, p_149742_4_, ForgeDirection.DOWN)) || (dir == ForgeDirection.UP && p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ - 1, p_149742_4_, ForgeDirection.UP)) || (dir == ForgeDirection.NORTH && p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, ForgeDirection.NORTH)) || (dir == ForgeDirection.SOUTH && p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, ForgeDirection.SOUTH)) || (dir == ForgeDirection.WEST && p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, ForgeDirection.WEST)) || (dir == ForgeDirection.EAST && p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, ForgeDirection.EAST));
    }
    
    public boolean canPlaceBlockAt(final World p_149742_1_, final int p_149742_2_, final int p_149742_3_, final int p_149742_4_) {
        return p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, ForgeDirection.EAST) || p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, ForgeDirection.WEST) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, ForgeDirection.SOUTH) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, ForgeDirection.NORTH) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ - 1, p_149742_4_, ForgeDirection.UP) || p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ + 1, p_149742_4_, ForgeDirection.DOWN);
    }
    
    public int onBlockPlaced(final World p_149660_1_, final int p_149660_2_, final int p_149660_3_, final int p_149660_4_, final int p_149660_5_, final float p_149660_6_, final float p_149660_7_, final float p_149660_8_, final int p_149660_9_) {
        byte b0 = -1;
        if (p_149660_5_ == 0 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_ + 1, p_149660_4_, ForgeDirection.DOWN)) {
            b0 = 0;
        }
        if (p_149660_5_ == 1 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_ - 1, p_149660_4_, ForgeDirection.UP)) {
            b0 = 1;
        }
        if (p_149660_5_ == 2 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ + 1, ForgeDirection.NORTH)) {
            b0 = 2;
        }
        if (p_149660_5_ == 3 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ - 1, ForgeDirection.SOUTH)) {
            b0 = 3;
        }
        if (p_149660_5_ == 4 && p_149660_1_.isSideSolid(p_149660_2_ + 1, p_149660_3_, p_149660_4_, ForgeDirection.WEST)) {
            b0 = 4;
        }
        if (p_149660_5_ == 5 && p_149660_1_.isSideSolid(p_149660_2_ - 1, p_149660_3_, p_149660_4_, ForgeDirection.EAST)) {
            b0 = 5;
        }
        return b0;
    }
    
    public void onNeighborBlockChange(final World p_149695_1_, final int p_149695_2_, final int p_149695_3_, final int p_149695_4_, final Block p_149695_5_) {
        this.func_149820_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
    }
    
    private void func_149820_e(final World p_149820_1_, final int p_149820_2_, final int p_149820_3_, final int p_149820_4_) {
        if (!this.canPlaceBlockOnSide(p_149820_1_, p_149820_2_, p_149820_3_, p_149820_4_, p_149820_1_.getBlockMetadata(p_149820_2_, p_149820_3_, p_149820_4_))) {
            this.dropBlockAsItem(p_149820_1_, p_149820_2_, p_149820_3_, p_149820_4_, p_149820_1_.getBlockMetadata(p_149820_2_, p_149820_3_, p_149820_4_), 0);
            p_149820_1_.setBlockToAir(p_149820_2_, p_149820_3_, p_149820_4_);
        }
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_, final int p_149668_3_, final int p_149668_4_) {
        final float f = 0.0625f;
        final int md = p_149668_1_.getBlockMetadata(p_149668_2_, p_149668_3_, p_149668_4_);
        if (md == 0) {
            return AxisAlignedBB.getBoundingBox((double)p_149668_2_, (double)(p_149668_3_ + f), (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
        }
        if (md == 1) {
            return AxisAlignedBB.getBoundingBox((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1 - f), (double)(p_149668_4_ + 1));
        }
        if (md == 4) {
            return AxisAlignedBB.getBoundingBox((double)(p_149668_2_ + f), (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
        }
        if (md == 5) {
            return AxisAlignedBB.getBoundingBox((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1 - f), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
        }
        if (md == 2) {
            return AxisAlignedBB.getBoundingBox((double)p_149668_2_, (double)p_149668_3_, (double)(p_149668_4_ + f), (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1));
        }
        if (md == 3) {
            return AxisAlignedBB.getBoundingBox((double)p_149668_2_, (double)p_149668_3_, (double)p_149668_4_, (double)(p_149668_2_ + 1), (double)(p_149668_3_ + 1), (double)(p_149668_4_ + 1 - f));
        }
        return AxisAlignedBB.getBoundingBox((double)(p_149668_2_ + f), (double)p_149668_3_, (double)(p_149668_4_ + f), (double)(p_149668_2_ + 1 - f), (double)(p_149668_3_ + 1 - f), (double)(p_149668_4_ + 1 - f));
    }
}
