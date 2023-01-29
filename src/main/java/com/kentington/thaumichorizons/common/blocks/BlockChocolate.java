//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

public class BlockChocolate extends Block {

    public IIcon coloredGrass;

    public BlockChocolate() {
        super(Material.cake);
        this.setHardness(0.5f);
        this.setResistance(0.5f);
        this.setBlockName("ThaumicHorizons_chocolate");
        this.setBlockTextureName("ThaumicHorizons:chocolate");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public void registerBlockIcons(final IIconRegister register) {
        this.blockIcon = register.registerIcon("thaumichorizons:chocolate");
        this.coloredGrass = register.registerIcon("thaumichorizons:grasscolorized");
    }
}
