// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import net.minecraft.world.World;
import java.util.List;
import java.util.ArrayList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketToggleInvisibleToServer implements IMessage, IMessageHandler<PacketToggleInvisibleToServer, IMessage>
{
    private int playerid;
    private int dim;
    
    public PacketToggleInvisibleToServer() {
    }
    
    public PacketToggleInvisibleToServer(final EntityPlayer player, final int dim) {
        this.playerid = player.getEntityId();
        this.dim = dim;
    }
    
    public void toBytes(final ByteBuf buffer) {
        buffer.writeInt(this.playerid);
        buffer.writeInt(this.dim);
    }
    
    public void fromBytes(final ByteBuf buffer) {
        this.playerid = buffer.readInt();
        this.dim = buffer.readInt();
    }
    
    public IMessage onMessage(final PacketToggleInvisibleToServer message, final MessageContext ctx) {
        final World world = (World)DimensionManager.getWorld(message.dim);
        final EntityPlayer player = (EntityPlayer)world.getEntityByID(message.playerid);
        ((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).toggleClimb = !((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).toggleClimb;
        if (((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).toggleClimb) {
            player.removePotionEffect(Potion.invisibility.id);
            player.setInvisible(false);
        }
        else {
            final PotionEffect effect = new PotionEffect(Potion.invisibility.id, Integer.MAX_VALUE, 0, true);
            effect.setCurativeItems((List)new ArrayList());
            player.addPotionEffect(effect);
            player.setInvisible(true);
        }
        return null;
    }
}
