// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import com.kentington.thaumichorizons.common.tiles.TileEssentiaDynamo;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.block.BlockContainer;

public class BlockEssentiaDynamo extends BlockContainer
{
    IIcon icon;
    
    public BlockEssentiaDynamo(final Material p_i45386_1_) {
        super(p_i45386_1_);
        this.setHardness(0.7f);
        this.setResistance(1.0f);
        this.setLightLevel(0.5f);
        this.setBlockName("ThaumicHorizons_essentiaDynamo");
        this.setBlockTextureName("ThaumicHorizons:essentiaDynamo");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    public TileEntity createNewTileEntity(final World p_149915_1_, final int p_149915_2_) {
        return this.createTileEntity(p_149915_1_, p_149915_2_);
    }
    
    public TileEntity createTileEntity(final World world, final int metadata) {
        return new TileEssentiaDynamo();
    }
    
    public void breakBlock(final World p_149749_1_, final int p_149749_2_, final int p_149749_3_, final int p_149749_4_, final Block p_149749_5_, final int p_149749_6_) {
        ((TileEssentiaDynamo)p_149749_1_.getTileEntity(p_149749_2_, p_149749_3_, p_149749_4_)).killMe();
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
        return ThaumicHorizons.blockEssentiaDynamoRI;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumcraft:thaumiumblock");
    }
    
    public IIcon getIcon(final int par1, final int par2) {
        return this.icon;
    }
}
