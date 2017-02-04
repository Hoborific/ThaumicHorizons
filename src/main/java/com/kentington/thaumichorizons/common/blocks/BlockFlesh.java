// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import java.util.Random;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import thaumcraft.common.config.Config;
import net.minecraft.block.Block;

public class BlockFlesh extends Block
{
    public BlockFlesh() {
        super(Config.taintMaterial);
        this.setHardness(0.5f);
        this.setResistance(0.5f);
        this.setBlockName("ThaumicHorizons_flesh");
        this.setBlockTextureName("thaumcraft:fleshblock");
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
