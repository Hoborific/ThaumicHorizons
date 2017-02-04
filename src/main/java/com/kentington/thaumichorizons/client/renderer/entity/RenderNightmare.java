// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.renderer.entity;

import com.google.common.collect.Maps;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.client.model.ModelBase;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderHorse;

public class RenderNightmare extends RenderHorse
{
    ResourceLocation enderTex;
    ResourceLocation enderTexIron;
    ResourceLocation enderTexGold;
    ResourceLocation enderTexDiamond;
    private static final Map field_110852_a;
    
    public RenderNightmare(final ModelBase p_i1256_1_, final float p_i1256_2_) {
        super(p_i1256_1_, p_i1256_2_);
        this.enderTex = new ResourceLocation("thaumichorizons", "textures/entity/nightmare.png");
        this.enderTexIron = new ResourceLocation("thaumichorizons", "textures/entity/nightmareiron.png");
        this.enderTexGold = new ResourceLocation("thaumichorizons", "textures/entity/nightmaregold.png");
        this.enderTexDiamond = new ResourceLocation("thaumichorizons", "textures/entity/nightmarediamond.png");
    }
    
    protected ResourceLocation getEntityTexture(final EntityHorse p_110775_1_) {
        switch (p_110775_1_.func_110241_cb()) {
            case 1: {
                return this.enderTexIron;
            }
            case 2: {
                return this.enderTexGold;
            }
            case 3: {
                return this.enderTexDiamond;
            }
            default: {
                return this.enderTex;
            }
        }
    }
    
    static {
        field_110852_a = Maps.newHashMap();
    }
}
