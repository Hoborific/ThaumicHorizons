// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.entity;

import org.lwjgl.opengl.GL11;
import com.kentington.thaumichorizons.common.entities.EntitySyringe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import com.kentington.thaumichorizons.client.renderer.model.ModelSyringe;
import net.minecraft.client.renderer.entity.Render;

public class RenderSyringe extends Render
{
    private ModelSyringe model;
    
    public RenderSyringe() {
        this.shadowSize = 0.0f;
        this.model = new ModelSyringe();
    }
    
    protected ResourceLocation getEntityTexture(final Entity p_110775_1_) {
        return new ResourceLocation("thaumichorizons", "textures/models/syringe.png");
    }
    
    public void doRender(final Entity ent, final double p_76986_2_, final double p_76986_4_, final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        if (ent instanceof EntitySyringe) {
            final EntitySyringe syringe = (EntitySyringe)ent;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
            GL11.glRotatef(syringe.rotationYaw + 90.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(-syringe.rotationPitch, 0.0f, 0.0f, 1.0f);
            this.bindEntityTexture((Entity)syringe);
            this.model.render(syringe, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, null);
            GL11.glPopMatrix();
        }
    }
}
