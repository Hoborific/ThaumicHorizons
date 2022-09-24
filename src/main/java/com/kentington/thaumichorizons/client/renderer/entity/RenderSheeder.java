//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.common.entities.EntitySheeder;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

public class RenderSheeder extends RenderSpider {
    private static final ResourceLocation spiderTextures;
    private static final ResourceLocation spiderEyesTextures;

    protected ResourceLocation getEntityTexture(final EntitySpider p_110775_1_) {
        return RenderSheeder.spiderTextures;
    }

    protected int shouldRenderPass(final EntitySpider p_77032_1_, final int p_77032_2_, final float p_77032_3_) {
        if (p_77032_2_ != 0) {
            return -1;
        }
        if (!((EntitySheeder) p_77032_1_).getSheared()) {
            this.bindTexture(RenderSheeder.spiderEyesTextures);
            return 1;
        }
        return -1;
    }

    static {
        spiderTextures = new ResourceLocation("thaumichorizons", "textures/entity/sheeder.png");
        spiderEyesTextures = new ResourceLocation("thaumichorizons", "textures/entity/sheederoverlay.png");
    }
}
