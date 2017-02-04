// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.model;

import com.kentington.thaumichorizons.common.entities.EntitySyringe;
import org.lwjgl.opengl.GL11;
import java.awt.Color;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBase;

public class ModelSyringe extends ModelBase
{
    ModelRenderer Body;
    ModelRenderer Needle;
    ModelRenderer PlungerA;
    ModelRenderer PlungerB;
    
    public ModelSyringe() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        (this.Body = new ModelRenderer((ModelBase)this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 9, 3, 3);
        this.Body.setRotationPoint(-3.0f, 21.0f, -1.0f);
        this.Body.setTextureSize(64, 32);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        (this.Needle = new ModelRenderer((ModelBase)this, 25, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Needle.setRotationPoint(-7.0f, 22.0f, 0.0f);
        this.Needle.setTextureSize(64, 32);
        this.Needle.mirror = true;
        this.setRotation(this.Needle, 0.0f, 0.0f, 0.0f);
        (this.PlungerA = new ModelRenderer((ModelBase)this, 0, 8)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.PlungerA.setRotationPoint(6.0f, 22.0f, 0.0f);
        this.PlungerA.setTextureSize(64, 32);
        this.PlungerA.mirror = true;
        this.setRotation(this.PlungerA, 0.0f, 0.0f, 0.0f);
        (this.PlungerB = new ModelRenderer((ModelBase)this, 0, 12)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 3);
        this.PlungerB.setRotationPoint(7.0f, 21.0f, -1.0f);
        this.PlungerB.setTextureSize(64, 32);
        this.PlungerB.mirror = true;
        this.setRotation(this.PlungerB, 0.0f, 0.0f, 0.0f);
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final ItemStack item) {
        if (item != null) {
            final Color col = new Color(ThaumicHorizons.itemSyringeInjection.getColorFromItemStack(item, 0));
            final float red = col.getRed() / 255.0f;
            final float green = col.getGreen() / 255.0f;
            final float blue = col.getBlue() / 255.0f;
            if (item.getItem() != ThaumicHorizons.itemSyringeEmpty) {
                GL11.glColor4f(red, green, blue, 0.75f);
            }
            else {
                GL11.glColor4f(red, green, blue, 0.5f);
            }
        }
        else if (entity != null && entity instanceof EntitySyringe) {
            final Color col = new Color(((EntitySyringe)entity).color);
            final float red = col.getRed() / 255.0f;
            final float green = col.getGreen() / 255.0f;
            final float blue = col.getBlue() / 255.0f;
            GL11.glColor4f(red, green, blue, 0.75f);
            GL11.glTranslatef(0.0f, -1.36f, 0.0f);
        }
        this.Body.render(f5);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.Needle.render(f5);
        this.PlungerA.render(f5);
        this.PlungerB.render(f5);
    }
    
    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity ent) {
        this.Body.rotateAngleX = ent.rotationPitch;
        this.Body.rotateAngleY = ent.rotationYaw;
        this.Needle.rotateAngleX = ent.rotationPitch;
        this.Needle.rotateAngleY = ent.rotationYaw;
        this.PlungerA.rotateAngleX = ent.rotationPitch;
        this.PlungerA.rotateAngleY = ent.rotationYaw;
        this.PlungerB.rotateAngleX = ent.rotationPitch;
        this.PlungerB.rotateAngleY = ent.rotationYaw;
    }
}
