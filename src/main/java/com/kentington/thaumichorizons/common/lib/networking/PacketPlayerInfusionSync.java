// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib.networking;

import java.util.ArrayList;
import java.util.List;
import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketPlayerInfusionSync implements IMessage, IMessageHandler<PacketPlayerInfusionSync, IMessage>
{
    int[] infusions;
    String name;
    boolean toggleClimb;
    boolean toggleInvisible;
    
    public PacketPlayerInfusionSync() {
        this.infusions = new int[10];
        this.name = "";
        this.toggleClimb = false;
        this.toggleInvisible = false;
    }
    
    public PacketPlayerInfusionSync(final String name, final int[] infusions, final boolean toggleClimb, final boolean toggleInvisible) {
        this.infusions = new int[10];
        this.name = "";
        this.name = name;
        this.infusions = infusions;
        this.toggleClimb = toggleClimb;
        this.toggleInvisible = toggleInvisible;
    }
    
    public IMessage onMessage(final PacketPlayerInfusionSync message, final MessageContext ctx) {
        if (Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().theWorld.getPlayerEntityByName(message.name) != null && Minecraft.getMinecraft().theWorld.getPlayerEntityByName(message.name).getExtendedProperties("CreatureInfusion") != null) {
            EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(message.name);
            EntityInfusionProperties prop = (EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion");
            prop.playerInfusions = message.infusions;
            if (prop.toggleClimb != message.toggleClimb) {
                prop.toggleClimb = message.toggleClimb;
            }
            if (prop.toggleInvisible != message.toggleInvisible) {
                prop.toggleInvisible = message.toggleInvisible;
                if (prop.toggleInvisible) {
                    player.removePotionEffectClient(Potion.invisibility.id);
                    player.setInvisible(false);
                }
                else {
                    final PotionEffect effect = new PotionEffect(Potion.invisibility.id, Integer.MAX_VALUE, 0, true);
                    effect.setCurativeItems((List)new ArrayList());
                    player.addPotionEffect(effect);
                    player.setInvisible(true);
                }
            }
        }
        return null;
    }
    
    public void fromBytes(final ByteBuf buf) {
        final int length = buf.readInt();
        final byte[] bites = new byte[length];
        final char[] chars = new char[length];
        buf.readBytes(bites);
        for (int i = 0; i < length; ++i) {
            chars[i] = (char)bites[i];
        }
        this.name = String.copyValueOf(chars);
        for (int i = 0; i < 10; ++i) {
            this.infusions[i] = buf.readInt();
        }
        this.toggleClimb = buf.readBoolean();
        this.toggleInvisible = buf.readBoolean();
    }
    
    public void toBytes(final ByteBuf buf) {
        buf.writeInt(this.name.length());
        final byte[] bites = new byte[this.name.length()];
        final char[] chars = this.name.toCharArray();
        for (int i = 0; i < this.name.length(); ++i) {
            bites[i] = (byte)chars[i];
        }
        buf.writeBytes(bites);
        for (int i = 0; i < 10; ++i) {
            buf.writeInt(this.infusions[i]);
        }
        buf.writeBoolean(this.toggleClimb);
        buf.writeBoolean(this.toggleInvisible);
    }
}
