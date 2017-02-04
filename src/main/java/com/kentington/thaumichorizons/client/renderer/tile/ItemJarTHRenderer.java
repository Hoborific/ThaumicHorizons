// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.tile;

import net.minecraft.entity.EntityLivingBase;
import thaumcraft.common.config.ConfigBlocks;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.EntityList;
import net.minecraft.client.Minecraft;
import com.kentington.thaumichorizons.common.tiles.TileSoulJar;
import thaumcraft.client.lib.UtilsFX;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import net.minecraft.item.Item;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemJarTHRenderer implements IItemRenderer
{
    static String tx3;
    
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.EQUIPPED_BLOCK;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        if (item.getItem() == Item.getItemFromBlock(ThaumicHorizons.blockJar)) {
            final float short1 = 240.0f;
            final float short2 = 240.0f;
            GL11.glTranslated(0.5, 0.0, 0.5);
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glTranslatef(-0.5f, -0.25f, -0.5f);
            }
            else if (type == IItemRenderer.ItemRenderType.EQUIPPED && data[1] instanceof EntityPlayer) {
                GL11.glTranslatef(0.0f, 0.0f, -0.5f);
            }
            if (item.hasTagCompound() && item.stackTagCompound.getBoolean("isSoul")) {
                final long nt = System.nanoTime();
                UtilsFX.bindTexture("thaumichorizons", ItemJarTHRenderer.tx3);
                GL11.glEnable(3042);
                GL11.glAlphaFunc(516, 0.003921569f);
                GL11.glDisable(2929);
                GL11.glDisable(2884);
                GL11.glPushMatrix();
                GL11.glTranslated(0.0, 0.4, 0.0);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                UtilsFX.renderAnimatedQuad(0.3f, 0.9f, 16, (int)(nt / 40000000L % 16L), 0.0f, 16777215);
                GL11.glDisable(3042);
                GL11.glPopMatrix();
                GL11.glEnable(2884);
                GL11.glEnable(2929);
                GL11.glDisable(3042);
            }
            else if (item.hasTagCompound()) {
                final TileSoulJar th = new TileSoulJar();
                GL11.glPushMatrix();
                GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                GL11.glTranslatef(-0.5f, 0.0f, -0.5f);
                final EntityLivingBase viewer = (EntityLivingBase)Minecraft.getMinecraft().thePlayer;
                th.entity = EntityList.createEntityFromNBT(item.getTagCompound(), viewer.worldObj);
                TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity)th, 0.0, 0.0, 0.0, 0.0f);
                GL11.glBlendFunc(770, 771);
                Minecraft.getMinecraft().entityRenderer.disableLightmap(0.0);
                GL11.glPopMatrix();
            }
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0f, 0.5f, 0.0f);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
            final RenderBlocks rb = (RenderBlocks)data[0];
            rb.useInventoryTint = true;
            rb.renderBlockAsItem(ConfigBlocks.blockJar, 0, 1.0f);
            GL11.glPopMatrix();
            GL11.glDisable(3042);
        }
    }
    
    static {
        ItemJarTHRenderer.tx3 = "textures/misc/soul.png";
    }
}
