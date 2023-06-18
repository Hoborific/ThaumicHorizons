//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockVat;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import com.kentington.thaumichorizons.common.tiles.TileVatSlave;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockVatRender implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(final Block block, final int metadata, final int modelID,
            final RenderBlocks renderer) {}

    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block,
            final int modelId, final RenderBlocks renderer) {
        if (world.getTileEntity(x, y, z) instanceof TileVatSlave) {
            final TileVatSlave tco = (TileVatSlave) world.getTileEntity(x, y, z);
            final TileVat boss = tco.getBoss(-1);
            if (boss == null) {
                return false;
            }
            Tessellator.instance.setColorRGBA(255, 255, 255, 192);
            Tessellator.instance.setBrightness(241);
            renderer.enableAO = false;
            final int dx = boss.xCoord - tco.xCoord;
            final int dy = boss.yCoord - tco.yCoord;
            final int dz = boss.zCoord - tco.zCoord;
            if (world.getBlockMetadata(x, y, z) == 10) {
                if (dy == 1) {
                    if (dx == -1 && dz == -1) {
                        renderer.renderFaceXPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassTL);
                        renderer.renderFaceZPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassTR);
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 0.75f);
                        renderer.setRenderBoundsFromBlock(block);
                        Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                        renderer.renderFaceXPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        renderer.renderFaceZPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                    } else if (dx == 1 && dz == -1) {
                        renderer.renderFaceXNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassTR);
                        renderer.renderFaceZPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassTL);
                        block.setBlockBounds(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f);
                        renderer.setRenderBoundsFromBlock(block);
                        Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                        renderer.renderFaceXNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        renderer.renderFaceZPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                    } else if (dx == 1 && dz == 1) {
                        renderer.renderFaceXNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassTL);
                        renderer.renderFaceZNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassTR);
                        block.setBlockBounds(0.25f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                        Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                        renderer.renderFaceXNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        renderer.renderFaceZNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                    } else if (dx == -1 && dz == 1) {
                        renderer.renderFaceXPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassTR);
                        renderer.renderFaceZNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassTL);
                        block.setBlockBounds(0.0f, 0.0f, 0.25f, 0.75f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                        Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                        renderer.renderFaceXPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        renderer.renderFaceZNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                    } else if (dx == 0) {
                        if (dz == -1) {
                            renderer.renderFaceZPos(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    ((BlockVat) ThaumicHorizons.blockVat).iconGlassT);
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f);
                            renderer.setRenderBoundsFromBlock(block);
                            Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                            renderer.renderFaceZPos(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    Blocks.water.getBlockTextureFromSide(0));
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                        } else if (dz == 1) {
                            renderer.renderFaceZNeg(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    ((BlockVat) ThaumicHorizons.blockVat).iconGlassT);
                            block.setBlockBounds(0.0f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                            Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                            renderer.renderFaceZNeg(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    Blocks.water.getBlockTextureFromSide(0));
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                        }
                    } else if (dz == 0) {
                        if (dx == -1) {
                            renderer.renderFaceXPos(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    ((BlockVat) ThaumicHorizons.blockVat).iconGlassT);
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                            Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                            renderer.renderFaceXPos(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    Blocks.water.getBlockTextureFromSide(0));
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                        } else if (dx == 1) {
                            renderer.renderFaceXNeg(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    ((BlockVat) ThaumicHorizons.blockVat).iconGlassT);
                            block.setBlockBounds(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                            Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                            renderer.renderFaceXNeg(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    Blocks.water.getBlockTextureFromSide(0));
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                        }
                    }
                } else if (dy == 2) {
                    if (dx == -1 && dz == -1) {
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                        renderer.renderFaceXPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassBL);
                        renderer.renderFaceZPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassBR);
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 0.75f);
                        renderer.setRenderBoundsFromBlock(block);
                        Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                        renderer.renderFaceXPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        renderer.renderFaceZPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                    } else if (dx == 1 && dz == -1) {
                        renderer.renderFaceXNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassBR);
                        renderer.renderFaceZPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassBL);
                        block.setBlockBounds(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f);
                        renderer.setRenderBoundsFromBlock(block);
                        Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                        renderer.renderFaceXNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        renderer.renderFaceZPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                    } else if (dx == 1 && dz == 1) {
                        renderer.renderFaceXNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassBL);
                        renderer.renderFaceZNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassBR);
                        block.setBlockBounds(0.25f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                        Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                        renderer.renderFaceXNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        renderer.renderFaceZNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                    } else if (dx == -1 && dz == 1) {
                        renderer.renderFaceXPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassBR);
                        renderer.renderFaceZNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                ((BlockVat) ThaumicHorizons.blockVat).iconGlassBL);
                        block.setBlockBounds(0.0f, 0.0f, 0.25f, 0.75f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                        Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                        renderer.renderFaceXPos(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        renderer.renderFaceZNeg(
                                block,
                                (double) x,
                                (double) y,
                                (double) z,
                                Blocks.water.getBlockTextureFromSide(0));
                        block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.setRenderBoundsFromBlock(block);
                    } else if (dx == 0) {
                        if (dz == -1) {
                            renderer.renderFaceZPos(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    ((BlockVat) ThaumicHorizons.blockVat).iconGlassB);
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f);
                            renderer.setRenderBoundsFromBlock(block);
                            Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                            renderer.renderFaceZPos(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    Blocks.water.getBlockTextureFromSide(0));
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                        } else if (dz == 1) {
                            renderer.renderFaceZNeg(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    ((BlockVat) ThaumicHorizons.blockVat).iconGlassB);
                            block.setBlockBounds(0.0f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                            Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                            renderer.renderFaceZNeg(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    Blocks.water.getBlockTextureFromSide(0));
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                        }
                    } else if (dz == 0) {
                        if (dx == -1) {
                            renderer.renderFaceXPos(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    ((BlockVat) ThaumicHorizons.blockVat).iconGlassB);
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                            Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                            renderer.renderFaceXPos(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    Blocks.water.getBlockTextureFromSide(0));
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                        } else if (dx == 1) {
                            renderer.renderFaceXNeg(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    ((BlockVat) ThaumicHorizons.blockVat).iconGlassB);
                            block.setBlockBounds(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                            Tessellator.instance.setColorRGBA(255, 255, 255, 255);
                            renderer.renderFaceXNeg(
                                    block,
                                    (double) x,
                                    (double) y,
                                    (double) z,
                                    Blocks.water.getBlockTextureFromSide(0));
                            block.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.setRenderBoundsFromBlock(block);
                        }
                    }
                }
                return renderer.enableAO = true;
            }
        }
        renderer.enableAO = true;
        return false;
    }

    public boolean shouldRender3DInInventory(final int modelId) {
        return false;
    }

    public int getRenderId() {
        return ThaumicHorizons.blockVatRI;
    }
}
