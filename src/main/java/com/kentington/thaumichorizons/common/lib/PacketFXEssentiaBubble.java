// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import thaumcraft.client.fx.ParticleEngine;
import net.minecraft.world.World;
import com.kentington.thaumichorizons.client.fx.FXEssentiaBubble;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketFXEssentiaBubble implements IMessage, IMessageHandler<PacketFXEssentiaBubble, IMessage>
{
    double x;
    double y;
    double z;
    int color;
    
    public PacketFXEssentiaBubble() {
    }
    
    public PacketFXEssentiaBubble(final double x, final double y, final double z, final int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }
    
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(final PacketFXEssentiaBubble message, final MessageContext ctx) {
        for (int i = 0; i < 10; ++i) {
            final FXEssentiaBubble fb = new FXEssentiaBubble((World)Minecraft.getMinecraft().theWorld, message.x + 1.5f * (Minecraft.getMinecraft().theWorld.rand.nextFloat() - 0.5f), message.y, message.z + 1.5f * (Minecraft.getMinecraft().theWorld.rand.nextFloat() - 0.5f), Minecraft.getMinecraft().thePlayer.ticksExisted, message.color, 0.3f, i * 2 + 2);
            fb.noClip = true;
            ParticleEngine.instance.addEffect((World)Minecraft.getMinecraft().theWorld, (EntityFX)fb);
        }
        return null;
    }
    
    public void fromBytes(final ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.color = buf.readInt();
    }
    
    public void toBytes(final ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeInt(this.color);
    }
}
