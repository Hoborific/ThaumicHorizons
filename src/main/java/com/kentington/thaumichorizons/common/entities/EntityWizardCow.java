//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.lib.networking.PacketGetCowData;
import com.kentington.thaumichorizons.common.lib.networking.PacketHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.blocks.ItemJarNode;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

public class EntityWizardCow extends EntityCow implements IEntityAdditionalSpawnData {
    AspectList aspects;
    public AspectList essentia;
    public int nodeMod;
    public int nodeType;
    public boolean hasNode;

    public EntityWizardCow(final World p_i1683_1_) {
        super(p_i1683_1_);
        this.aspects = new AspectList();
        this.essentia = new AspectList();
        if (p_i1683_1_.isRemote) {
            PacketHandler.INSTANCE.sendToServer((IMessage) new PacketGetCowData(this.getEntityId()));
        }
    }

    public boolean interact(final EntityPlayer p_70085_1_) {
        final ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
        if (itemstack != null
                && this.hasNode
                && (itemstack.getItem() == ConfigItems.itemJarFilled
                        || itemstack.getItem() == Item.getItemFromBlock(ConfigBlocks.blockJar))) {
            final ItemStack jarOut = new ItemStack(ConfigItems.itemJarFilled);
            jarOut.setItemDamage(p_70085_1_.inventory.getCurrentItem().getItemDamage());
            boolean found = false;
            for (final Aspect asp : this.essentia.getAspects()) {
                if (this.essentia.getAmount(asp) > 0) {
                    if (itemstack.getItem() == Item.getItemFromBlock(ConfigBlocks.blockJar)) {
                        ((ItemJarFilled) jarOut.getItem())
                                .setAspects(jarOut, new AspectList().add(asp, this.essentia.getAmount(asp)));
                        this.essentia.remove(asp);
                        found = true;
                    } else {
                        final int amount = ((ItemJarFilled) jarOut.getItem())
                                .getAspects(itemstack)
                                .getAmount(asp);
                        if (amount > 0 && amount < 64) {
                            found = true;
                            if (amount + this.essentia.getAmount(asp) <= 64) {
                                ((ItemJarFilled) jarOut.getItem())
                                        .setAspects(
                                                jarOut,
                                                new AspectList().add(asp, amount + this.essentia.getAmount(asp)));
                                this.essentia.remove(asp);
                            } else {
                                this.essentia.remove(asp, 64 - amount);
                                ((ItemJarFilled) jarOut.getItem()).setAspects(jarOut, new AspectList().add(asp, 64));
                            }
                        }
                    }
                }
                if (found) {
                    if (itemstack.stackSize-- == 1) {
                        p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, jarOut);
                    } else if (!p_70085_1_.inventory.addItemStackToInventory(jarOut)) {
                        p_70085_1_.dropPlayerItemWithRandomChoice(jarOut, false);
                    }
                    p_70085_1_.inventory.markDirty();
                    return true;
                }
            }
            return false;
        }
        if (itemstack != null && !this.hasNode && itemstack.getItem() == ConfigItems.itemJarNode) {
            this.aspects = ((ItemJarNode) itemstack.getItem()).getAspects(itemstack);
            this.hasNode = true;
            final NodeModifier mod = ((ItemJarNode) itemstack.getItem()).getNodeModifier(itemstack);
            switch (mod) {
                case BRIGHT: {
                    this.nodeMod = 1;
                    break;
                }
                case PALE: {
                    this.nodeMod = -1;
                    break;
                }
                case FADING: {
                    this.nodeMod = -2;
                    break;
                }
            }
            final NodeType type = ((ItemJarNode) itemstack.getItem()).getNodeType(itemstack);
            switch (type) {
                case NORMAL: {
                    this.nodeType = 1;
                    break;
                }
                case UNSTABLE: {
                    this.nodeType = 2;
                    break;
                }
                case DARK: {
                    this.nodeType = 3;
                    break;
                }
                case TAINTED: {
                    this.nodeType = 4;
                    break;
                }
                case HUNGRY: {
                    this.nodeType = 5;
                    break;
                }
                case PURE: {
                    this.nodeType = 6;
                    break;
                }
            }
            p_70085_1_.inventory.setInventorySlotContents(
                    p_70085_1_.inventory.currentItem, new ItemStack(ConfigBlocks.blockJar));
            p_70085_1_.inventory.markDirty();
            return true;
        }
        return super.interact(p_70085_1_);
    }

    public NodeType getNodeType() {
        switch (this.nodeType) {
            case 2: {
                return NodeType.UNSTABLE;
            }
            case 3: {
                return NodeType.DARK;
            }
            case 4: {
                return NodeType.TAINTED;
            }
            case 5: {
                return NodeType.HUNGRY;
            }
            case 6: {
                return NodeType.PURE;
            }
            default: {
                return NodeType.NORMAL;
            }
        }
    }

    public NodeModifier getNodeMod() {
        switch (this.nodeMod) {
            case 1: {
                return NodeModifier.BRIGHT;
            }
            case -1: {
                return NodeModifier.PALE;
            }
            case -2: {
                return NodeModifier.FADING;
            }
            default: {
                return null;
            }
        }
    }

    public void updateAITick() {
        super.updateAITick();
        if (this.hasNode) {
            for (final Aspect asp : this.aspects.getAspects()) {
                final int divisor = this.aspects.getAmount(asp) * (3 + this.nodeMod);
                if (this.essentia.getAmount(asp) < 64 && divisor > 0 && this.ticksExisted % (150000 / divisor) == 0) {
                    this.essentia.add(asp, 1);
                }
            }
        }
    }

    public AspectList getEssentia() {
        return this.essentia;
    }

    public AspectList getAspects() {
        return this.aspects;
    }

    public void writeEntityToNBT(final NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("hasNode", this.hasNode);
        p_70014_1_.setInteger("nodeMod", this.nodeMod);
        p_70014_1_.setInteger("nodeType", this.nodeType);
        final NBTTagCompound aspectTag = new NBTTagCompound();
        this.aspects.writeToNBT(aspectTag);
        p_70014_1_.setTag("aspects", (NBTBase) aspectTag);
        final NBTTagCompound essentiaTag = new NBTTagCompound();
        this.essentia.writeToNBT(essentiaTag);
        p_70014_1_.setTag("essentia", (NBTBase) essentiaTag);
    }

    public void readEntityFromNBT(final NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        this.hasNode = p_70037_1_.getBoolean("hasNode");
        this.nodeMod = p_70037_1_.getInteger("nodeMod");
        this.nodeType = p_70037_1_.getInteger("nodeType");
        this.aspects.readFromNBT(p_70037_1_.getCompoundTag("aspects"));
        this.essentia.readFromNBT(p_70037_1_.getCompoundTag("essentia"));
    }

    public void writeSpawnData(final ByteBuf buffer) {
        if (this.aspects.size() > 0 && this.aspects.getAspects()[0] != null) {
            buffer.writeBoolean(true);
            buffer.writeInt(this.aspects.size());
            for (final Aspect asp : this.aspects.getAspects()) {
                final String tag = asp.getTag();
                buffer.writeInt(tag.length());
                buffer.writeBytes(tag.getBytes());
                buffer.writeInt(this.aspects.getAmount(asp));
            }
        } else {
            buffer.writeBoolean(false);
        }
    }

    public void readSpawnData(final ByteBuf buffer) {
        if (buffer.readBoolean()) {
            for (int numAspects = buffer.readInt(), i = 0; i < numAspects; ++i) {
                final int length = buffer.readInt();
                final byte[] bytes = new byte[length];
                final char[] chars = new char[length];
                buffer.readBytes(bytes);
                final int j = 0;
                while (i < bytes.length) {
                    chars[j] = (char) bytes[j];
                    ++i;
                }
                final String tag = String.copyValueOf(chars);
                this.aspects.add(Aspect.getAspect(tag), buffer.readInt());
            }
        }
    }
}
