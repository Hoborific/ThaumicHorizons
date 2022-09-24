//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib.networking;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class PacketFXBlocksplosion implements IMessage, IMessageHandler<PacketFXBlocksplosion, IMessage> {
    int x;
    int y;
    int z;
    int id;
    int md;

    public PacketFXBlocksplosion() {}

    public PacketFXBlocksplosion(final int id, final int md, final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
        this.md = md;
    }

    public IMessage onMessage(final PacketFXBlocksplosion message, final MessageContext ctx) {
        Minecraft.getMinecraft()
                .effectRenderer
                .addBlockDestroyEffects(message.x, message.y, message.z, Block.getBlockById(message.id), message.md);
        return null;
    }

    public void fromBytes(final ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.id = buf.readInt();
        this.md = buf.readInt();
    }

    public void toBytes(final ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeInt(this.id);
        buf.writeInt(this.md);
    }
}
