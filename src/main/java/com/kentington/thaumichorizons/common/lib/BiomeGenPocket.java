// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import java.util.Random;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.config.ConfigBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenPocket extends BiomeGenBase
{
    public BiomeGenPocket(final int inty) {
        super(inty);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.topBlock = ConfigBlocks.blockEldritchNothing;
        this.fillerBlock = ConfigBlocks.blockEldritchNothing;
        this.setBiomeName("Pocket Plane");
        this.setDisableRain();
    }
    
    @SideOnly(Side.CLIENT)
    public int getSkyColorByTemp(final float p_76731_1_) {
        return 0;
    }
    
    public void decorate(final World world, final Random random, final int x, final int z) {
    }
    
    public BiomeGenBase createMutation() {
        return null;
    }
}
