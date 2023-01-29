//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.lib.UtilsFX;

import com.kentington.thaumichorizons.client.renderer.model.ModelVat;

public class TileVatRender extends TileEntitySpecialRenderer {

    private static ModelVat model;
    private String tx1;

    public TileVatRender() {
        this.tx1 = "textures/models/vat.png";
    }

    public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y - 0.5f, (float) z + 0.5f);
        UtilsFX.bindTexture("thaumichorizons", this.tx1);
        TileVatRender.model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glEnable(32826);
        GL11.glPopMatrix();
    }

    static {
        TileVatRender.model = new ModelVat();
    }
}
