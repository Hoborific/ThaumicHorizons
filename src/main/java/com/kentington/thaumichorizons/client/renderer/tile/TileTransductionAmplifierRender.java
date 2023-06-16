//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.kentington.thaumichorizons.common.tiles.TileTransductionAmplifier;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.client.lib.UtilsFX;

@SideOnly(Side.CLIENT)
public class TileTransductionAmplifierRender extends TileEntitySpecialRenderer {

    private final IModelCustom model;
    private static final ResourceLocation MODEL;

    public TileTransductionAmplifierRender() {
        this.model = AdvancedModelLoader.loadModel(TileTransductionAmplifierRender.MODEL);
    }

    public void renderTileEntityAt(final TileTransductionAmplifier tile, final double par2, final double par4,
            final double par6, final float par8) {
        int bright = 20;
        GL11.glPushMatrix();
        if (tile.getWorldObj() != null) {
            bright = tile.getBlockType().getMixedBrightnessForBlock(
                    (IBlockAccess) tile.getWorldObj(),
                    tile.xCoord,
                    tile.yCoord,
                    tile.zCoord);
            tile.direction = (byte) tile.getBlockMetadata();
            if (tile.direction == 3) {
                GL11.glTranslatef((float) par2 + 0.5f, (float) par4 + 0.5f, (float) par6 + 1.0f);
                GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
            } else if (tile.direction == 4) {
                GL11.glTranslatef((float) par2, (float) par4 + 0.5f, (float) par6 + 0.5f);
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
            } else if (tile.direction == 5) {
                GL11.glTranslatef((float) par2 + 1.0f, (float) par4 + 0.5f, (float) par6 + 0.5f);
                GL11.glRotatef(270.0f, 0.0f, 1.0f, 0.0f);
            } else if (tile.direction == 2) {
                GL11.glTranslatef((float) par2 + 0.5f, (float) par4 + 0.5f, (float) par6);
                GL11.glRotatef(0.0f, 0.0f, 1.0f, 0.0f);
            }
        } else {
            GL11.glTranslatef((float) par2 + 0.75f, (float) par4 + 0.25f, (float) par6);
        }
        UtilsFX.bindTexture("textures/models/node_converter.png");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        final float v = Math.min(50, tile.count) / 137.0f;
        this.model.renderPart("lock");
        GL11.glColor4f(0.8f, 0.8f, 0.0f, 1.0f);
        if (tile.getWorldObj() != null) {
            final float scale = MathHelper.sin(Minecraft.getMinecraft().renderViewEntity.ticksExisted / 3.0f) * 0.1f
                    + 0.9f;
            final int j = 50 + (int) (170.0f * (v * 2.5f * scale));
            final int k = j % 65536;
            final int l = j / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k / 1.0f, l / 1.0f);
        }
        UtilsFX.bindTexture("textures/models/node_converter_over.png");
        this.model.renderPart("lock");
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        for (int a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            if (tile.getWorldObj() != null) {
                final int k = bright % 65536;
                final int l = bright / 65536;
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k / 1.0f, l / 1.0f);
            }
            GL11.glRotatef((float) (90 * a), 0.0f, 0.0f, 1.0f);
            GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0f, 0.0f, v);
            UtilsFX.bindTexture("textures/models/node_converter.png");
            this.model.renderPart("piston");
            GL11.glColor4f(0.8f, 0.8f, 0.0f, 1.0f);
            if (tile.getWorldObj() != null) {
                final float scale2 = MathHelper
                        .sin((Minecraft.getMinecraft().renderViewEntity.ticksExisted + a * 5) / 3.0f) * 0.1f + 0.9f;
                final int i = 50 + (int) (170.0f * (v * 2.5f * scale2));
                final int m = i % 65536;
                final int l2 = i / 65536;
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, m / 1.0f, l2 / 1.0f);
            }
            UtilsFX.bindTexture("textures/models/node_converter_over.png");
            this.model.renderPart("piston");
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(final TileEntity par1TileEntity, final double par2, final double par4,
            final double par6, final float par8) {
        this.renderTileEntityAt((TileTransductionAmplifier) par1TileEntity, par2, par4, par6, par8);
    }

    static {
        MODEL = new ResourceLocation("thaumcraft", "textures/models/node_stabilizer.obj");
    }
}
