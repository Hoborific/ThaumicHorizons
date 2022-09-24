//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRecombinator extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Pearl;
    ModelRenderer Claw1A;
    ModelRenderer Claw2A;
    ModelRenderer Claw3A;
    ModelRenderer Claw4A;
    ModelRenderer EndA;
    ModelRenderer Shape1;
    ModelRenderer Claw1B;
    ModelRenderer Claw2B;
    ModelRenderer Claw3B;
    ModelRenderer Claw4B;

    public ModelRecombinator() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.Base = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 4, 16);
        this.Base.setRotationPoint(-8.0f, 20.0f, -8.0f);
        this.Base.setTextureSize(64, 64);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        (this.Pearl = new ModelRenderer((ModelBase) this, 0, 20)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Pearl.setRotationPoint(-2.0f, 14.0f, -2.0f);
        this.Pearl.setTextureSize(64, 64);
        this.Pearl.mirror = true;
        this.setRotation(this.Pearl, 0.0f, 0.0f, 0.0f);
        (this.Claw1A = new ModelRenderer((ModelBase) this, 0, 28)).addBox(0.0f, 0.0f, 0.0f, 2, 8, 1);
        this.Claw1A.setRotationPoint(-1.0f, 12.0f, 7.0f);
        this.Claw1A.setTextureSize(64, 64);
        this.Claw1A.mirror = true;
        this.setRotation(this.Claw1A, 0.0f, 0.0f, 0.0f);
        (this.Claw2A = new ModelRenderer((ModelBase) this, 0, 28)).addBox(0.0f, 0.0f, 0.0f, 2, 8, 1);
        this.Claw2A.setRotationPoint(-1.0f, 12.0f, -8.0f);
        this.Claw2A.setTextureSize(64, 64);
        this.Claw2A.mirror = true;
        this.setRotation(this.Claw2A, 0.0f, 0.0f, 0.0f);
        (this.Claw3A = new ModelRenderer((ModelBase) this, 6, 28)).addBox(0.0f, 0.0f, 0.0f, 1, 8, 2);
        this.Claw3A.setRotationPoint(-8.0f, 12.0f, -1.0f);
        this.Claw3A.setTextureSize(64, 64);
        this.Claw3A.mirror = true;
        this.setRotation(this.Claw3A, 0.0f, 0.0f, 0.0f);
        (this.Claw4A = new ModelRenderer((ModelBase) this, 6, 28)).addBox(0.0f, 0.0f, 0.0f, 1, 8, 2);
        this.Claw4A.setRotationPoint(7.0f, 12.0f, -1.0f);
        this.Claw4A.setTextureSize(64, 64);
        this.Claw4A.mirror = true;
        this.setRotation(this.Claw4A, 0.0f, 0.0f, 0.0f);
        (this.EndA = new ModelRenderer((ModelBase) this, 0, 48)).addBox(0.0f, 0.0f, 0.0f, 4, 2, 4);
        this.EndA.setRotationPoint(-2.0f, 8.0f, -2.0f);
        this.EndA.setTextureSize(64, 64);
        this.EndA.mirror = true;
        this.setRotation(this.EndA, 0.0f, 0.0f, 0.0f);
        (this.Shape1 = new ModelRenderer((ModelBase) this, 0, 38)).addBox(0.0f, 0.0f, 0.0f, 8, 2, 8);
        this.Shape1.setRotationPoint(-4.0f, 10.0f, -4.0f);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        (this.Claw1B = new ModelRenderer((ModelBase) this, 0, 54)).addBox(0.0f, 0.0f, 0.0f, 2, 1, 4);
        this.Claw1B.setRotationPoint(-1.0f, 11.0f, 4.0f);
        this.Claw1B.setTextureSize(64, 64);
        this.Claw1B.mirror = true;
        this.setRotation(this.Claw1B, 0.0f, 0.0f, 0.0f);
        (this.Claw2B = new ModelRenderer((ModelBase) this, 0, 54)).addBox(0.0f, 0.0f, 0.0f, 2, 1, 4);
        this.Claw2B.setRotationPoint(-1.0f, 11.0f, -8.0f);
        this.Claw2B.setTextureSize(64, 64);
        this.Claw2B.mirror = true;
        this.setRotation(this.Claw2B, 0.0f, 0.0f, 0.0f);
        (this.Claw3B = new ModelRenderer((ModelBase) this, 0, 59)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 2);
        this.Claw3B.setRotationPoint(4.0f, 11.0f, -1.0f);
        this.Claw3B.setTextureSize(64, 64);
        this.Claw3B.mirror = true;
        this.setRotation(this.Claw3B, 0.0f, 0.0f, 0.0f);
        (this.Claw4B = new ModelRenderer((ModelBase) this, 0, 59)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 2);
        this.Claw4B.setRotationPoint(-8.0f, 11.0f, -1.0f);
        this.Claw4B.setTextureSize(64, 64);
        this.Claw4B.mirror = true;
        this.setRotation(this.Claw4B, 0.0f, 0.0f, 0.0f);
    }

    public void render(
            final Entity entity,
            final float f,
            final float f1,
            final float f2,
            final float f3,
            final float f4,
            final float f5,
            final float motion) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Base.render(f5);
        this.Pearl.setRotationPoint(-2.0f, 14.0f + motion, -2.0f);
        this.Pearl.render(f5);
        this.Claw1A.render(f5);
        this.Claw2A.render(f5);
        this.Claw3A.render(f5);
        this.Claw4A.render(f5);
        this.EndA.render(f5);
        this.Shape1.render(f5);
        this.Claw1B.render(f5);
        this.Claw2B.render(f5);
        this.Claw3B.render(f5);
        this.Claw4B.render(f5);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(
            final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) null);
    }
}
