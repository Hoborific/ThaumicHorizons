// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketPlayerInfusionSync implements IMessage, IMessageHandler<PacketPlayerInfusionSync, IMessage>
{
    int[] infusions;
    String name;
    
    public PacketPlayerInfusionSync() {
        this.infusions = new int[10];
        this.name = "";
    }
    
    public PacketPlayerInfusionSync(final String name, final int[] infusions) {
        this.infusions = new int[10];
        this.name = "";
        this.name = name;
        this.infusions = infusions;
    }
    
    public IMessage onMessage(final PacketPlayerInfusionSync message, final MessageContext ctx) {
        if (Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().theWorld.getPlayerEntityByName(message.name) != null && Minecraft.getMinecraft().theWorld.getPlayerEntityByName(message.name).getExtendedProperties("CreatureInfusion") != null) {
            ((EntityInfusionProperties)Minecraft.getMinecraft().theWorld.getPlayerEntityByName(message.name).getExtendedProperties("CreatureInfusion")).playerInfusions = message.infusions;
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
    }
}
