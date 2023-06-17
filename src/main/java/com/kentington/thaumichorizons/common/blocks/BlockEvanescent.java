//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEvanescent extends Block {

    public BlockEvanescent() {
        super(Material.glass);
        this.setHardness(Float.MAX_VALUE);
        this.setResistance(Float.MAX_VALUE);
        this.setBlockName("ThaumicHorizons_evanescent");
        this.setBlockTextureName("ThaumicHorizons:evanescent");
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
    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int md) {
        ThaumicHorizons.instance.renderEventHandler.resetBlocks(Minecraft.getMinecraft().thePlayer);
    }

    public boolean canPlaceBlockAt(final World p_149742_1_, final int p_149742_2_, final int p_149742_3_,
            final int p_149742_4_) {
        return false;
    }
}
