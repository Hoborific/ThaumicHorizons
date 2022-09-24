//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.common.tiles.TileCloud;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import org.lwjgl.opengl.GL11;

public class TileCloudRender extends TileEntitySpecialRenderer {
    private Minecraft mc;
    Random random;
    private int rendererUpdateCount;
    private static final ResourceLocation locationRainPng;
    private static final ResourceLocation locationEmberPng;
    private static final ResourceLocation locationSnowPng;

    public TileCloudRender() {
        this.mc = Minecraft.getMinecraft();
        this.random = new Random();
    }

    public void renderTileEntityAt(
            final TileEntity tile, final double x, final double y, final double z, final float partial) {
        if (TileCloud.raining) {
            this.renderRainSnowToo((TileCloud) tile, x, y, z, partial);
            ++this.rendererUpdateCount;
        }
    }

    public void renderRainSnowToo(
            final TileCloud tco,
            final double p_147500_2_,
            final double p_147500_4_,
            final double p_147500_6_,
            final float p_147500_8_) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) p_147500_2_ + 0.5f, (float) p_147500_4_ + 1.5f, (float) p_147500_6_ + 0.5f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        GL11.glPopMatrix();
        if (tco.getWorldObj() == null) {
            return;
        }
        final float f1 = 1.0f;
        GL11.glAlphaFunc(516, 0.1f);
        if (f1 > 0.0f) {
            final Tessellator tessellator = Tessellator.instance;
            final BiomeGenBase biomegenbase = tco.getWorldObj().getBiomeGenForCoords(tco.xCoord, tco.zCoord);
            if (tco.md == 1) {
                this.bindTexture(TileCloudRender.locationEmberPng);
            } else if (biomegenbase.getFloatTemperature(tco.xCoord, tco.yCoord, tco.zCoord) >= 0.15) {
                this.bindTexture(TileCloudRender.locationRainPng);
            } else {
                this.bindTexture(TileCloudRender.locationSnowPng);
            }
            GL11.glTexParameterf(3553, 10242, 10497.0f);
            GL11.glTexParameterf(3553, 10243, 10497.0f);
            GL11.glDisable(2896);
            GL11.glDisable(2884);
            GL11.glDisable(3042);
            GL11.glDepthMask(true);
            OpenGlHelper.glBlendFunc(770, 1, 1, 0);
            final float f2 = tco.getWorldObj().getTotalWorldTime() + p_147500_8_ + tco.xCoord + 16 * tco.zCoord;
            final float f3 = -f2 * 0.2f - MathHelper.floor_float(-f2 * 0.1f);
            final byte b0 = 1;
            final double d3 = f2 * 0.025 * (1.0 - (b0 & 0x1) * 2.5);
            tessellator.startDrawingQuads();
            switch (tco.md) {
                case 1: {
                    tessellator.setColorRGBA(255, 255, 255, 255);
                    break;
                }
                case 3: {
                    tessellator.setColorRGBA(32, 255, 64, 255);
                    break;
                }
                case 4: {
                    tessellator.setColorRGBA(64, 64, 64, 255);
                    break;
                }
                case 5: {
                    tessellator.setColorRGBA(255, 64, 32, 255);
                    break;
                }
                case 6: {
                    tessellator.setColorRGBA(170, 64, 200, 255);
                    break;
                }
                case 7: {
                    tessellator.setColorRGBA(255, 255, 255, 255);
                    break;
                }
                case 8: {
                    tessellator.setColorRGBA(160, 255, 160, 255);
                    break;
                }
                case 9: {
                    tessellator.setColorRGBA(255, 230, 64, 255);
                    break;
                }
                default: {
                    tessellator.setColorRGBA(32, 64, 255, 255);
                    break;
                }
            }
            final double d4 = 0.0;
            final double d5 = 0.0;
            final double d6 = 1.0;
            final double d7 = 0.0;
            final double d8 = 0.0;
            final double d9 = 1.0;
            final double d10 = 1.0;
            final double d11 = 1.0;
            final double d12 = (tco.howManyDown != -1) ? (tco.howManyDown - 1) : ((double) (256.0f * f1));
            final double d13 = 0.0;
            final double d14 = 1.0;
            final double d15 = -1.0f + f3;
            final double d16 = tco.howManyDown / 4.0 + d15;
            tessellator.addVertexWithUV(p_147500_2_ + d4, p_147500_4_ - d12, p_147500_6_ + d5, d14, d16);
            tessellator.addVertexWithUV(p_147500_2_ + d4, p_147500_4_, p_147500_6_ + d5, d14, d15);
            tessellator.addVertexWithUV(p_147500_2_ + d6, p_147500_4_, p_147500_6_ + d7, d13, d15);
            tessellator.addVertexWithUV(p_147500_2_ + d6, p_147500_4_ - d12, p_147500_6_ + d7, d13, d16);
            tessellator.addVertexWithUV(p_147500_2_ + d10, p_147500_4_ - d12, p_147500_6_ + d11, d14, d16);
            tessellator.addVertexWithUV(p_147500_2_ + d10, p_147500_4_, p_147500_6_ + d11, d14, d15);
            tessellator.addVertexWithUV(p_147500_2_ + d8, p_147500_4_, p_147500_6_ + d9, d13, d15);
            tessellator.addVertexWithUV(p_147500_2_ + d8, p_147500_4_ - d12, p_147500_6_ + d9, d13, d16);
            tessellator.addVertexWithUV(p_147500_2_ + d6, p_147500_4_ - d12, p_147500_6_ + d7, d14, d16);
            tessellator.addVertexWithUV(p_147500_2_ + d6, p_147500_4_, p_147500_6_ + d7, d14, d15);
            tessellator.addVertexWithUV(p_147500_2_ + d10, p_147500_4_, p_147500_6_ + d11, d13, d15);
            tessellator.addVertexWithUV(p_147500_2_ + d10, p_147500_4_ - d12, p_147500_6_ + d11, d13, d16);
            tessellator.addVertexWithUV(p_147500_2_ + d8, p_147500_4_ - d12, p_147500_6_ + d9, d14, d16);
            tessellator.addVertexWithUV(p_147500_2_ + d8, p_147500_4_, p_147500_6_ + d9, d14, d15);
            tessellator.addVertexWithUV(p_147500_2_ + d4, p_147500_4_, p_147500_6_ + d5, d13, d15);
            tessellator.addVertexWithUV(p_147500_2_ + d4, p_147500_4_ - d12, p_147500_6_ + d5, d13, d16);
            tessellator.draw();
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glDepthMask(true);
        }
    }

    static {
        locationRainPng = new ResourceLocation("thaumichorizons", "textures/environment/rain.png");
        locationEmberPng = new ResourceLocation("thaumichorizons", "textures/environment/firerain.png");
        locationSnowPng = new ResourceLocation("textures/environment/snow.png");
    }
}
