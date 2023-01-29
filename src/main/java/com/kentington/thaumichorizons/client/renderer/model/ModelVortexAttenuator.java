//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVortexAttenuator extends ModelBase {

    ModelRenderer Base;
    ModelRenderer Rod;
    ModelRenderer Ring_A1;
    ModelRenderer Ring_A2;
    ModelRenderer Ring_A3;
    ModelRenderer Ring_A4;
    ModelRenderer Ring_B1;
    ModelRenderer Ring_B2;
    ModelRenderer Ring_B3;
    ModelRenderer Ring_B4;
    ModelRenderer Ring_C1;
    ModelRenderer Ring_C2;
    ModelRenderer Ring_C3;
    ModelRenderer Ring_C4;

    public ModelVortexAttenuator() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.Base = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 4, 16);
        this.Base.setRotationPoint(-8.0f, 20.0f, -8.0f);
        this.Base.setTextureSize(64, 64);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        (this.Rod = new ModelRenderer((ModelBase) this, 0, 20)).addBox(0.0f, 0.0f, 0.0f, 2, 12, 2);
        this.Rod.setRotationPoint(-1.0f, 8.0f, -1.0f);
        this.Rod.setTextureSize(64, 64);
        this.Rod.mirror = true;
        this.setRotation(this.Rod, 0.0f, 0.0f, 0.0f);
        (this.Ring_A1 = new ModelRenderer((ModelBase) this, 0, 34)).addBox(0.0f, 0.0f, 0.0f, 12, 1, 1);
        this.Ring_A1.setRotationPoint(-6.0f, 18.0f, -6.0f);
        this.Ring_A1.setTextureSize(64, 64);
        this.Ring_A1.mirror = true;
        this.setRotation(this.Ring_A1, 0.0f, 0.0f, 0.0f);
        (this.Ring_A2 = new ModelRenderer((ModelBase) this, 0, 34)).addBox(0.0f, 0.0f, 0.0f, 12, 1, 1);
        this.Ring_A2.setRotationPoint(-6.0f, 18.0f, 5.0f);
        this.Ring_A2.setTextureSize(64, 64);
        this.Ring_A2.mirror = true;
        this.setRotation(this.Ring_A2, 0.0f, 0.0f, 0.0f);
        (this.Ring_A3 = new ModelRenderer((ModelBase) this, 0, 36)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 10);
        this.Ring_A3.setRotationPoint(5.0f, 18.0f, -5.0f);
        this.Ring_A3.setTextureSize(64, 64);
        this.Ring_A3.mirror = true;
        this.setRotation(this.Ring_A3, 0.0f, 0.0f, 0.0f);
        (this.Ring_A4 = new ModelRenderer((ModelBase) this, 0, 36)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 10);
        this.Ring_A4.setRotationPoint(-6.0f, 18.0f, -5.0f);
        this.Ring_A4.setTextureSize(64, 64);
        this.Ring_A4.mirror = true;
        this.setRotation(this.Ring_A4, 0.0f, 0.0f, 0.0f);
        (this.Ring_B1 = new ModelRenderer((ModelBase) this, 0, 47)).addBox(0.0f, 0.0f, 0.0f, 8, 1, 1);
        this.Ring_B1.setRotationPoint(-4.0f, 15.0f, 3.0f);
        this.Ring_B1.setTextureSize(64, 64);
        this.Ring_B1.mirror = true;
        this.setRotation(this.Ring_B1, 0.0f, 0.0f, 0.0f);
        (this.Ring_B2 = new ModelRenderer((ModelBase) this, 0, 47)).addBox(0.0f, 0.0f, 0.0f, 8, 1, 1);
        this.Ring_B2.setRotationPoint(-4.0f, 15.0f, -4.0f);
        this.Ring_B2.setTextureSize(64, 64);
        this.Ring_B2.mirror = true;
        this.setRotation(this.Ring_B2, 0.0f, 0.0f, 0.0f);
        (this.Ring_B3 = new ModelRenderer((ModelBase) this, 0, 49)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Ring_B3.setRotationPoint(3.0f, 15.0f, -3.0f);
        this.Ring_B3.setTextureSize(64, 64);
        this.Ring_B3.mirror = true;
        this.setRotation(this.Ring_B3, 0.0f, 0.0f, 0.0f);
        (this.Ring_B4 = new ModelRenderer((ModelBase) this, 0, 49)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Ring_B4.setRotationPoint(-4.0f, 15.0f, -3.0f);
        this.Ring_B4.setTextureSize(64, 64);
        this.Ring_B4.mirror = true;
        this.setRotation(this.Ring_B4, 0.0f, 0.0f, 0.0f);
        (this.Ring_C1 = new ModelRenderer((ModelBase) this, 0, 56)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Ring_C1.setRotationPoint(-2.0f, 12.0f, -2.0f);
        this.Ring_C1.setTextureSize(64, 64);
        this.Ring_C1.mirror = true;
        this.setRotation(this.Ring_C1, 0.0f, 0.0f, 0.0f);
        (this.Ring_C2 = new ModelRenderer((ModelBase) this, 0, 56)).addBox(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Ring_C2.setRotationPoint(-2.0f, 12.0f, 1.0f);
        this.Ring_C2.setTextureSize(64, 64);
        this.Ring_C2.mirror = true;
        this.setRotation(this.Ring_C2, 0.0f, 0.0f, 0.0f);
        (this.Ring_C3 = new ModelRenderer((ModelBase) this, 0, 58)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.Ring_C3.setRotationPoint(1.0f, 12.0f, -1.0f);
        this.Ring_C3.setTextureSize(64, 64);
        this.Ring_C3.mirror = true;
        this.setRotation(this.Ring_C3, 0.0f, 0.0f, 0.0f);
        (this.Ring_C4 = new ModelRenderer((ModelBase) this, 0, 58)).addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.Ring_C4.setRotationPoint(-2.0f, 12.0f, -1.0f);
        this.Ring_C4.setTextureSize(64, 64);
        this.Ring_C4.mirror = true;
        this.setRotation(this.Ring_C4, 0.0f, 0.0f, 0.0f);
    }

    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3,
            final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Base.render(f5);
        this.Rod.render(f5);
        this.Ring_A1.render(f5);
        this.Ring_A2.render(f5);
        this.Ring_A3.render(f5);
        this.Ring_A4.render(f5);
        this.Ring_B1.render(f5);
        this.Ring_B2.render(f5);
        this.Ring_B3.render(f5);
        this.Ring_B4.render(f5);
        this.Ring_C1.render(f5);
        this.Ring_C2.render(f5);
        this.Ring_C3.render(f5);
        this.Ring_C4.render(f5);
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
