//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVat extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Water;
    ModelRenderer Glass;
    ModelRenderer Top;
    ModelRenderer Hatch;

    public ModelVat() {
        this.textureWidth = 512;
        this.textureHeight = 512;
        (this.Base = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 48, 16, 48);
        this.Base.setRotationPoint(-24.0f, 8.0f, -24.0f);
        this.Base.setTextureSize(512, 512);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        (this.Water = new ModelRenderer((ModelBase) this, 256, 0)).addBox(0.0f, 0.0f, 0.0f, 40, 32, 40);
        this.Water.setRotationPoint(-20.0f, -24.0f, -20.0f);
        this.Water.setTextureSize(512, 512);
        this.Water.mirror = true;
        this.setRotation(this.Water, 0.0f, 0.0f, 0.0f);
        (this.Glass = new ModelRenderer((ModelBase) this, 0, 128)).addBox(0.0f, 0.0f, 0.0f, 48, 32, 48);
        this.Glass.setRotationPoint(-24.0f, -24.0f, -24.0f);
        this.Glass.setTextureSize(512, 512);
        this.Glass.mirror = true;
        this.setRotation(this.Glass, 0.0f, 0.0f, 0.0f);
        (this.Top = new ModelRenderer((ModelBase) this, 256, 128)).addBox(0.0f, 0.0f, 0.0f, 48, 14, 48);
        this.Top.setRotationPoint(-24.0f, -38.0f, -24.0f);
        this.Top.setTextureSize(512, 512);
        this.Top.mirror = true;
        this.setRotation(this.Top, 0.0f, 0.0f, 0.0f);
        (this.Hatch = new ModelRenderer((ModelBase) this, 0, 80)).addBox(0.0f, 0.0f, 0.0f, 16, 2, 16);
        this.Hatch.setRotationPoint(-8.0f, -40.0f, -8.0f);
        this.Hatch.setTextureSize(512, 512);
        this.Hatch.mirror = true;
        this.setRotation(this.Hatch, 0.0f, 0.0f, 0.0f);
    }

    public void render(
            final Entity entity,
            final float f,
            final float f1,
            final float f2,
            final float f3,
            final float f4,
            final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.Base.render(f5);
        this.Water.render(f5);
        this.Glass.render(f5);
        this.Top.render(f5);
        this.Hatch.render(f5);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
