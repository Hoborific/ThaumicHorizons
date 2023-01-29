//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class RenderOreBoar extends RenderPig {

    private static final ResourceLocation pigTextures;

    public RenderOreBoar(final ModelBase p_i1265_1_, final ModelBase p_i1265_2_, final float p_i1265_3_) {
        super(p_i1265_1_, p_i1265_2_, p_i1265_3_);
    }

    protected ResourceLocation getEntityTexture(final EntityPig p_110775_1_) {
        return RenderOreBoar.pigTextures;
    }

    static {
        pigTextures = new ResourceLocation("thaumichorizons", "textures/entity/oreboar.png");
    }
}
