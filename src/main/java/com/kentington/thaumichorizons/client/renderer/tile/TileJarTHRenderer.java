// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.tile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import com.kentington.thaumichorizons.common.tiles.TileSoulJar;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.client.renderers.models.ModelJar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

@SideOnly(Side.CLIENT)
public class TileJarTHRenderer extends TileEntitySpecialRenderer
{
    private ModelJar model;
    static String tx3;
    
    public TileJarTHRenderer() {
        this.model = new ModelJar();
    }
    
    public void renderTileEntityAt(final TileEntity tile, final double x, final double y, final double z, final float f) {
        if (!(tile instanceof TileSoulJar)) {
            return;
        }
        final TileSoulJar th = (TileSoulJar)tile;
        if (th.jarTag != null && th.jarTag.getBoolean("isSoul")) {
            final long nt = System.nanoTime();
            UtilsFX.bindTexture("thaumichorizons", TileJarTHRenderer.tx3);
            GL11.glEnable(3042);
            GL11.glAlphaFunc(516, 0.003921569f);
            GL11.glDisable(2929);
            GL11.glDisable(2884);
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            UtilsFX.renderFacingQuad(tile.xCoord + 0.5, tile.yCoord + 0.4, tile.zCoord + 0.5, 0.0f, 0.1f, 0.9f, 16, (int)(nt / 40000000L % 16L), f, 16777215);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
            GL11.glEnable(2884);
            GL11.glEnable(2929);
            GL11.glDisable(3042);
            return;
        }
        GL11.glPushMatrix();
        GL11.glDisable(2884);
        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.01f, (float)z + 0.5f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.bindTexture(th.getTexture());
        if (th.entity != null) {
            final float f2 = 0.25f;
            GL11.glScalef(f2, f2, f2);
            th.entity.setLocationAndAngles(th.xCoord + 0.5, th.yCoord + 0.5, th.zCoord + 0.5, 0.0f, 0.0f);
            Render render;
            render = RenderManager.instance.getEntityRenderObject(th.entity);
            if (render != null) {
                render.doRender(th.entity, 0.0, 0.0, 0.0, 0.0f, f);
            }
        }
        GL11.glEnable(2884);
        GL11.glPopMatrix();
    }
    
    static {
        TileJarTHRenderer.tx3 = "textures/misc/soul.png";
    }
}
