//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public class PacketNoMoreItems implements IMessage, IMessageHandler<PacketNoMoreItems, IMessage> {

    @SideOnly(Side.CLIENT)
    public IMessage onMessage(final PacketNoMoreItems message, final MessageContext ctx) {
        Minecraft.getMinecraft().thePlayer.inventory.clearInventory(null, -1);
        final IInventory baubles = BaublesApi.getBaubles(Minecraft.getMinecraft().thePlayer);
        baubles.setInventorySlotContents(0, null);
        baubles.setInventorySlotContents(1, null);
        baubles.setInventorySlotContents(2, null);
        baubles.setInventorySlotContents(3, null);
        return null;
    }

    public void fromBytes(final ByteBuf buf) {}

    public void toBytes(final ByteBuf buf) {}
}
