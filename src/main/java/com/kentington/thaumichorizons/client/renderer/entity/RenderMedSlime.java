// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderSlime;

public class RenderMedSlime extends RenderSlime
{
    private static final ResourceLocation slimeTextures;
    
    protected ResourceLocation getEntityTexture(final EntitySlime p_110775_1_) {
        return RenderMedSlime.slimeTextures;
    }
    
    public RenderMedSlime(final ModelBase p_i1267_1_, final ModelBase p_i1267_2_, final float p_i1267_3_) {
        super(p_i1267_1_, p_i1267_2_, p_i1267_3_);
    }
    
    static {
        slimeTextures = new ResourceLocation("thaumichorizons", "textures/entity/medslime.png");
    }
}
