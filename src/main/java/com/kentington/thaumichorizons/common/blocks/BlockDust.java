//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

public class BlockDust extends Block {

    public BlockDust() {
        super(Material.sand);
        this.setHardness(0.5f);
        this.setStepSound(BlockDust.soundTypeSand);
        this.setBlockName("ThaumicHorizons_dust");
        this.setBlockTextureName("ThaumicHorizons:dust");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World p_149668_1_, final int p_149668_2_,
            final int p_149668_3_, final int p_149668_4_) {
        final float f = 0.125f;
        return AxisAlignedBB.getBoundingBox(
                (double) p_149668_2_,
                (double) p_149668_3_,
                (double) p_149668_4_,
                (double) (p_149668_2_ + 1),
                (double) (p_149668_3_ + 1 - f),
                (double) (p_149668_4_ + 1));
    }

    public void onEntityCollidedWithBlock(final World p_149670_1_, final int p_149670_2_, final int p_149670_3_,
            final int p_149670_4_, final Entity p_149670_5_) {
        p_149670_5_.motionX *= 0.4;
        p_149670_5_.motionZ *= 0.4;
    }
}
