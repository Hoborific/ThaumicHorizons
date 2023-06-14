//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;

import com.kentington.thaumichorizons.common.entities.EntityGoldChicken;

import thaumcraft.common.Thaumcraft;

public class RenderGoldChicken extends RenderChicken {

    ResourceLocation rl;

    public RenderGoldChicken(final ModelBase p_i1252_1_, final float p_i1252_2_) {
        super(p_i1252_1_, p_i1252_2_);
        this.rl = new ResourceLocation("thaumichorizons", "textures/entity/goldchicken.png");
    }

    protected ResourceLocation getEntityTexture(final EntityChicken p_110775_1_) {
        return this.rl;
    }

    public void doRender(final EntityGoldChicken p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        super.doRender((EntityChicken) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        if (p_76986_1_.worldObj.rand.nextFloat() > 0.95f) {
            final float angle = (float) (p_76986_1_.worldObj.rand.nextFloat() * 2.0f * 3.141592653589793);
            Thaumcraft.proxy.sparkle(
                    (float) p_76986_1_.posX + p_76986_1_.width * (float) Math.cos(angle),
                    (float) p_76986_1_.posY + p_76986_1_.height * (p_76986_1_.worldObj.rand.nextFloat() - 0.1f) * 1.2f,
                    (float) p_76986_1_.posZ + p_76986_1_.width * (float) Math.sin(angle),
                    2.0f,
                    1,
                    0.0f);
        }
    }

    public void doRender(final Entity p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        this.doRender((EntityGoldChicken) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(final EntityLiving p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        this.doRender((EntityGoldChicken) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(final EntityLivingBase p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        this.doRender((EntityGoldChicken) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}
