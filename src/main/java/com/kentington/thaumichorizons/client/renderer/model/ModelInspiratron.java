//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInspiratron extends ModelBase {

    ModelRenderer Jar;
    ModelRenderer BottomA;
    ModelRenderer Bottom1B;
    ModelRenderer Top;

    public ModelInspiratron() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.Jar = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 14, 10);
        this.Jar.setRotationPoint(-5.0f, 11.0f, -5.0f);
        this.Jar.setTextureSize(64, 64);
        this.Jar.mirror = true;
        this.setRotation(this.Jar, 0.0f, 0.0f, 0.0f);
        (this.BottomA = new ModelRenderer((ModelBase) this, 0, 39)).addBox(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.BottomA.setRotationPoint(-8.0f, 23.0f, -8.0f);
        this.BottomA.setTextureSize(64, 64);
        this.BottomA.mirror = true;
        this.setRotation(this.BottomA, 0.0f, 0.0f, 0.0f);
        (this.Bottom1B = new ModelRenderer((ModelBase) this, 0, 24)).addBox(0.0f, 0.0f, 0.0f, 12, 3, 12);
        this.Bottom1B.setRotationPoint(-6.0f, 20.0f, -6.0f);
        this.Bottom1B.setTextureSize(64, 64);
        this.Bottom1B.mirror = true;
        this.setRotation(this.Bottom1B, 0.0f, 0.0f, 0.0f);
        (this.Top = new ModelRenderer((ModelBase) this, 0, 24)).addBox(0.0f, 0.0f, 0.0f, 12, 1, 12);
        this.Top.setRotationPoint(-6.0f, 10.0f, -6.0f);
        this.Top.setTextureSize(64, 64);
        this.Top.mirror = true;
        this.setRotation(this.Top, 0.0f, 0.0f, 0.0f);
    }

    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3,
            final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.Jar.render(f5);
        this.BottomA.render(f5);
        this.Bottom1B.render(f5);
        this.Top.render(f5);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
