//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAlchemite extends ModelBase {

    ModelRenderer Shape1;

    public ModelAlchemite() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        (this.Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape1.setRotationPoint(-8.0f, 8.0f, -8.0f);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
    }

    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3,
            final float f4, final float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.Shape1.render(f5);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
