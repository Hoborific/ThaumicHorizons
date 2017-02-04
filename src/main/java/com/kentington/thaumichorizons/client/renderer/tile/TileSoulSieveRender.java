// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.tile;

import thaumcraft.client.lib.UtilsFX;
import org.lwjgl.opengl.GL11;
import com.kentington.thaumichorizons.common.tiles.TileSoulExtractor;
import net.minecraft.tileentity.TileEntity;
import com.kentington.thaumichorizons.client.renderer.model.ModelSoulSieve;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileSoulSieveRender extends TileEntitySpecialRenderer
{
    static String tx1;
    private ModelSoulSieve model;
    
    public TileSoulSieveRender() {
        this.model = new ModelSoulSieve();
    }
    
    public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float f) {
        final TileSoulExtractor tco = (TileSoulExtractor)te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        UtilsFX.bindTexture("thaumichorizons", TileSoulSieveRender.tx1);
        this.model.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, 0.15625f * (float)(Math.cos(Math.toRadians(tco.sieveMotion)) - 1.0), tco.ticksLeft);
        GL11.glPopMatrix();
    }
    
    static {
        TileSoulSieveRender.tx1 = "textures/models/soulsieve.png";
    }
}
