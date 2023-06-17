//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.lib.UtilsFX;

public class ItemCorpseEffigyRender implements IItemRenderer {

    private final ModelBiped corpse;
    private final String tx1;

    public ItemCorpseEffigyRender() {
        this.corpse = new ModelBiped();
        this.tx1 = "textures/models/corpseeffigy.png";
    }

    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item,
            final IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.BLOCK_3D;
    }

    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack item, final Object... data) {
        final ItemRenderer ir = RenderManager.instance.itemRenderer;
        GL11.glPushMatrix();
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
        if (type != IItemRenderer.ItemRenderType.INVENTORY) {
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glTranslated(0.0, -3.0, 0.0);
            } else {
                GL11.glScalef(0.5f, 0.5f, 0.5f);
                GL11.glTranslated(-1.0, -4.0, -1.0);
            }
        } else {
            GL11.glTranslated(0.0, -0.9, 0.0);
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
        UtilsFX.bindTexture(new ResourceLocation("thaumichorizons", this.tx1));
        this.corpse.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.125f);
        GL11.glPopMatrix();
    }
}
