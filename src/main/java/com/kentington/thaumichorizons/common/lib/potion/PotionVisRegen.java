// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib.potion;

import net.minecraft.entity.EntityLivingBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.Potion;

public class PotionVisRegen extends Potion
{
    public static PotionVisRegen instance;
    private int statusIconIndex;
    static final ResourceLocation rl;
    
    public PotionVisRegen(final int par1, final boolean par2, final int par3) {
        super(par1, par2, par3);
        this.setIconIndex(this.statusIconIndex = 3, 0);
    }
    
    public static void init() {
        PotionVisRegen.instance.setPotionName("potion.visregen");
        PotionVisRegen.instance.setIconIndex(3, 0);
        PotionVisRegen.instance.setEffectiveness(0.25);
    }
    
    public boolean isBadEffect() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(PotionVisRegen.rl);
        return super.getStatusIconIndex();
    }
    
    public void performEffect(final EntityLivingBase target, final int par2) {
    }
    
    static {
        PotionVisRegen.instance = null;
        rl = new ResourceLocation("thaumichorizons", "textures/misc/potions.png");
    }
}
