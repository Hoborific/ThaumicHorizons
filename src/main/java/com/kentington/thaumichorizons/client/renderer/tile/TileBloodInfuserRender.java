//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.renderer.model.ModelBloodInfuser;
import com.kentington.thaumichorizons.client.renderer.model.ModelSyringe;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileBloodInfuser;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileBloodInfuserRender extends TileEntitySpecialRenderer {
    static String tx1;
    private ModelBloodInfuser base;
    static String tx2;
    private ModelSyringe syringe;

    public TileBloodInfuserRender() {
        this.base = new ModelBloodInfuser();
        this.syringe = new ModelSyringe();
    }

    public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float f) {
        final TileBloodInfuser tco = (TileBloodInfuser) te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        UtilsFX.bindTexture("thaumichorizons", TileBloodInfuserRender.tx1);
        this.base.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
        if (tco.hasBlood()) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 1.90625f, (float) y + 0.75f, (float) z + 0.46875f);
            GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
            UtilsFX.bindTexture("thaumichorizons", TileBloodInfuserRender.tx2);
            this.syringe.render(
                    null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, new ItemStack(ThaumicHorizons.itemSyringeHuman));
            GL11.glPopMatrix();
        }
    }

    static {
        TileBloodInfuserRender.tx1 = "textures/models/bloodinfuser.png";
        TileBloodInfuserRender.tx2 = "textures/models/syringe.png";
    }
}
