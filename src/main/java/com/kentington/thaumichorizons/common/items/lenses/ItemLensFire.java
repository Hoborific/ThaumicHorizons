// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.items.lenses;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import com.kentington.thaumichorizons.common.lib.PacketRemoveNightvision;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.util.IIcon;
import net.minecraft.item.Item;

public class ItemLensFire extends Item implements ILens
{
    IIcon icon;
    
    public ItemLensFire() {
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    public String lensName() {
        return "LensFire";
    }
    
    public void handleRender(final Minecraft mc, final float partialTicks) {
        final boolean inWater = mc.thePlayer.isInsideOfMaterial(Material.water);
        if (!inWater && (mc.thePlayer.getActivePotionEffect(Potion.nightVision) == null || mc.thePlayer.getActivePotionEffect(Potion.nightVision).getDuration() < 242) && Minecraft.getSystemTime() > LensManager.nightVisionOffTime) {
            LensManager.nightVisionOffTime = Minecraft.getSystemTime();
            mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 255, 0, true));
        }
        else if (inWater) {
            mc.thePlayer.removePotionEffect(Potion.nightVision.id);
        }
    }
    
    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.LensFire";
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:lensfire");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }
    
    public void handleRemoval(final EntityPlayer p) {
        p.removePotionEffect(Potion.nightVision.id);
        PacketHandler.INSTANCE.sendTo((IMessage)new PacketRemoveNightvision(), (EntityPlayerMP)p);
    }
}
