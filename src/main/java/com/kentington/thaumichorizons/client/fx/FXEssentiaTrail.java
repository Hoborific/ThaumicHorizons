//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.fx;

import java.awt.Color;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class FXEssentiaTrail extends EntityFX {

    private final double targetX;
    private final double targetY;
    private final double targetZ;
    private int count;
    public int particle;

    public FXEssentiaTrail(final World par1World, final double par2, final double par4, final double par6,
            final double tx, final double ty, final double tz, final int count, final int color, final float scale) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.count = 0;
        this.particle = 24;
        final float particleRed = 0.6f;
        this.particleBlue = particleRed;
        this.particleGreen = particleRed;
        this.particleRed = particleRed;
        this.particleScale = (MathHelper.sin(count / 2.0f) * 0.1f + 1.0f) * scale;
        this.count = count;
        this.targetX = tx;
        this.targetY = ty;
        this.targetZ = tz;
        final double dx = tx - this.posX;
        final double dy = ty - this.posY;
        final double dz = tz - this.posZ;
        this.particleMaxAge = 20 + this.rand.nextInt(20);
        this.motionX = MathHelper.sin(count / 4.0f) * 0.003f + this.rand.nextGaussian() * 0.002000000094994903;
        this.motionY = 0.1f + MathHelper.sin(count / 3.0f) * 0.002f;
        this.motionZ = MathHelper.sin(count / 2.0f) * 0.003f + this.rand.nextGaussian() * 0.002000000094994903;
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

    public void renderParticle(final Tessellator tessellator, final float f, final float f1, final float f2,
            final float f3, final float f4, final float f5) {
        final float t2 = 0.5625f;
        final float t3 = 0.625f;
        final float t4 = 0.0625f;
        final float t5 = 0.125f;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
        final int part = this.particle + this.particleAge % 16;
        final float s = MathHelper.sin((this.particleAge - this.count) / 5.0f) * 0.25f + 1.0f;
        final float var12 = 0.1f * this.particleScale * s;
        final float var13 = (float) (this.prevPosX + (this.posX - this.prevPosX) * f - FXEssentiaTrail.interpPosX);
        final float var14 = (float) (this.prevPosY + (this.posY - this.prevPosY) * f - FXEssentiaTrail.interpPosY);
        final float var15 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * f - FXEssentiaTrail.interpPosZ);
        final float var16 = 1.0f;
        tessellator.setBrightness(240);
        tessellator
                .setColorRGBA_F(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 0.5f);
        tessellator.addVertexWithUV(
                (double) (var13 - f1 * var12 - f4 * var12),
                (double) (var14 - f2 * var12),
                (double) (var15 - f3 * var12 - f5 * var12),
                (double) t2,
                (double) t5);
        tessellator.addVertexWithUV(
                (double) (var13 - f1 * var12 + f4 * var12),
                (double) (var14 + f2 * var12),
                (double) (var15 - f3 * var12 + f5 * var12),
                (double) t3,
                (double) t5);
        tessellator.addVertexWithUV(
                (double) (var13 + f1 * var12 + f4 * var12),
                (double) (var14 + f2 * var12),
                (double) (var15 + f3 * var12 + f5 * var12),
                (double) t3,
                (double) t4);
        tessellator.addVertexWithUV(
                (double) (var13 + f1 * var12 - f4 * var12),
                (double) (var14 - f2 * var12),
                (double) (var15 + f3 * var12 - f5 * var12),
                (double) t2,
                (double) t4);
    }

    public int getFXLayer() {
        return 1;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
            return;
        }
        this.motionY += 0.01 * this.particleGravity;
        if (!this.noClip) {
            this.pushOutOfBlocks(this.posX, this.posY, this.posZ);
        }
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.985;
        this.motionY *= 0.985;
        this.motionZ *= 0.985;
        this.motionX = MathHelper.clamp_float((float) this.motionX, -0.05f, 0.05f);
        this.motionY = MathHelper.clamp_float((float) this.motionY, -0.05f, 0.05f);
        this.motionZ = MathHelper.clamp_float((float) this.motionZ, -0.05f, 0.05f);
        double dx = this.targetX - this.posX;
        double dy = this.targetY - this.posY;
        double dz = this.targetZ - this.posZ;
        final double d13 = 0.01;
        final double d14 = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
        if (d14 < 2.0) {
            this.particleScale *= 0.98f;
        }
        if (this.particleScale < 0.2f) {
            this.setDead();
            return;
        }
        dx /= d14;
        dy /= d14;
        dz /= d14;
        this.motionX += dx * (d13 / Math.min(1.0, d14));
        this.motionY += dy * (d13 / Math.min(1.0, d14));
        this.motionZ += dz * (d13 / Math.min(1.0, d14));
    }

    public void setGravity(final float value) {
        this.particleGravity = value;
    }

    protected boolean pushOutOfBlocks(final double par1, final double par3, final double par5) {
        final int var7 = MathHelper.floor_double(par1);
        final int var8 = MathHelper.floor_double(par3);
        final int var9 = MathHelper.floor_double(par5);
        final double var10 = par1 - var7;
        final double var11 = par3 - var8;
        final double var12 = par5 - var9;
        if (!this.worldObj.isAirBlock(var7, var8, var9)
                && this.worldObj.isBlockNormalCubeDefault(var7, var8, var9, true)
                && !this.worldObj.isAnyLiquid(this.boundingBox)) {
            final boolean var13 = !this.worldObj.isBlockNormalCubeDefault(var7 - 1, var8, var9, true);
            final boolean var14 = !this.worldObj.isBlockNormalCubeDefault(var7 + 1, var8, var9, true);
            final boolean var15 = !this.worldObj.isBlockNormalCubeDefault(var7, var8 - 1, var9, true);
            final boolean var16 = !this.worldObj.isBlockNormalCubeDefault(var7, var8 + 1, var9, true);
            final boolean var17 = !this.worldObj.isBlockNormalCubeDefault(var7, var8, var9 - 1, true);
            final boolean var18 = !this.worldObj.isBlockNormalCubeDefault(var7, var8, var9 + 1, true);
            byte var19 = -1;
            double var20 = 9999.0;
            if (var13 && var10 < var20) {
                var20 = var10;
                var19 = 0;
            }
            if (var14 && 1.0 - var10 < var20) {
                var20 = 1.0 - var10;
                var19 = 1;
            }
            if (var15 && var11 < var20) {
                var20 = var11;
                var19 = 2;
            }
            if (var16 && 1.0 - var11 < var20) {
                var20 = 1.0 - var11;
                var19 = 3;
            }
            if (var17 && var12 < var20) {
                var20 = var12;
                var19 = 4;
            }
            if (var18 && 1.0 - var12 < var20) {
                var20 = 1.0 - var12;
                var19 = 5;
            }
            final float var21 = this.rand.nextFloat() * 0.05f + 0.025f;
            final float var22 = (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1f;
            if (var19 == 0) {
                this.motionX = -var21;
                this.motionZ = var22;
                this.motionY = var22;
            }
            if (var19 == 1) {
                this.motionX = var21;
                this.motionZ = var22;
                this.motionY = var22;
            }
            if (var19 == 2) {
                this.motionY = -var21;
                this.motionZ = var22;
                this.motionX = var22;
            }
            if (var19 == 3) {
                this.motionY = var21;
                this.motionZ = var22;
                this.motionX = var22;
            }
            if (var19 == 4) {
                this.motionZ = -var21;
                this.motionX = var22;
                this.motionY = var22;
            }
            if (var19 == 5) {
                this.motionZ = var21;
                this.motionX = var22;
                this.motionY = var22;
            }
            return true;
        }
        return false;
    }
}
