// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.entity.passive.EntityWolf;
import com.kentington.thaumichorizons.common.entities.EntityNetherHound;
import net.minecraft.entity.Entity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderWolf;

public class RenderNetherHound extends RenderWolf
{
    ResourceLocation wolfTex;
    
    public RenderNetherHound(final ModelBase p_i1269_1_, final ModelBase p_i1269_2_, final float p_i1269_3_) {
        super(p_i1269_1_, p_i1269_2_, p_i1269_3_);
        this.wolfTex = new ResourceLocation("thaumichorizons", "textures/entity/netherhound.png");
    }
    
    protected ResourceLocation getEntityTexture(final Entity p_110775_1_) {
        return this.getEntityTexture((EntityNetherHound)p_110775_1_);
    }
    
    protected ResourceLocation getEntityTexture(final EntityWolf p_110775_1_) {
        return this.getEntityTexture((EntityNetherHound)p_110775_1_);
    }
    
    protected ResourceLocation getEntityTexture(final EntityNetherHound p_110775_1_) {
        return this.wolfTex;
    }
}
