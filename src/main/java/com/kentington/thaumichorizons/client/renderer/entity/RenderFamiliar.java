//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.ResourceLocation;

import com.kentington.thaumichorizons.common.entities.EntityFamiliar;

import thaumcraft.common.Thaumcraft;

public class RenderFamiliar extends RenderOcelot {

    ResourceLocation rl;

    public RenderFamiliar(final ModelBase p_i1264_1_, final float p_i1264_2_) {
        super(p_i1264_1_, p_i1264_2_);
        this.rl = new ResourceLocation("thaumichorizons", "textures/entity/familiar.png");
    }

    protected ResourceLocation getEntityTexture(final EntityOcelot p_110775_1_) {
        return this.rl;
    }

    public void doRender(final EntityFamiliar p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        super.doRender((EntityOcelot) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        if (p_76986_1_.worldObj.rand.nextFloat() > 0.97f) {
            final float angle = (float) (p_76986_1_.worldObj.rand.nextFloat() * 2.0f * 3.141592653589793);
            Thaumcraft.proxy.sparkle(
                    (float) p_76986_1_.posX + p_76986_1_.width * (float) Math.cos(angle),
                    (float) p_76986_1_.posY + p_76986_1_.height * (p_76986_1_.worldObj.rand.nextFloat() - 0.1f) * 1.2f,
                    (float) p_76986_1_.posZ + p_76986_1_.width * (float) Math.sin(angle),
                    2.0f,
                    0,
                    0.0f);
        }
    }

    public void doRender(final Entity p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        this.doRender((EntityFamiliar) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(final EntityLiving p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        this.doRender((EntityFamiliar) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(final EntityLivingBase p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        this.doRender((EntityFamiliar) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}
