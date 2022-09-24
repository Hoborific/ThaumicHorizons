//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBloodInfuser extends ModelBase {
    ModelRenderer Body;
    ModelRenderer SyringeSlot;
    ModelRenderer Pipe1A;
    ModelRenderer Pipe1B;
    ModelRenderer Pipe2A;
    ModelRenderer Pipe2B;
    ModelRenderer Pipe3A;
    ModelRenderer Pipe3B;
    ModelRenderer Pipe4A;
    ModelRenderer Pipe4B;

    public ModelBloodInfuser() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.Body = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 10, 16);
        this.Body.setRotationPoint(-8.0f, 14.0f, -8.0f);
        this.Body.setTextureSize(64, 64);
        this.Body.mirror = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        (this.SyringeSlot = new ModelRenderer((ModelBase) this, 0, 32)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.SyringeSlot.setRotationPoint(-3.0f, 8.0f, -3.0f);
        this.SyringeSlot.setTextureSize(64, 64);
        this.SyringeSlot.mirror = true;
        this.setRotation(this.SyringeSlot, 0.0f, 0.0f, 0.0f);
        (this.Pipe1A = new ModelRenderer((ModelBase) this, 0, 48)).addBox(0.0f, 0.0f, 0.0f, 2, 2, 3);
        this.Pipe1A.setRotationPoint(-1.0f, 9.0f, 3.0f);
        this.Pipe1A.setTextureSize(64, 64);
        this.Pipe1A.mirror = true;
        this.setRotation(this.Pipe1A, 0.0f, 0.0f, 0.0f);
        (this.Pipe1B = new ModelRenderer((ModelBase) this, 0, 54)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.Pipe1B.setRotationPoint(-1.0f, 11.0f, 4.0f);
        this.Pipe1B.setTextureSize(64, 64);
        this.Pipe1B.mirror = true;
        this.setRotation(this.Pipe1B, 0.0f, 0.0f, 0.0f);
        (this.Pipe2A = new ModelRenderer((ModelBase) this, 0, 48)).addBox(0.0f, 0.0f, 0.0f, 2, 2, 3);
        this.Pipe2A.setRotationPoint(-1.0f, 9.0f, -6.0f);
        this.Pipe2A.setTextureSize(64, 64);
        this.Pipe2A.mirror = true;
        this.setRotation(this.Pipe2A, 0.0f, 0.0f, 0.0f);
        (this.Pipe2B = new ModelRenderer((ModelBase) this, 0, 54)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.Pipe2B.setRotationPoint(-1.0f, 11.0f, -6.0f);
        this.Pipe2B.setTextureSize(64, 64);
        this.Pipe2B.mirror = true;
        this.setRotation(this.Pipe2B, 0.0f, 0.0f, 0.0f);
        (this.Pipe3A = new ModelRenderer((ModelBase) this, 0, 48)).addBox(0.0f, 0.0f, 0.0f, 3, 2, 2);
        this.Pipe3A.setRotationPoint(3.0f, 9.0f, -1.0f);
        this.Pipe3A.setTextureSize(64, 64);
        this.Pipe3A.mirror = true;
        this.setRotation(this.Pipe3A, 0.0f, 0.0f, 0.0f);
        (this.Pipe3B = new ModelRenderer((ModelBase) this, 0, 54)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.Pipe3B.setRotationPoint(4.0f, 11.0f, -1.0f);
        this.Pipe3B.setTextureSize(64, 64);
        this.Pipe3B.mirror = true;
        this.setRotation(this.Pipe3B, 0.0f, 0.0f, 0.0f);
        (this.Pipe4A = new ModelRenderer((ModelBase) this, 0, 48)).addBox(0.0f, 0.0f, 0.0f, 3, 2, 2);
        this.Pipe4A.setRotationPoint(-6.0f, 9.0f, -1.0f);
        this.Pipe4A.setTextureSize(64, 64);
        this.Pipe4A.mirror = true;
        this.setRotation(this.Pipe4A, 0.0f, 0.0f, 0.0f);
        (this.Pipe4B = new ModelRenderer((ModelBase) this, 0, 54)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.Pipe4B.setRotationPoint(-6.0f, 11.0f, -1.0f);
        this.Pipe4B.setTextureSize(64, 64);
        this.Pipe4B.mirror = true;
        this.setRotation(this.Pipe4B, 0.0f, 0.0f, 0.0f);
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
        this.Body.render(f5);
        this.SyringeSlot.render(f5);
        this.Pipe1A.render(f5);
        this.Pipe1B.render(f5);
        this.Pipe2A.render(f5);
        this.Pipe2B.render(f5);
        this.Pipe3A.render(f5);
        this.Pipe3B.render(f5);
        this.Pipe4A.render(f5);
        this.Pipe4B.render(f5);
    }

    private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
