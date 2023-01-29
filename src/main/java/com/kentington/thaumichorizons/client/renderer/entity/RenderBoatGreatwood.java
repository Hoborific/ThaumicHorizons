//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import com.kentington.thaumichorizons.common.entities.EntityBoatGreatwood;

public class RenderBoatGreatwood extends RenderBoat {

    private static final ResourceLocation boatTextures;

    protected ResourceLocation getEntityTexture(final EntityBoatGreatwood p_110775_1_) {
        return RenderBoatGreatwood.boatTextures;
    }

    protected ResourceLocation getEntityTexture(final Entity p_110775_1_) {
        return this.getEntityTexture((EntityBoatGreatwood) p_110775_1_);
    }

    static {
        boatTextures = new ResourceLocation("thaumichorizons", "textures/entity/boatgreatwood.png");
    }
}
