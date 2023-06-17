//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib.networking;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.world.World;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

public class PacketFXDeadCreature implements IMessage, IMessageHandler<PacketFXDeadCreature, IMessage> {

    double x;
    double y;
    double z;

    public PacketFXDeadCreature() {}

    public PacketFXDeadCreature(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @SideOnly(Side.CLIENT)
    public IMessage onMessage(final PacketFXDeadCreature message, final MessageContext ctx) {
        final World world = Minecraft.getMinecraft().theWorld;
        for (int i = 0; i < 36; ++i) {
            final EntitySpellParticleFX fb = new EntitySpellParticleFX(world, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            fb.setRBGColorF(0.8f, 0.2f, 0.2f);
            fb.motionX = (world.rand.nextFloat() - 0.5f) * 0.4f;
            fb.motionY = (world.rand.nextFloat() - 0.5f) * 0.2f;
            fb.motionZ = (world.rand.nextFloat() - 0.5f) * 0.4f;
            fb.noClip = true;
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(fb);
        }
        world.playSoundEffect(this.x + 0.5, this.y, this.z + 0.5, "thaumcraft:gore", 2.0f, 1.0f);
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
