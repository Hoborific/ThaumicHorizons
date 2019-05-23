package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.tiles.TileCloud;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public class PacketRainState implements IMessage, IMessageHandler<PacketRainState, IMessage> {

    public PacketRainState(boolean raining) {
        this.raining = raining;
    }

    public PacketRainState() {
        raining = false;
    }

    private boolean raining;

    @Override
    public void fromBytes(ByteBuf buf) {
        raining = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(raining);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketRainState message, MessageContext ctx) {
        TileCloud.raining = message.raining;
        return null;
    }
}
