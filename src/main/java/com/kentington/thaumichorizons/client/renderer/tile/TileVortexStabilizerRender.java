//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.kentington.thaumichorizons.client.renderer.model.ModelVortexAttenuator;
import com.kentington.thaumichorizons.common.tiles.TileVortexStabilizer;

import thaumcraft.client.lib.UtilsFX;

public class TileVortexStabilizerRender extends TileEntitySpecialRenderer {

    private ModelVortexAttenuator model;
    static String tx1;

    public TileVortexStabilizerRender() {
        this.model = new ModelVortexAttenuator();
    }

    public void renderTileEntityAt(final TileEntity p_147500_1_, final double x, final double y, final double z,
            final float p_147500_8_) {
        final TileVortexStabilizer te = (TileVortexStabilizer) p_147500_1_;
        GL11.glPushMatrix();
        GL11.glDisable(2884);
        GL11.glTranslatef((float) x + 0.5f, (float) y, (float) z + 0.5f);
        switch (te.blockMetadata) {
            case 0: {
                GL11.glTranslatef(0.0f, -0.5f, 0.0f);
                break;
            }
            case 1: {
                GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
                GL11.glTranslatef(0.0f, -1.5f, 0.0f);
                break;
            }
            case 2: {
                GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glTranslatef(0.5f, -1.0f, 0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef(270.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glTranslatef(-0.5f, -1.0f, 0.0f);
                break;
            }
            case 4: {
                GL11.glRotatef(270.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glTranslatef(0.0f, -1.0f, -0.5f);
                break;
            }
            case 5: {
                GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glTranslatef(0.0f, -1.0f, 0.5f);
                break;
            }
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(32826);
        GL11.glPopMatrix();
        UtilsFX.bindTexture("thaumichorizons", TileVortexStabilizerRender.tx1);
        this.model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    static {
        TileVortexStabilizerRender.tx1 = "textures/models/attenuator.png";
    }
}
