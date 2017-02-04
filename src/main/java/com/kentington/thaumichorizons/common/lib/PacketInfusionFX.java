// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import thaumcraft.common.tiles.TilePedestal;
import com.kentington.thaumichorizons.common.tiles.TileVatSlave;
import thaumcraft.common.Thaumcraft;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketInfusionFX implements IMessage, IMessageHandler<PacketInfusionFX, IMessage>
{
    private int x;
    private int y;
    private int z;
    private byte dx;
    private byte dy;
    private byte dz;
    private int color;
    
    public PacketInfusionFX() {
    }
    
    public PacketInfusionFX(final int x, final int y, final int z, final byte dx, final byte dy, final byte dz, final int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.color = color;
    }
    
    public void toBytes(final ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.color);
        buffer.writeByte((int)this.dx);
        buffer.writeByte((int)this.dy);
        buffer.writeByte((int)this.dz);
    }
    
    public void fromBytes(final ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.color = buffer.readInt();
        this.dx = buffer.readByte();
        this.dy = buffer.readByte();
        this.dz = buffer.readByte();
    }
    
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(final PacketInfusionFX message, final MessageContext ctx) {
        final int tx = message.x - message.dx;
        final int ty = message.y - message.dy;
        final int tz = message.z - message.dz;
        final String key = tx + ":" + ty + ":" + tz + ":" + message.color;
        final TileEntity tile = Thaumcraft.proxy.getClientWorld().getTileEntity(message.x, message.y, message.z);
        if (tile != null && tile instanceof TileVatSlave) {
            int count = 15;
            if (Thaumcraft.proxy.getClientWorld().getTileEntity(tx, ty, tz) != null && Thaumcraft.proxy.getClientWorld().getTileEntity(tx, ty, tz) instanceof TilePedestal) {
                count = 60;
            }
            final TileVat is = ((TileVatSlave)tile).getBoss(-1);
            if (is == null) {
                return null;
            }
            if (is.sourceFX.containsKey(key)) {
                final TileVat.SourceFX sf = is.sourceFX.get(key);
                sf.ticks = count;
                is.sourceFX.put(key, sf);
            }
            else {
                final TileVat tmp232_230 = is;
                tmp232_230.getClass();
                final HashMap<String, TileVat.SourceFX> sourceFX = is.sourceFX;
                final String s = key;
                final TileVat this$0 = is;
                this$0.getClass();
                sourceFX.put(s, this$0.new SourceFX(new ChunkCoordinates(tx, ty, tz), count, message.color));
            }
        }
        return null;
    }
}
