// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.client.fx;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;
import java.awt.Color;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.client.particle.EntityFX;

public class FXEssentiaBubble extends EntityFX
{
    private int count;
    private int delay;
    public int particle;
    
    public FXEssentiaBubble(final World par1World, final double par2, final double par4, final double par6, final int count, final int color, final float scale, final int delay) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.count = 0;
        this.delay = 0;
        this.particle = 24;
        final float particleRed = 0.6f;
        this.particleBlue = particleRed;
        this.particleGreen = particleRed;
        this.particleRed = particleRed;
        this.particleScale = (MathHelper.sin(count / 2.0f) * 0.1f + 1.0f) * scale;
        this.delay = delay;
        this.count = count;
        this.particleMaxAge = 20 + this.rand.nextInt(20);
        this.motionY = 0.025f + MathHelper.sin(count / 3.0f) * 0.002f;
        final double n = 0.0;
        this.motionZ = n;
        this.motionX = n;
        final Color c = new Color(color);
        final float mr = c.getRed() / 255.0f * 0.2f;
        final float mg = c.getGreen() / 255.0f * 0.2f;
        final float mb = c.getBlue() / 255.0f * 0.2f;
        this.particleRed = c.getRed() / 255.0f - mr + this.rand.nextFloat() * mr;
        this.particleGreen = c.getGreen() / 255.0f - mg + this.rand.nextFloat() * mg;
        this.particleBlue = c.getBlue() / 255.0f - mb + this.rand.nextFloat() * mb;
        this.particleGravity = 0.2f;
        this.noClip = false;
    }
    
    public void renderParticle(final Tessellator tessellator, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        if (this.delay > 0) {
            return;
        }
        final float t2 = 0.5625f;
        final float t3 = 0.625f;
        final float t4 = 0.0625f;
        final float t5 = 0.125f;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
        final int part = this.particle + this.particleAge % 16;
        final float s = MathHelper.sin((this.particleAge - this.count) / 5.0f) * 0.25f + 1.0f;
        final float var12 = 0.1f * this.particleScale * s;
        final float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - FXEssentiaBubble.interpPosX);
        final float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - FXEssentiaBubble.interpPosY);
        final float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - FXEssentiaBubble.interpPosZ);
        final float var16 = 1.0f;
        tessellator.setBrightness(240);
        tessellator.setColorRGBA_F(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 0.5f);
        tessellator.addVertexWithUV((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)t2, (double)t5);
        tessellator.addVertexWithUV((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)t3, (double)t5);
        tessellator.addVertexWithUV((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)t3, (double)t4);
        tessellator.addVertexWithUV((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)t2, (double)t4);
    }
    
    public int getFXLayer() {
        return 1;
    }
    
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.delay > 0) {
            --this.delay;
            return;
        }
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
            return;
        }
        this.motionY += 0.00125;
        this.particleScale *= 1.05f;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionY *= 0.985;
    }
}
