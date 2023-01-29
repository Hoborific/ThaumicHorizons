//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSoulforge extends ModelBase {

    ModelRenderer BrainTank;
    ModelRenderer Neck;
    ModelRenderer SoulTank;
    ModelRenderer Rod1;
    ModelRenderer Rod2;
    ModelRenderer Rod3;
    ModelRenderer Rod4;
    public ModelRenderer Brine;

    public ModelSoulforge() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.BrainTank = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 8, 16);
        this.BrainTank.setRotationPoint(-8.0f, 16.0f, -8.0f);
        this.BrainTank.setTextureSize(64, 64);
        this.BrainTank.mirror = true;
        this.setRotation(this.BrainTank, 0.0f, 0.0f, 0.0f);
        (this.Neck = new ModelRenderer((ModelBase) this, 0, 24)).addBox(0.0f, 0.0f, 0.0f, 5, 2, 5);
        this.Neck.setRotationPoint(-2.5f, 14.0f, -2.5f);
        this.Neck.setTextureSize(64, 64);
        this.Neck.mirror = true;
        this.setRotation(this.Neck, 0.0f, 0.0f, 0.0f);
        (this.SoulTank = new ModelRenderer((ModelBase) this, 0, 48)).addBox(0.0f, 0.0f, 0.0f, 10, 6, 10);
        this.SoulTank.setRotationPoint(-5.0f, 8.0f, -5.0f);
        this.SoulTank.setTextureSize(64, 64);
        this.SoulTank.mirror = true;
        this.setRotation(this.SoulTank, 0.0f, 0.0f, 0.0f);
        (this.Rod1 = new ModelRenderer((ModelBase) this, 0, 31)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Rod1.setRotationPoint(-8.0f, 12.0f, -8.0f);
        this.Rod1.setTextureSize(64, 64);
        this.Rod1.mirror = true;
        this.setRotation(this.Rod1, 0.0f, 0.0f, 0.0f);
        (this.Rod2 = new ModelRenderer((ModelBase) this, 0, 31)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Rod2.setRotationPoint(-8.0f, 12.0f, 7.0f);
        this.Rod2.setTextureSize(64, 64);
        this.Rod2.mirror = true;
        this.setRotation(this.Rod2, 0.0f, 0.0f, 0.0f);
        (this.Rod3 = new ModelRenderer((ModelBase) this, 0, 31)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Rod3.setRotationPoint(7.0f, 12.0f, 7.0f);
        this.Rod3.setTextureSize(64, 64);
        this.Rod3.mirror = true;
        this.setRotation(this.Rod3, 0.0f, 0.0f, 0.0f);
        (this.Rod4 = new ModelRenderer((ModelBase) this, 0, 31)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Rod4.setRotationPoint(7.0f, 12.0f, -8.0f);
        this.Rod4.setTextureSize(64, 64);
        this.Rod4.mirror = true;
        this.setRotation(this.Rod4, 0.0f, 0.0f, 0.0f);
        (this.Brine = new ModelRenderer((ModelBase) this, 0, 0)).addBox(-7.0f, 17.0f, -7.0f, 14, 6, 14);
        this.Brine.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.Brine.setTextureSize(64, 32);
        this.Brine.mirror = true;
        this.setRotation(this.Brine, 0.0f, 0.0f, 0.0f);
    }

    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3,
            final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.BrainTank.render(f5);
        this.Neck.render(f5);
        this.SoulTank.render(f5);
        this.Rod1.render(f5);
        this.Rod2.render(f5);
        this.Rod3.render(f5);
        this.Rod4.render(f5);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4,
            final float f5, final Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void renderBrine() {
        this.Brine.render(0.0625f);
    }
}
