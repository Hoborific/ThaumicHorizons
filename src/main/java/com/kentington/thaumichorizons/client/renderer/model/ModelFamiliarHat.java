//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFamiliarHat extends ModelBase {

    ModelRenderer HatBase;
    ModelRenderer HatA;
    ModelRenderer HatB;
    ModelRenderer HatC;
    ModelRenderer GoldBuckle;

    public ModelFamiliarHat() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        (this.HatBase = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 7, 1, 7);
        this.HatBase.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.HatBase.setTextureSize(32, 32);
        this.HatBase.mirror = true;
        this.setRotation(this.HatBase, 0.0f, 0.0f, 0.0f);
        (this.HatA = new ModelRenderer((ModelBase) this, 0, 8)).addBox(0.0f, -1.0f, 1.0f, 5, 2, 5);
        this.HatA.setRotationPoint(1.0f, -1.0f, 0.0f);
        this.HatA.setTextureSize(32, 32);
        this.HatA.mirror = true;
        this.setRotation(this.HatA, 0.0f, 0.0f, 0.0f);
        (this.HatB = new ModelRenderer((ModelBase) this, 0, 15)).addBox(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.HatB.setRotationPoint(2.0f, -4.0f, 2.0f);
        this.HatB.setTextureSize(32, 32);
        this.HatB.mirror = true;
        this.setRotation(this.HatB, 0.0f, 0.0f, 0.0f);
        (this.HatC = new ModelRenderer((ModelBase) this, 0, 20)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.HatC.setRotationPoint(3.0f, -5.0f, 3.0f);
        this.HatC.setTextureSize(32, 32);
        this.HatC.mirror = true;
        this.setRotation(this.HatC, 0.0f, 0.0f, 0.0f);
        (this.GoldBuckle = new ModelRenderer((ModelBase) this, 0, 22)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.GoldBuckle.setRotationPoint(0.0f, -1.0f, 3.0f);
        this.GoldBuckle.setTextureSize(32, 32);
        this.GoldBuckle.mirror = true;
        this.setRotation(this.GoldBuckle, 0.0f, 0.0f, 0.0f);
    }

    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3,
            final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.HatBase.render(f5);
        this.HatA.render(f5);
        this.HatB.render(f5);
        this.HatC.render(f5);
        this.GoldBuckle.render(f5);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
