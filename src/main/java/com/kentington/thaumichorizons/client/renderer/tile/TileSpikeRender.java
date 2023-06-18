//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.kentington.thaumichorizons.common.tiles.TileSpike;

import thaumcraft.client.lib.UtilsFX;

public class TileSpikeRender extends TileEntitySpecialRenderer {

    private final IModelCustom model;
    private static final ResourceLocation SPIKE;
    static String tx1;
    static String tx2;
    static String tx3;

    public TileSpikeRender() {
        this.model = AdvancedModelLoader.loadModel(TileSpikeRender.SPIKE);
    }

    public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float f) {
        final TileSpike tco = (TileSpike) te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) (y + 0.5), (float) z + 0.5f);
        GL11.glScalef(0.35f, 0.35f, 0.35f);
        switch (tco.direction) {
            case 1 -> {
                GL11.glTranslatef(0.0f, -1.45f, 0.0f);
            }
            case 0 -> {
                GL11.glTranslatef(0.0f, 1.45f, 0.0f);
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
            }
            case 2 -> {
                GL11.glTranslatef(0.0f, 0.0f, 1.45f);
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
            }
            case 3 -> {
                GL11.glTranslatef(0.0f, 0.0f, -1.45f);
                GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            }
            case 4 -> {
                GL11.glTranslatef(1.45f, 0.0f, 0.0f);
                GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
            }
            case 5 -> {
                GL11.glTranslatef(-1.45f, 0.0f, 0.0f);
                GL11.glRotatef(-90.0f, 0.0f, 0.0f, 1.0f);
            }
        }
        if (tco.spikeType == 0) {
            UtilsFX.bindTexture("thaumichorizons", TileSpikeRender.tx1);
        } else if (tco.spikeType == 1) {
            UtilsFX.bindTexture("thaumichorizons", TileSpikeRender.tx2);
        } else {
            UtilsFX.bindTexture("thaumichorizons", TileSpikeRender.tx3);
        }
        this.model.renderAll();
        GL11.glPopMatrix();
    }

    static {
        SPIKE = new ResourceLocation("thaumichorizons", "textures/models/spike.obj");
        TileSpikeRender.tx1 = "textures/models/metalspike.png";
        TileSpikeRender.tx2 = "textures/models/woodenspike.png";
        TileSpikeRender.tx3 = "textures/models/toothspike.png";
    }
}
