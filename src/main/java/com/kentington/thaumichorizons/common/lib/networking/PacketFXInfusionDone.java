//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import thaumcraft.common.Thaumcraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public class PacketFXInfusionDone implements IMessage, IMessageHandler<PacketFXInfusionDone, IMessage> {

    int x;
    int y;
    int z;

    public PacketFXInfusionDone() {}

    public PacketFXInfusionDone(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @SideOnly(Side.CLIENT)
    public IMessage onMessage(final PacketFXInfusionDone message, final MessageContext ctx) {
        Thaumcraft.proxy
                .blockSparkle((World) Minecraft.getMinecraft().theWorld, message.x, message.y, message.z, -9999, 20);
        Thaumcraft.proxy.blockSparkle(
                (World) Minecraft.getMinecraft().theWorld,
                message.x,
                message.y - 1,
                message.z,
                -9999,
                20);
        return null;
    }

    public void fromBytes(final ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
    }

    public void toBytes(final ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
    }
}
