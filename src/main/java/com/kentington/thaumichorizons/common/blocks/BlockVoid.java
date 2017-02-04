// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.client.renderer.texture.IIconRegister;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import thaumcraft.common.config.Config;
import net.minecraft.block.Block;

public class BlockVoid extends Block
{
    public BlockVoid() {
        super(Config.airyMaterial);
        this.setHardness(-1.0f);
        this.setResistance(60000.0f);
        this.setBlockName("ThaumicHorizons_void");
        this.setBlockTextureName("ThaumicHorizons:void");
        this.setStepSound(new Block.SoundType("cloth", 0.0f, 1.0f));
        this.setLightLevel(1.0f);
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    public void registerBlockIcons(final IIconRegister register) {
        this.blockIcon = register.registerIcon("thaumichorizons:void");
    }
}
