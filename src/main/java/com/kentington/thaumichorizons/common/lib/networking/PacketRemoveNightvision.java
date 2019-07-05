// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib.networking;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.kentington.thaumichorizons.common.items.lenses.LensManager;
import net.minecraft.potion.Potion;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketRemoveNightvision implements IMessage, IMessageHandler<PacketRemoveNightvision, IMessage>
{
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(final PacketRemoveNightvision message, final MessageContext ctx) {
        Minecraft.getMinecraft().thePlayer.removePotionEffect(Potion.nightVision.id);
        Minecraft.getMinecraft();
        LensManager.nightVisionOffTime = Minecraft.getSystemTime() + 100L;
        return null;
    }
    
    public void fromBytes(final ByteBuf buf) {
    }
    
    public void toBytes(final ByteBuf buf) {
    }
}
