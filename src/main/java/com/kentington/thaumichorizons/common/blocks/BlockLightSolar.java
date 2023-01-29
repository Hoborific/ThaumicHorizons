//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileLight;

public class BlockLightSolar extends BlockLight {

    public void onEntityCollidedWithBlock(final World p_149670_1_, final int p_149670_2_, final int p_149670_3_,
            final int p_149670_4_, final Entity p_149670_5_) {
        if (p_149670_5_ instanceof EntityLivingBase) {
            final EntityLivingBase critter = (EntityLivingBase) p_149670_5_;
            if (critter.isEntityUndead()) {
                critter.setFire(5);
            }
        }
    }

    @Override
    public int getRenderType() {
        return ThaumicHorizons.blockLightSolarRI;
    }

    @Override
    public TileEntity createTileEntity(final World world, final int metadata) {
        return new TileLight();
    }
}
