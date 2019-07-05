// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.lib.networking.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import java.util.List;
import java.util.ArrayList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import thaumcraft.api.nodes.IRevealer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

public class THKeyHandler
{
    public KeyBinding keyV;
    public KeyBinding keyM;
    public KeyBinding keyC;
    public KeyBinding keyX;
    private boolean keyPressedM;
    public static long lastPressM;
    private boolean keyPressedC;
    public static long lastPressC;
    private boolean keyPressedX;
    public static long lastPressX;
    private boolean keyPressedV;
    public static boolean radialActive;
    public static boolean radialLock;
    public static long lastPressV;
    
    public THKeyHandler() {
        this.keyV = new KeyBinding("Change Arcane Lens", 47, "key.categories.misc");
        this.keyM = new KeyBinding("Activate Morphic Fingers", 49, "key.categories.misc");
        this.keyC = new KeyBinding("Toggle Spider Climb", 46, "key.categories.misc");
        this.keyX = new KeyBinding("Toggle Chameleon Skin", 45, "key.categories.misc");
        this.keyPressedM = false;
        this.keyPressedC = false;
        this.keyPressedX = false;
        this.keyPressedV = false;
        ClientRegistry.registerKeyBinding(this.keyV);
        ClientRegistry.registerKeyBinding(this.keyM);
        ClientRegistry.registerKeyBinding(this.keyC);
        ClientRegistry.registerKeyBinding(this.keyX);
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void playerTick(final TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        if (event.phase == TickEvent.Phase.START) {
            if (this.keyV.getIsKeyPressed()) {
                if (FMLClientHandler.instance().getClient().inGameHasFocus) {
                    final EntityPlayer player = event.player;
                    if (player != null) {
                        if (!this.keyPressedV) {
                            THKeyHandler.lastPressV = System.currentTimeMillis();
                            THKeyHandler.radialLock = false;
                        }
                        if (!THKeyHandler.radialLock && player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() instanceof IRevealer) {
                            if (player.isSneaking()) {
                                PacketHandler.INSTANCE.sendToServer((IMessage)new PacketLensChangeToServer(player, "REMOVE"));
                            }
                            else {
                                THKeyHandler.radialActive = true;
                            }
                        }
                    }
                    this.keyPressedV = true;
                }
            }
            else {
                THKeyHandler.radialActive = false;
                if (this.keyPressedV) {
                    THKeyHandler.lastPressV = System.currentTimeMillis();
                }
                this.keyPressedV = false;
            }
            if (this.keyM.getIsKeyPressed()) {
                if (FMLClientHandler.instance().getClient().inGameHasFocus) {
                    final EntityPlayer player = event.player;
                    if (player != null) {
                        if (!this.keyPressedM) {
                            THKeyHandler.lastPressM = System.currentTimeMillis();
                        }
                        if (((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).hasPlayerInfusion(2) && !this.keyPressedM) {
                            player.openGui((Object)ThaumicHorizons.instance, 9, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
                            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketFingersToServer(player, player.dimension));
                        }
                    }
                    this.keyPressedM = true;
                }
            }
            else {
                if (this.keyPressedM) {
                    THKeyHandler.lastPressM = System.currentTimeMillis();
                }
                this.keyPressedM = false;
            }
            if (this.keyC.getIsKeyPressed()) {
                if (FMLClientHandler.instance().getClient().inGameHasFocus) {
                    final EntityPlayer player = event.player;
                    if (player != null) {
                        if (!this.keyPressedC) {
                            THKeyHandler.lastPressC = System.currentTimeMillis();
                        }
                        if (((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).hasPlayerInfusion(9) && !this.keyPressedC) {
                            ((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).toggleClimb = !((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).toggleClimb;
                            if (((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).toggleClimb) {
                                player.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + "Spider Climb disabled."));
                            }
                            else {
                                player.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + "Spider Climb enabled."));
                            }
                            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketToggleClimbToServer(player, player.dimension));
                        }
                    }
                    this.keyPressedC = true;
                }
            }
            else {
                if (this.keyPressedC) {
                    THKeyHandler.lastPressC = System.currentTimeMillis();
                }
                this.keyPressedC = false;
            }
            if (this.keyX.getIsKeyPressed()) {
                if (FMLClientHandler.instance().getClient().inGameHasFocus) {
                    final EntityPlayer player = event.player;
                    if (player != null) {
                        if (!this.keyPressedX) {
                            THKeyHandler.lastPressX = System.currentTimeMillis();
                        }
                        if (((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).hasPlayerInfusion(10) && !this.keyPressedX) {
                            ((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).toggleInvisible = !((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).toggleInvisible;
                            if (((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).toggleInvisible) {
                                player.removePotionEffectClient(Potion.invisibility.id);
                                player.setInvisible(false);
                                player.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + "Chameleon Skin disabled."));
                            }
                            else {
                                final PotionEffect effect = new PotionEffect(Potion.invisibility.id, Integer.MAX_VALUE, 0, true);
                                effect.setCurativeItems((List)new ArrayList());
                                player.addPotionEffect(effect);
                                player.setInvisible(true);
                                player.addChatMessage((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + "Chameleon Skin enabled."));
                            }
                            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketToggleInvisibleToServer(player, player.dimension));
                        }
                    }
                    this.keyPressedX = true;
                }
            }
            else {
                if (this.keyPressedX) {
                    THKeyHandler.lastPressX = System.currentTimeMillis();
                }
                this.keyPressedX = false;
            }
        }
    }
    
    static {
        THKeyHandler.lastPressM = 0L;
        THKeyHandler.lastPressC = 0L;
        THKeyHandler.lastPressX = 0L;
        THKeyHandler.radialActive = false;
        THKeyHandler.radialLock = false;
        THKeyHandler.lastPressV = 0L;
    }
}
