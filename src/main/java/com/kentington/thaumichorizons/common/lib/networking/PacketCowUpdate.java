//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import com.kentington.thaumichorizons.common.entities.EntityWizardCow;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import thaumcraft.api.aspects.AspectList;

public class PacketCowUpdate implements IMessage, IMessageHandler<PacketCowUpdate, IMessage> {

    int[] aspects;
    int[] amounts;
    int type;
    int mod;
    int id;

    public PacketCowUpdate() {}

    public PacketCowUpdate(final int[] aspects, final int[] amounts, final int type, final int mod, final int id) {
        this.aspects = aspects;
        this.amounts = amounts;
        this.type = type;
        this.mod = mod;
        this.id = id;
    }

    public IMessage onMessage(final PacketCowUpdate message, final MessageContext ctx) {
        final Entity ent = Minecraft.getMinecraft().theWorld.getEntityByID(message.id);
        if (ent instanceof EntityWizardCow) {
            final EntityWizardCow cow = (EntityWizardCow) ent;
            cow.nodeMod = message.mod;
            cow.nodeType = message.type;
            cow.essentia = new AspectList();
            for (int i = 0; i < message.aspects.length; ++i) {
                cow.essentia.add(PacketGetCowData.sorted[message.aspects[i]], message.amounts[i]);
            }
        }
        return null;
    }

    public void fromBytes(final ByteBuf buf) {
        this.id = buf.readInt();
        this.type = buf.readInt();
        this.mod = buf.readInt();
        for (int count = buf.readInt(), i = 0; i < count; ++i) {
            this.aspects[i] = buf.readInt();
            this.amounts[i] = buf.readInt();
        }
    }

    public void toBytes(final ByteBuf buf) {
        buf.writeInt(this.id);
        buf.writeInt(this.type);
        buf.writeInt(this.mod);
        buf.writeInt(this.aspects.length);
        for (int i = 0; i < this.aspects.length; ++i) {
            buf.writeInt(this.aspects[i]);
            buf.writeInt(this.amounts[i]);
        }
    }
}
