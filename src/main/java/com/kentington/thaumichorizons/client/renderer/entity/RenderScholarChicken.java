// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderChicken;

public class RenderScholarChicken extends RenderChicken
{
    ResourceLocation rl;
    
    public RenderScholarChicken(final ModelBase p_i1252_1_, final float p_i1252_2_) {
        super(p_i1252_1_, p_i1252_2_);
        this.rl = new ResourceLocation("thaumichorizons", "textures/entity/scholarchicken.png");
    }
    
    protected ResourceLocation getEntityTexture(final EntityChicken p_110775_1_) {
        return this.rl;
    }
}
