//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.client.fx.FXContainment;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import thaumcraft.client.fx.ParticleEngine;

public class PacketFXContainment implements IMessage, IMessageHandler<PacketFXContainment, IMessage> {

    double x;
    double y;
    double z;

    public PacketFXContainment() {}

    public PacketFXContainment(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @SideOnly(Side.CLIENT)
    public IMessage onMessage(final PacketFXContainment message, final MessageContext ctx) {
        final FXContainment fb = new FXContainment(
                (World) Minecraft.getMinecraft().theWorld,
                message.x,
                message.y,
                message.z);
        ParticleEngine.instance.addEffect((World) Minecraft.getMinecraft().theWorld, (EntityFX) fb);
        return null;
    }

    public void fromBytes(final ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    public void toBytes(final ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
    }
}
