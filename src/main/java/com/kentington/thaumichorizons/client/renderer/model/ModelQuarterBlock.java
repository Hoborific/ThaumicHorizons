//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelQuarterBlock extends ModelBase {
    public ModelRenderer block;

    public ModelQuarterBlock() {
        (this.block = new ModelRenderer((ModelBase) this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.block.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.block.setTextureSize(64, 32);
        this.block.mirror = true;
        this.textureWidth = 64;
        this.textureHeight = 32;
    }

    public void render() {
        this.block.render(0.0625f);
    }
}
