//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderPocketPlane implements IChunkProvider {

    private Random rand;
    private World worldObj;
    private WorldType worldType;
    private BiomeGenBase[] biomesForGeneration;

    public ChunkProviderPocketPlane(final World worldObj, final long seed) {
        this.worldObj = worldObj;
        this.worldType = worldObj.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);
    }

    public boolean chunkExists(final int p_73149_1_, final int p_73149_2_) {
        return true;
    }

    public Chunk provideChunk(final int p_73154_1_, final int p_73154_2_) {
        this.rand.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
        final Block[] ablock = new Block[32768];
        final byte[] meta = new byte[ablock.length];
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                for (int j1 = 127; j1 >= 0; --j1) {
                    final int k2 = (l * 16 + k) * 128 + j1;
                    ablock[k2] = null;
                    meta[k2] = 0;
                }
            }
        }
        this.biomesForGeneration = this.worldObj.getWorldChunkManager()
                .loadBlockGeneratorData(this.biomesForGeneration, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
        final Chunk chunk = new Chunk(this.worldObj, ablock, meta, p_73154_1_, p_73154_2_);
        final byte[] abyte = chunk.getBiomeArray();
        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte) this.biomesForGeneration[i].biomeID;
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    public Chunk loadChunk(final int p_73158_1_, final int p_73158_2_) {
        return this.provideChunk(p_73158_1_, p_73158_2_);
    }

    public void populate(final IChunkProvider p_73153_1_, final int p_73153_2_, final int p_73153_3_) {}

    public boolean saveChunks(final boolean p_73151_1_, final IProgressUpdate p_73151_2_) {
        return true;
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "RandomLevelSource";
    }

    public List getPossibleCreatures(final EnumCreatureType p_73155_1_, final int p_73155_2_, final int p_73155_3_,
            final int p_73155_4_) {
        return new ArrayList();
    }

    public ChunkPosition func_147416_a(final World p_147416_1_, final String p_147416_2_, final int p_147416_3_,
            final int p_147416_4_, final int p_147416_5_) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(final int p_82695_1_, final int p_82695_2_) {}

    public void saveExtraData() {}
}
