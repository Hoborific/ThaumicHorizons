//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionShock extends Potion {
    public static PotionShock instance;
    private int statusIconIndex;
    static final ResourceLocation rl;

    public PotionShock(final int par1, final boolean par2, final int par3) {
        super(par1, par2, par3);
        this.setIconIndex(this.statusIconIndex = 0, 0);
    }

    public static void init() {
        PotionShock.instance.setPotionName("potion.shock");
        PotionShock.instance.setIconIndex(0, 0);
        PotionShock.instance.setEffectiveness(0.25);
    }

    public boolean isBadEffect() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(PotionShock.rl);
        return super.getStatusIconIndex();
    }

    public void performEffect(final EntityLivingBase player, final int par2) {}

    static {
        PotionShock.instance = null;
        rl = new ResourceLocation("thaumichorizons", "textures/misc/potions.png");
    }
}
