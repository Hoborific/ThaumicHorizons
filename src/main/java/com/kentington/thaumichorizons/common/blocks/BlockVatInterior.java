//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVatSlave;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVatInterior extends BlockContainer {
    public BlockVatInterior() {
        super(Material.water);
        this.setHardness(3.0f);
        this.setResistance(15.0f);
        this.lightValue = 8;
        this.setBlockName("ThaumicHorizons_vatInterior");
        this.setBlockTextureName("ThaumicHorizons:vatInterior");
    }

    public TileEntity createNewTileEntity(final World world, final int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(final World world, final int metadata) {
        return new TileVatSlave();
    }

    public void breakBlock(final World world, final int x, final int y, final int z, final Block block, final int md) {
        ((TileVatSlave) world.getTileEntity(x, y, z)).killMyBoss(md);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public int getRenderType() {
        return ThaumicHorizons.blockVatInteriorRI;
    }

    public boolean onBlockActivated(
            final World world,
            final int x,
            final int y,
            final int z,
            final EntityPlayer player,
            final int p_149727_6_,
            final float p_149727_7_,
            final float p_149727_8_,
            final float p_149727_9_) {
        return ((TileVatSlave) world.getTileEntity(x, y, z)).activate(player);
    }

    public void onEntityCollidedWithBlock(
            final World p_149670_1_,
            final int p_149670_2_,
            final int p_149670_3_,
            final int p_149670_4_,
            final Entity p_149670_5_) {
        p_149670_5_.setAir(300);
    }
}
