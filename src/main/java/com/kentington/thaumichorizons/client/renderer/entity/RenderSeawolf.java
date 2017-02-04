// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.entity.passive.EntityWolf;
import com.kentington.thaumichorizons.common.entities.EntitySeawolf;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderWolf;

public class RenderSeawolf extends RenderWolf
{
    ResourceLocation wolfTex;
    
    public RenderSeawolf(final ModelBase p_i1269_1_, final ModelBase p_i1269_2_, final float p_i1269_3_) {
        super(p_i1269_1_, p_i1269_2_, p_i1269_3_);
        this.wolfTex = new ResourceLocation("thaumichorizons", "textures/entity/seawolf.png");
    }
    
    protected ResourceLocation getEntityTexture(final Entity p_110775_1_) {
        return this.getEntityTexture((EntitySeawolf)p_110775_1_);
    }
    
    protected ResourceLocation getEntityTexture(final EntityWolf p_110775_1_) {
        return this.getEntityTexture((EntitySeawolf)p_110775_1_);
    }
    
    protected ResourceLocation getEntityTexture(final EntitySeawolf p_110775_1_) {
        return this.wolfTex;
    }
}
