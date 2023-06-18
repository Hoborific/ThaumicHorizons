//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib;

import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderPocketPlane extends WorldProvider {

    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 0.0f);
        this.dimensionId = ThaumicHorizons.dimensionPocketId;
        this.hasNoSky = true;
    }

    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderPocketPlane(this.worldObj, this.worldObj.getSeed());
    }

    public int getAverageGroundLevel() {
        return 128;
    }

    public ChunkCoordinates getEntrancePortalLocation() {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public Vec3 getFogColor(final float p_76562_1_, final float p_76562_2_) {
        return Vec3.createVectorHelper(0.02734375, 0.01171875, 0.16015625);
    }

    protected void generateLightBrightnessTable() {
        final float f = 0.3f;
        for (int i = 0; i <= 15; ++i) {
            final float f2 = 1.0f - i / 15.0f;
            this.lightBrightnessTable[i] = (1.0f - f2) / (f2 * 3.0f + 1.0f) * (1.0f - f) + f;
        }
    }

    public boolean isSurfaceWorld() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float[] calcSunriseSunsetColors(final float p_76560_1_, final float p_76560_2_) {
        return null;
    }

    public String getWelcomeMessage() {
        return "Entering pocket plane...";
    }

    public String getDepartMessage() {
        return "Leaving pocket plane...";
    }

    public boolean shouldMapSpin(final String entity, final double x, final double y, final double z) {
        return true;
    }

    public boolean canBlockFreeze(final int x, final int y, final int z, final boolean byWater) {
        return false;
    }

    public boolean canSnowAt(final int x, final int y, final int z, final boolean checkLight) {
        return false;
    }

    public boolean canDoLightning(final Chunk chunk) {
        return false;
    }

    public boolean canDoRainSnowIce(final Chunk chunk) {
        return false;
    }

    public boolean canCoordinateBeSpawn(final int p_76566_1_, final int p_76566_2_) {
        return this.worldObj.getTopBlock(p_76566_1_, p_76566_2_).getMaterial().blocksMovement();
    }

    public float calculateCelestialAngle(final long p_76563_1_, final float p_76563_3_) {
        return 0.5f;
    }

    public boolean canRespawnHere() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean doesXZShowFog(final int p_76568_1_, final int p_76568_2_) {
        return false;
    }

    public String getDimensionName() {
        return "Pocket Plane";
    }
}
