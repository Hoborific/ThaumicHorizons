//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import java.awt.Color;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

import com.kentington.thaumichorizons.common.lib.PocketPlaneData;

public class ModelReceptacle extends ModelBase {

    ModelRenderer Corner1;
    ModelRenderer Corner2;
    ModelRenderer Corner3;
    ModelRenderer Corner4;
    ModelRenderer Side1A;
    ModelRenderer Corner5;
    ModelRenderer Corner6;
    ModelRenderer Corner7;
    ModelRenderer Corner8;
    ModelRenderer Side1B;
    ModelRenderer Side1C;
    ModelRenderer Side1D;
    ModelRenderer Side2A;
    ModelRenderer Side2B;
    ModelRenderer Side2C;
    ModelRenderer Side2D;
    ModelRenderer Side3A;
    ModelRenderer Side3B;
    ModelRenderer Side3C;
    ModelRenderer Side3D;
    ModelRenderer Keystone;

    public ModelReceptacle() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.Corner1 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner1.setRotationPoint(-8.0f, 20.0f, 4.0f);
        this.Corner1.setTextureSize(64, 64);
        this.Corner1.mirror = true;
        this.setRotation(this.Corner1, 0.0f, 0.0f, 0.0f);
        (this.Corner2 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner2.setRotationPoint(-8.0f, 20.0f, -8.0f);
        this.Corner2.setTextureSize(64, 64);
        this.Corner2.mirror = true;
        this.setRotation(this.Corner2, 0.0f, 0.0f, 0.0f);
        (this.Corner3 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner3.setRotationPoint(4.0f, 20.0f, -8.0f);
        this.Corner3.setTextureSize(64, 64);
        this.Corner3.mirror = true;
        this.setRotation(this.Corner3, 0.0f, 0.0f, 0.0f);
        (this.Corner4 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner4.setRotationPoint(4.0f, 20.0f, 4.0f);
        this.Corner4.setTextureSize(64, 64);
        this.Corner4.mirror = true;
        this.setRotation(this.Corner4, 0.0f, 0.0f, 0.0f);
        (this.Side1A = new ModelRenderer((ModelBase) this, 0, 8)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 4);
        this.Side1A.setRotationPoint(-4.0f, 8.0f, 4.0f);
        this.Side1A.setTextureSize(64, 64);
        this.Side1A.mirror = true;
        this.setRotation(this.Side1A, 0.0f, 0.0f, 0.0f);
        (this.Corner5 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner5.setRotationPoint(-8.0f, 8.0f, 4.0f);
        this.Corner5.setTextureSize(64, 64);
        this.Corner5.mirror = true;
        this.setRotation(this.Corner5, 0.0f, 0.0f, 0.0f);
        (this.Corner6 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner6.setRotationPoint(-8.0f, 8.0f, -8.0f);
        this.Corner6.setTextureSize(64, 64);
        this.Corner6.mirror = true;
        this.setRotation(this.Corner6, 0.0f, 0.0f, 0.0f);
        (this.Corner7 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner7.setRotationPoint(4.0f, 8.0f, -8.0f);
        this.Corner7.setTextureSize(64, 64);
        this.Corner7.mirror = true;
        this.setRotation(this.Corner7, 0.0f, 0.0f, 0.0f);
        (this.Corner8 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner8.setRotationPoint(4.0f, 8.0f, 4.0f);
        this.Corner8.setTextureSize(64, 64);
        this.Corner8.mirror = true;
        this.setRotation(this.Corner8, 0.0f, 0.0f, 0.0f);
        (this.Side1B = new ModelRenderer((ModelBase) this, 0, 8)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 4);
        this.Side1B.setRotationPoint(-4.0f, 8.0f, -8.0f);
        this.Side1B.setTextureSize(64, 64);
        this.Side1B.mirror = true;
        this.setRotation(this.Side1B, 0.0f, 0.0f, 0.0f);
        (this.Side1C = new ModelRenderer((ModelBase) this, 0, 8)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 4);
        this.Side1C.setRotationPoint(-4.0f, 20.0f, -8.0f);
        this.Side1C.setTextureSize(64, 64);
        this.Side1C.mirror = true;
        this.setRotation(this.Side1C, 0.0f, 0.0f, 0.0f);
        (this.Side1D = new ModelRenderer((ModelBase) this, 0, 8)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 4);
        this.Side1D.setRotationPoint(-4.0f, 20.0f, 4.0f);
        this.Side1D.setTextureSize(64, 64);
        this.Side1D.mirror = true;
        this.setRotation(this.Side1D, 0.0f, 0.0f, 0.0f);
        (this.Side2A = new ModelRenderer((ModelBase) this, 0, 16)).addBox(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Side2A.setRotationPoint(-8.0f, 12.0f, -8.0f);
        this.Side2A.setTextureSize(64, 64);
        this.Side2A.mirror = true;
        this.setRotation(this.Side2A, 0.0f, 0.0f, 0.0f);
        (this.Side2B = new ModelRenderer((ModelBase) this, 0, 16)).addBox(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Side2B.setRotationPoint(4.0f, 12.0f, 4.0f);
        this.Side2B.setTextureSize(64, 64);
        this.Side2B.mirror = true;
        this.setRotation(this.Side2B, 0.0f, 0.0f, 0.0f);
        (this.Side2C = new ModelRenderer((ModelBase) this, 0, 16)).addBox(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Side2C.setRotationPoint(-8.0f, 12.0f, 4.0f);
        this.Side2C.setTextureSize(64, 64);
        this.Side2C.mirror = true;
        this.setRotation(this.Side2C, 0.0f, 0.0f, 0.0f);
        (this.Side2D = new ModelRenderer((ModelBase) this, 0, 16)).addBox(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Side2D.setRotationPoint(4.0f, 12.0f, -8.0f);
        this.Side2D.setTextureSize(64, 64);
        this.Side2D.mirror = true;
        this.setRotation(this.Side2D, 0.0f, 0.0f, 0.0f);
        (this.Side3A = new ModelRenderer((ModelBase) this, 0, 28)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Side3A.setRotationPoint(-8.0f, 8.0f, -4.0f);
        this.Side3A.setTextureSize(64, 64);
        this.Side3A.mirror = true;
        this.setRotation(this.Side3A, 0.0f, 0.0f, 0.0f);
        (this.Side3B = new ModelRenderer((ModelBase) this, 0, 28)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Side3B.setRotationPoint(4.0f, 8.0f, -4.0f);
        this.Side3B.setTextureSize(64, 64);
        this.Side3B.mirror = true;
        this.setRotation(this.Side3B, 0.0f, 0.0f, 0.0f);
        (this.Side3C = new ModelRenderer((ModelBase) this, 0, 28)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Side3C.setRotationPoint(-8.0f, 20.0f, -4.0f);
        this.Side3C.setTextureSize(64, 64);
        this.Side3C.mirror = true;
        this.setRotation(this.Side3C, 0.0f, 0.0f, 0.0f);
        (this.Side3D = new ModelRenderer((ModelBase) this, 0, 28)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Side3D.setRotationPoint(4.0f, 20.0f, -4.0f);
        this.Side3D.setTextureSize(64, 64);
        this.Side3D.mirror = true;
        this.setRotation(this.Side3D, 0.0f, 0.0f, 0.0f);
        (this.Keystone = new ModelRenderer((ModelBase) this, 24, 0)).addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
        this.Keystone.setRotationPoint(-4.0f, 12.0f, -4.0f);
        this.Keystone.setTextureSize(64, 64);
        this.Keystone.mirror = true;
        this.setRotation(this.Keystone, 0.0f, 0.0f, 0.0f);
    }

    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3,
            final float f4, final float f5, final boolean keystone, final int plane) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Corner1.render(f5);
        this.Corner2.render(f5);
        this.Corner3.render(f5);
        this.Corner4.render(f5);
        this.Side1A.render(f5);
        this.Corner5.render(f5);
        this.Corner6.render(f5);
        this.Corner7.render(f5);
        this.Corner8.render(f5);
        this.Side1B.render(f5);
        this.Side1C.render(f5);
        this.Side1D.render(f5);
        this.Side2A.render(f5);
        this.Side2B.render(f5);
        this.Side2C.render(f5);
        this.Side2D.render(f5);
        this.Side3A.render(f5);
        this.Side3B.render(f5);
        this.Side3C.render(f5);
        this.Side3D.render(f5);
        if (keystone && PocketPlaneData.planes.size() > plane) {
            final Color col = new Color(PocketPlaneData.planes.get(plane).color);
            renderKeystone(col.getRed() / 255.0f, col.getGreen() / 255.0f, col.getBlue() / 255.0f, f5);
        } else if (keystone) {
            renderKeystone(1f, 1f, 1f, f5);
        }
    }

    private void renderKeystone(float r, float g, float b, float f5) {
        GL11.glColor4f(r, g, b, 1.0f);
        this.Keystone.render(f5);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4,
            final float f5) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) null);
    }
}
