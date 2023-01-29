//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLeviathan extends Block {

    public BlockLeviathan() {
        super(Material.rock);
        this.setHardness(10.0f);
        this.setResistance(10.0f);
        this.setBlockName("ThaumicHorizons_leviathan");
        this.setBlockTextureName("ThaumicHorizons:leviathan");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World p_149734_1_, final int p_149734_2_, final int p_149734_3_,
            final int p_149734_4_, final Random p_149734_5_) {
        this.func_150186_m(p_149734_1_, p_149734_2_, p_149734_3_, p_149734_4_);
    }

    private void func_150186_m(final World p_150186_1_, final int p_150186_2_, final int p_150186_3_,
            final int p_150186_4_) {
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
            if (random.nextInt(10) == 0 && (d2 < p_150186_2_ || d2 > p_150186_2_ + 1
                    || d3 < 0.0
                    || d3 > p_150186_3_ + 1
                    || d4 < p_150186_4_
                    || d4 > p_150186_4_ + 1)) {
                p_150186_1_.spawnParticle("smoke", d2, d3, d4, 0.0, 0.0, 0.0);
            }
        }
    }
}
