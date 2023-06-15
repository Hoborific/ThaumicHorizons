//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.kentington.thaumichorizons.client.renderer.model.ModelReceptacle;
import com.kentington.thaumichorizons.common.tiles.TileSlot;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.client.lib.UtilsFX;

@SideOnly(Side.CLIENT)
public class TileSlotRender extends TileEntitySpecialRenderer {

    static String tx1;
    private final ModelReceptacle base;

    public TileSlotRender() {
        this.base = new ModelReceptacle();
    }

    public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float f) {
        final TileSlot tco = (TileSlot) te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y - 0.5f, (float) z + 0.5f);
        UtilsFX.bindTexture("thaumichorizons", TileSlotRender.tx1);
        this.base.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, tco.hasKeystone, tco.pocketID);
        GL11.glPopMatrix();
    }

    static {
        TileSlotRender.tx1 = "textures/models/receptacle.png";
    }
}
