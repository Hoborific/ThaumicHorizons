//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelSoulSieve extends ModelBase {

    ModelRenderer TopSide1;
    ModelRenderer TopSide2;
    ModelRenderer TopSide3;
    ModelRenderer TopSide4;
    ModelRenderer TopBottom;
    ModelRenderer Middle;
    ModelRenderer Bottom;
    ModelRenderer Sand;
    ModelRenderer SieveSide4;
    ModelRenderer SieveSide3;
    ModelRenderer SieveSide2;
    ModelRenderer SieveSide1;
    ModelRenderer SieveCenter4;
    ModelRenderer SieveCenter3;
    ModelRenderer SieveCenter2;
    ModelRenderer SieveCenter1;
    ModelRenderer SieveOuter4B;
    ModelRenderer SieveOuter4A;
    ModelRenderer SieveOuter3B;
    ModelRenderer SieveOuter3A;
    ModelRenderer SieveOuter2A;
    ModelRenderer SieveOuter2B;
    ModelRenderer SieveOuter1B;
    ModelRenderer SieveOuter1A;

    public ModelSoulSieve() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        (this.TopSide1 = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 6, 1);
        this.TopSide1.setRotationPoint(-8.0f, 8.0f, 7.0f);
        this.TopSide1.setTextureSize(64, 32);
        this.TopSide1.mirror = true;
        this.setRotation(this.TopSide1, 0.0f, 0.0f, 0.0f);
        (this.TopSide2 = new ModelRenderer((ModelBase) this, 0, 9)).addBox(0.0f, 0.0f, 0.0f, 16, 6, 1);
        this.TopSide2.setRotationPoint(-8.0f, 8.0f, -8.0f);
        this.TopSide2.setTextureSize(64, 32);
        this.TopSide2.mirror = true;
        this.setRotation(this.TopSide2, 0.0f, 0.0f, 0.0f);
        (this.TopSide3 = new ModelRenderer((ModelBase) this, 0, 44)).addBox(0.0f, 0.0f, 0.0f, 1, 6, 14);
        this.TopSide3.setRotationPoint(7.0f, 8.0f, -7.0f);
        this.TopSide3.setTextureSize(64, 32);
        this.TopSide3.mirror = true;
        this.setRotation(this.TopSide3, 0.0f, 0.0f, 0.0f);
        (this.TopSide4 = new ModelRenderer((ModelBase) this, 0, 18)).addBox(0.0f, 0.0f, 0.0f, 1, 6, 14);
        this.TopSide4.setRotationPoint(-8.0f, 8.0f, -7.0f);
        this.TopSide4.setTextureSize(64, 32);
        this.TopSide4.mirror = true;
        this.setRotation(this.TopSide4, 0.0f, 0.0f, 0.0f);
        (this.TopBottom = new ModelRenderer((ModelBase) this, 48, 0)).addBox(0.0f, 0.0f, 0.0f, 14, 1, 14);
        this.TopBottom.setRotationPoint(-7.0f, 13.0f, -7.0f);
        this.TopBottom.setTextureSize(64, 32);
        this.TopBottom.mirror = true;
        this.setRotation(this.TopBottom, 0.0f, 0.0f, 0.0f);
        (this.Middle = new ModelRenderer((ModelBase) this, 32, 32)).addBox(0.0f, 0.0f, 0.0f, 8, 6, 8);
        this.Middle.setRotationPoint(-4.0f, 14.0f, -4.0f);
        this.Middle.setTextureSize(64, 32);
        this.Middle.mirror = true;
        this.setRotation(this.Middle, 0.0f, 0.0f, 0.0f);
        (this.Bottom = new ModelRenderer((ModelBase) this, 32, 18)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Bottom.setRotationPoint(-2.0f, 20.0f, -2.0f);
        this.Bottom.setTextureSize(64, 32);
        this.Bottom.mirror = true;
        this.setRotation(this.Bottom, 0.0f, 0.0f, 0.0f);
        (this.Sand = new ModelRenderer((ModelBase) this, 72, 16)).addBox(0.0f, 0.0f, 0.0f, 14, 3, 14);
        this.Sand.setRotationPoint(-7.0f, 10.0f, -7.0f);
        this.Sand.setTextureSize(64, 32);
        this.Sand.mirror = true;
        this.setRotation(this.Sand, 0.0f, 0.0f, 0.0f);
        (this.SieveSide4 = new ModelRenderer((ModelBase) this, 64, 36)).addBox(0.0f, 0.0f, 0.0f, 2, 1, 5);
        this.SieveSide4.setRotationPoint(-1.0f, 8.0f, 2.0f);
        this.SieveSide4.setTextureSize(64, 32);
        this.SieveSide4.mirror = true;
        this.setRotation(this.SieveSide4, 0.0f, 0.0f, 0.0f);
        (this.SieveSide3 = new ModelRenderer((ModelBase) this, 64, 36)).addBox(0.0f, 0.0f, 0.0f, 2, 1, 5);
        this.SieveSide3.setRotationPoint(-1.0f, 8.0f, -7.0f);
        this.SieveSide3.setTextureSize(64, 32);
        this.SieveSide3.mirror = true;
        this.setRotation(this.SieveSide3, 0.0f, 0.0f, 0.0f);
        (this.SieveSide2 = new ModelRenderer((ModelBase) this, 48, 18)).addBox(0.0f, 0.0f, 0.0f, 5, 1, 2);
        this.SieveSide2.setRotationPoint(-7.0f, 8.0f, -1.0f);
        this.SieveSide2.setTextureSize(64, 32);
        this.SieveSide2.mirror = true;
        this.setRotation(this.SieveSide2, 0.0f, 0.0f, 0.0f);
        (this.SieveSide1 = new ModelRenderer((ModelBase) this, 48, 18)).addBox(0.0f, 0.0f, 0.0f, 5, 1, 2);
        this.SieveSide1.setRotationPoint(2.0f, 8.0f, -1.0f);
        this.SieveSide1.setTextureSize(64, 32);
        this.SieveSide1.mirror = true;
        this.setRotation(this.SieveSide1, 0.0f, 0.0f, 0.0f);
        (this.SieveCenter4 = new ModelRenderer((ModelBase) this, 36, 0)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.SieveCenter4.setRotationPoint(1.0f, 8.0f, -1.0f);
        this.SieveCenter4.setTextureSize(64, 32);
        this.SieveCenter4.mirror = true;
        this.setRotation(this.SieveCenter4, 0.0f, 0.0f, 0.0f);
        (this.SieveCenter3 = new ModelRenderer((ModelBase) this, 36, 0)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.SieveCenter3.setRotationPoint(-2.0f, 8.0f, -1.0f);
        this.SieveCenter3.setTextureSize(64, 32);
        this.SieveCenter3.mirror = true;
        this.setRotation(this.SieveCenter3, 0.0f, 0.0f, 0.0f);
        (this.SieveCenter2 = new ModelRenderer((ModelBase) this, 32, 28)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveCenter2.setRotationPoint(-2.0f, 8.0f, 1.0f);
        this.SieveCenter2.setTextureSize(64, 32);
        this.SieveCenter2.mirror = true;
        this.setRotation(this.SieveCenter2, 0.0f, 0.0f, 0.0f);
        (this.SieveCenter1 = new ModelRenderer((ModelBase) this, 32, 28)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveCenter1.setRotationPoint(-2.0f, 8.0f, -2.0f);
        this.SieveCenter1.setTextureSize(64, 32);
        this.SieveCenter1.mirror = true;
        this.setRotation(this.SieveCenter1, 0.0f, 0.0f, 0.0f);
        (this.SieveOuter4B = new ModelRenderer((ModelBase) this, 62, 24)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveOuter4B.setRotationPoint(-5.0f, 8.0f, -5.0f);
        this.SieveOuter4B.setTextureSize(128, 64);
        this.SieveOuter4B.mirror = true;
        this.setRotation(this.SieveOuter4B, 0.0f, 0.0f, 0.0f);
        (this.SieveOuter4A = new ModelRenderer((ModelBase) this, 64, 32)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.SieveOuter4A.setRotationPoint(-5.0f, 8.0f, -4.0f);
        this.SieveOuter4A.setTextureSize(128, 64);
        this.SieveOuter4A.mirror = true;
        this.setRotation(this.SieveOuter4A, 0.0f, 0.0f, 0.0f);
        (this.SieveOuter3B = new ModelRenderer((ModelBase) this, 62, 24)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveOuter3B.setRotationPoint(1.0f, 8.0f, -5.0f);
        this.SieveOuter3B.setTextureSize(128, 64);
        this.SieveOuter3B.mirror = true;
        this.setRotation(this.SieveOuter3B, 0.0f, 0.0f, 0.0f);
        (this.SieveOuter3A = new ModelRenderer((ModelBase) this, 64, 32)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.SieveOuter3A.setRotationPoint(4.0f, 8.0f, -4.0f);
        this.SieveOuter3A.setTextureSize(128, 64);
        this.SieveOuter3A.mirror = true;
        this.setRotation(this.SieveOuter3A, 0.0f, 0.0f, 0.0f);
        (this.SieveOuter2A = new ModelRenderer((ModelBase) this, 64, 32)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.SieveOuter2A.setRotationPoint(4.0f, 8.0f, 1.0f);
        this.SieveOuter2A.setTextureSize(128, 64);
        this.SieveOuter2A.mirror = true;
        this.setRotation(this.SieveOuter2A, 0.0f, 0.0f, 0.0f);
        (this.SieveOuter2B = new ModelRenderer((ModelBase) this, 62, 24)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveOuter2B.setRotationPoint(1.0f, 8.0f, 4.0f);
        this.SieveOuter2B.setTextureSize(128, 64);
        this.SieveOuter2B.mirror = true;
        this.setRotation(this.SieveOuter2B, 0.0f, 0.0f, 0.0f);
        (this.SieveOuter1B = new ModelRenderer((ModelBase) this, 62, 24)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveOuter1B.setRotationPoint(-5.0f, 8.0f, 4.0f);
        this.SieveOuter1B.setTextureSize(128, 64);
        this.SieveOuter1B.mirror = true;
        this.setRotation(this.SieveOuter1B, 0.0f, 0.0f, 0.0f);
        (this.SieveOuter1A = new ModelRenderer((ModelBase) this, 64, 32)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.SieveOuter1A.setRotationPoint(-5.0f, 8.0f, 1.0f);
        this.SieveOuter1A.setTextureSize(128, 64);
        this.SieveOuter1A.mirror = true;
        this.setRotation(this.SieveOuter1A, 0.0f, 0.0f, 0.0f);
    }

    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3,
            final float f4, final float f5, final float sieveMovement, final float sandScale) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.TopSide1.render(f5);
        this.TopSide2.render(f5);
        this.TopSide3.render(f5);
        this.TopSide4.render(f5);
        this.TopBottom.render(f5);
        this.Middle.render(f5);
        this.Bottom.render(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0f, -sieveMovement, 0.0f);
        this.SieveSide4.render(f5);
        this.SieveSide3.render(f5);
        this.SieveSide2.render(f5);
        this.SieveSide1.render(f5);
        this.SieveCenter4.render(f5);
        this.SieveCenter3.render(f5);
        this.SieveCenter2.render(f5);
        this.SieveCenter1.render(f5);
        this.SieveOuter4B.render(f5);
        this.SieveOuter4A.render(f5);
        this.SieveOuter3B.render(f5);
        this.SieveOuter3A.render(f5);
        this.SieveOuter2A.render(f5);
        this.SieveOuter2B.render(f5);
        this.SieveOuter1B.render(f5);
        this.SieveOuter1A.render(f5);
        GL11.glPopMatrix();
        if (sandScale > 0.0f) {
            this.Sand.render(f5);
        }
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
