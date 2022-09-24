//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockVatSolid extends BlockVat {
    public IIcon iconLidCenterTop;
    public IIcon iconLidSideCenter;
    public IIcon iconLidLeftRight;
    public IIcon iconInnerCenter;
    public IIcon iconInnerPosZ;
    public IIcon iconInnerNegZ;
    public IIcon iconInnerPosX;
    public IIcon iconInnerNegX;
    public IIcon iconInnerCornerA;
    public IIcon iconInnerCornerB;
    public IIcon iconInnerCornerC;
    public IIcon iconInnerCornerD;
    public IIcon iconBaseCenter;
    public IIcon iconBaseLeftRight;
    public IIcon iconBaseSide;
    public IIcon iconBaseSideBottom;
    public IIcon iconGreatwood;

    public BlockVatSolid() {
        super(Material.wood);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderBlockPass() {
        return 0;
    }

    @Override
    public int getRenderType() {
        return ThaumicHorizons.blockVatSolidRI;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(final IIconRegister ir) {
        this.iconLidCenterTop = ir.registerIcon("thaumichorizons:vatlidtopcenter");
        this.iconLidSideCenter = ir.registerIcon("thaumichorizons:vatlidcenter");
        this.iconLidLeftRight = ir.registerIcon("thaumichorizons:vatlidleftright");
        this.iconInnerCenter = ir.registerIcon("thaumichorizons:vatinnercenter");
        this.iconInnerPosZ = ir.registerIcon("thaumichorizons:vatinnerposz");
        this.iconInnerNegZ = ir.registerIcon("thaumichorizons:vatinnernegz");
        this.iconInnerPosX = ir.registerIcon("thaumichorizons:vatinnerposx");
        this.iconInnerNegX = ir.registerIcon("thaumichorizons:vatinnernegx");
        this.iconInnerCornerA = ir.registerIcon("thaumichorizons:vatinnercornera");
        this.iconInnerCornerB = ir.registerIcon("thaumichorizons:vatinnercornerb");
        this.iconInnerCornerC = ir.registerIcon("thaumichorizons:vatinnercornerc");
        this.iconInnerCornerD = ir.registerIcon("thaumichorizons:vatinnercornerd");
        this.iconBaseCenter = ir.registerIcon("thaumichorizons:vatbasecenter");
        this.iconBaseLeftRight = ir.registerIcon("thaumichorizons:vatbaseleftright");
        this.iconBaseSide = ir.registerIcon("thaumichorizons:vatbasesidecenter");
        this.iconBaseSideBottom = ir.registerIcon("thaumichorizons:vatbasesidebottom");
        this.iconGreatwood = ir.registerIcon("thaumcraft:planks_greatwood");
    }

    @Override
    public IIcon getIcon(final int par1, final int par2) {
        return this.iconGreatwood;
    }
}
