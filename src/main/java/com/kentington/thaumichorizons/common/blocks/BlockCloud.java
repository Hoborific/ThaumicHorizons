// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.entity.Entity;
import com.kentington.thaumichorizons.common.entities.EntityItemInvulnerable;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.items.wands.ItemWandCasting;
import net.minecraft.entity.player.EntityPlayer;
import com.kentington.thaumichorizons.common.tiles.TileCloud;
import net.minecraft.tileentity.TileEntity;
import java.util.Random;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockContainer;

public class BlockCloud extends BlockContainer
{
    public IIcon[] icon;
    public IIcon[] icontop;
    boolean glow;
    
    public BlockCloud(final boolean glowy) {
        super(Material.cloth);
        this.icon = new IIcon[10];
        this.icontop = new IIcon[10];
        this.setHardness(Float.MAX_VALUE);
        this.setResistance(Float.MAX_VALUE);
        this.setBlockName("ThaumicHorizons_cloud");
        this.setBlockTextureName("ThaumicHorizons:cloud");
        this.setCreativeTab(ThaumicHorizons.tabTH);
        if (glowy) {
            this.setLightLevel(1.0f);
        }
        this.glow = glowy;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.icon[0] = par1IconRegister.registerIcon("thaumichorizons:cloud");
        this.icon[1] = par1IconRegister.registerIcon("thaumichorizons:firecloud");
        this.icon[2] = par1IconRegister.registerIcon("thaumichorizons:thundercloud");
        this.icon[3] = par1IconRegister.registerIcon("thaumichorizons:acidcloud");
        this.icon[4] = par1IconRegister.registerIcon("thaumichorizons:alloycloud");
        this.icon[5] = par1IconRegister.registerIcon("thaumichorizons:fleshcloud");
        this.icon[6] = par1IconRegister.registerIcon("thaumichorizons:viscloud");
        this.icon[7] = par1IconRegister.registerIcon("thaumichorizons:glyphcloud");
        this.icon[8] = par1IconRegister.registerIcon("thaumichorizons:sporecloud");
        this.icon[9] = par1IconRegister.registerIcon("thaumichorizons:animuscloud");
        this.icontop[0] = par1IconRegister.registerIcon("thaumichorizons:cloudtop");
        this.icontop[1] = par1IconRegister.registerIcon("thaumichorizons:firecloudtop");
        this.icontop[2] = par1IconRegister.registerIcon("thaumichorizons:thundercloudtop");
        this.icontop[3] = par1IconRegister.registerIcon("thaumichorizons:acidcloudtop");
        this.icontop[4] = par1IconRegister.registerIcon("thaumichorizons:alloycloudtop");
        this.icontop[5] = par1IconRegister.registerIcon("thaumichorizons:fleshcloudtop");
        this.icontop[6] = par1IconRegister.registerIcon("thaumichorizons:viscloudtop");
        this.icontop[7] = par1IconRegister.registerIcon("thaumichorizons:glyphcloudtop");
        this.icontop[8] = par1IconRegister.registerIcon("thaumichorizons:sporecloudtop");
        this.icontop[9] = par1IconRegister.registerIcon("thaumichorizons:animuscloudtop");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int par1, final int par2) {
        if (par1 == 0 || par1 == 1) {
            return this.icontop[par2];
        }
        return this.icon[par2];
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
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World p_149734_1_, final int p_149734_2_, final int p_149734_3_, final int p_149734_4_, final Random p_149734_5_) {
    }
    
