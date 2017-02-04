// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import java.util.Random;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;

public class BlockBone extends Block
{
    public BlockBone() {
        super(Material.rock);
        this.setHardness(2.5f);
        this.setResistance(2.5f);
        this.setBlockName("ThaumicHorizons_bone");
        this.setBlockTextureName("ThaumicHorizons:bone");
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    public Item getItemDropped(final int p_149650_1_, final Random p_149650_2_, final int p_149650_3_) {
        return Items.bone;
    }
    
    public int quantityDroppedWithBonus(final int p_149679_1_, final Random p_149679_2_) {
        return this.quantityDropped(p_149679_2_) + p_149679_2_.nextInt(p_149679_1_ + 1);
    }
    
    public int quantityDropped(final Random p_149745_1_) {
        return 3 + p_149745_1_.nextInt(3);
    }
}
