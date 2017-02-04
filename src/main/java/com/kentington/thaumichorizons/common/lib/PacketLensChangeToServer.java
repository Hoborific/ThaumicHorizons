// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import com.kentington.thaumichorizons.common.items.lenses.LensManager;
import thaumcraft.api.nodes.IRevealer;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketLensChangeToServer implements IMessage, IMessageHandler<PacketLensChangeToServer, IMessage>
{
    private int dim;
    private int playerid;
    private String lens;
    
    public PacketLensChangeToServer() {
    }
    
    public PacketLensChangeToServer(final EntityPlayer player, final String lens) {
        this.dim = player.worldObj.provider.dimensionId;
        this.playerid = player.getEntityId();
        this.lens = lens;
    }
    
    public void toBytes(final ByteBuf buffer) {
        buffer.writeInt(this.dim);
        buffer.writeInt(this.playerid);
        ByteBufUtils.writeUTF8String(buffer, this.lens);
    }
    
    public void fromBytes(final ByteBuf buffer) {
        this.dim = buffer.readInt();
        this.playerid = buffer.readInt();
        this.lens = ByteBufUtils.readUTF8String(buffer);
    }
    
    public IMessage onMessage(final PacketLensChangeToServer message, final MessageContext ctx) {
        final World world = (World)DimensionManager.getWorld(message.dim);
        if (world == null || (ctx.getServerHandler().playerEntity != null && ctx.getServerHandler().playerEntity.getEntityId() != message.playerid)) {
            return null;
        }
        final Entity player = world.getEntityByID(message.playerid);
        if (player != null && player instanceof EntityPlayer && ((EntityPlayer)player).inventory.armorItemInSlot(3) != null && ((EntityPlayer)player).inventory.armorItemInSlot(3).getItem() instanceof IRevealer) {
            LensManager.changeLens(((EntityPlayer)player).inventory.armorItemInSlot(3), world, (EntityPlayer)player, message.lens);
        }
        return null;
    }
}
