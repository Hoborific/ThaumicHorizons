//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PotionVacuum extends Potion {

    public static PotionVacuum instance;
    private int statusIconIndex;
    static final ResourceLocation rl;

    public PotionVacuum(final int par1, final boolean par2, final int par3) {
        super(par1, par2, par3);
        this.setIconIndex(this.statusIconIndex = 2, 0);
    }

    public static void init() {
        PotionVacuum.instance.setPotionName("potion.vacuum");
        PotionVacuum.instance.setIconIndex(2, 0);
        PotionVacuum.instance.setEffectiveness(0.25);
    }

    public boolean isBadEffect() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(PotionVacuum.rl);
        return super.getStatusIconIndex();
    }

    public void performEffect(final EntityLivingBase player, final int par2) {}

    static {
        PotionVacuum.instance = null;
        rl = new ResourceLocation("thaumichorizons", "textures/misc/potions.png");
    }
}
