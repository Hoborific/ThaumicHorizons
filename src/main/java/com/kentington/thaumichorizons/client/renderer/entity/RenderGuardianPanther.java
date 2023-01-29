//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.entity.passive.EntityOcelot;

import org.lwjgl.opengl.GL11;

public class RenderGuardianPanther extends RenderOcelot {

    public RenderGuardianPanther(final ModelBase p_i1264_1_, final float p_i1264_2_) {
        super(p_i1264_1_, p_i1264_2_);
    }

    protected void preRenderCallback(final EntityOcelot p_77041_1_, final float p_77041_2_) {
        super.preRenderCallback(p_77041_1_, p_77041_2_);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }
}
