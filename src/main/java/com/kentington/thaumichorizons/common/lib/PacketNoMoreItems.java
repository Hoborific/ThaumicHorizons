// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import baubles.api.BaublesApi;
import net.minecraft.item.Item;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketNoMoreItems implements IMessage, IMessageHandler<PacketNoMoreItems, IMessage>
{
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(final PacketNoMoreItems message, final MessageContext ctx) {
        Minecraft.getMinecraft().thePlayer.inventory.clearInventory((Item)null, -1);
        final IInventory baubles = BaublesApi.getBaubles((EntityPlayer)Minecraft.getMinecraft().thePlayer);
        baubles.setInventorySlotContents(0, (ItemStack)null);
        baubles.setInventorySlotContents(1, (ItemStack)null);
        baubles.setInventorySlotContents(2, (ItemStack)null);
        baubles.setInventorySlotContents(3, (ItemStack)null);
        return null;
    }
    
    public void fromBytes(final ByteBuf buf) {
    }
    
    public void toBytes(final ByteBuf buf) {
    }
}