    private void func_150186_m(final World p_150186_1_, final int p_150186_2_, final int p_150186_3_, final int p_150186_4_) {
        final Random random = p_150186_1_.rand;
        final double d0 = 0.0625;
        for (int l = 0; l < 6; ++l) {
            double d2 = p_150186_2_ + random.nextFloat();
            double d3 = p_150186_3_ + random.nextFloat();
            double d4 = p_150186_4_ + random.nextFloat();
            if (l == 0 && !p_150186_1_.getBlock(p_150186_2_, p_150186_3_ + 1, p_150186_4_).isOpaqueCube()) {
                d3 = p_150186_3_ + 1 + d0;
            }
            if (l == 1 && !p_150186_1_.getBlock(p_150186_2_, p_150186_3_ - 1, p_150186_4_).isOpaqueCube()) {
                d3 = p_150186_3_ + 0 - d0;
            }
            if (l == 2 && !p_150186_1_.getBlock(p_150186_2_, p_150186_3_, p_150186_4_ + 1).isOpaqueCube()) {
                d4 = p_150186_4_ + 1 + d0;
            }
            if (l == 3 && !p_150186_1_.getBlock(p_150186_2_, p_150186_3_, p_150186_4_ - 1).isOpaqueCube()) {
                d4 = p_150186_4_ + 0 - d0;
            }
            if (l == 4 && !p_150186_1_.getBlock(p_150186_2_ + 1, p_150186_3_, p_150186_4_).isOpaqueCube()) {
                d2 = p_150186_2_ + 1 + d0;
            }
            if (l == 5 && !p_150186_1_.getBlock(p_150186_2_ - 1, p_150186_3_, p_150186_4_).isOpaqueCube()) {
                d2 = p_150186_2_ + 0 - d0;
            }
            if (random.nextInt(10) == 0 && (d2 < p_150186_2_ || d2 > p_150186_2_ + 1 || d3 < 0.0 || d3 > p_150186_3_ + 1 || d4 < p_150186_4_ || d4 > p_150186_4_ + 1)) {
                p_150186_1_.spawnParticle("cloud", d2, d3, d4, 0.0, 0.0, 0.0);
            }
        }
    }
    
    public TileEntity createNewTileEntity(final World p_149915_1_, final int p_149915_2_) {
        return new TileCloud();
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int p_149727_6_, final float p_149727_7_, final float p_149727_8_, final float p_149727_9_) {
        if (!world.isRemote && player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemWandCasting && ResearchManager.isResearchComplete(player.getCommandSenderName(), "planarClouds") && ((ItemWandCasting)player.getHeldItem().getItem()).consumeVis(player.getHeldItem(), player, Aspect.AIR, 100, false)) {
            world.spawnEntityInWorld((Entity)new EntityItemInvulnerable(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack((Block)this, 1, world.getBlockMetadata(x, y, z))));
            world.setBlockToAir(x, y, z);
            world.markBlockForUpdate(x, y, z);
            return true;
        }
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(final Item par1, final CreativeTabs par2CreativeTabs, final List par3List) {
        if (!this.glow) {
            par3List.add(new ItemStack((Block)this, 1, 0));
            par3List.add(new ItemStack((Block)this, 1, 2));
            par3List.add(new ItemStack((Block)this, 1, 3));
            par3List.add(new ItemStack((Block)this, 1, 5));
            par3List.add(new ItemStack((Block)this, 1, 8));
        }
        else {
            par3List.add(new ItemStack((Block)this, 1, 1));
            par3List.add(new ItemStack((Block)this, 1, 4));
            par3List.add(new ItemStack((Block)this, 1, 6));
            par3List.add(new ItemStack((Block)this, 1, 7));
            par3List.add(new ItemStack((Block)this, 1, 9));
        }
    }
    
    public int damageDropped(final int par1) {
        return par1;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(final IBlockAccess p_149646_1_, final int p_149646_2_, final int p_149646_3_, final int p_149646_4_, final int p_149646_5_) {
        final Block block = p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_);
        return block != this || p_149646_1_.getBlockMetadata(p_149646_2_, p_149646_3_, p_149646_4_) != p_149646_1_.getBlockMetadata(p_149646_2_ - Facing.offsetsXForSide[p_149646_5_], p_149646_3_ - Facing.offsetsYForSide[p_149646_5_], p_149646_4_ - Facing.offsetsZForSide[p_149646_5_]);
    }
    
    public boolean getBlocksMovement(final IBlockAccess world, final int x, final int y, final int z) {
        return false;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        return null;
    }
    
    public void onEntityCollidedWithBlock(final World p_149670_1_, final int p_149670_2_, final int p_149670_3_, final int p_149670_4_, final Entity ent) {
        final int md = p_149670_1_.getBlockMetadata(p_149670_2_, p_149670_3_, p_149670_4_);
        if (md == 1 || md == 4) {
            ent.setFire(6);
        }
        else if (md == 3) {
            ent.attackEntityFrom(DamageSourceThaumcraft.dissolve, 1.0f);
        }
    }
}
