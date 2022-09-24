//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.config.ConfigBlocks;

public class TileSoulJar extends TileThaumcraft implements IWandable {
    public NBTTagCompound jarTag;
    public Entity entity;
    public boolean drop;
    ResourceLocation texture;

    public TileSoulJar() {
        this.jarTag = null;
        this.entity = null;
        this.drop = true;
        this.texture = new ResourceLocation("thaumcraft", "textures/models/jar.png");
    }

    public void updateEntity() {
        if (this.entity == null && this.jarTag != null && !this.jarTag.getBoolean("isSoul")) {
            this.entity = EntityList.createEntityFromNBT(this.jarTag, this.worldObj);
        }
    }

    @Override
    public void readCustomNBT(final NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.jarTag = nbttagcompound.getCompoundTag("jarTag");
    }

    @Override
    public void writeCustomNBT(final NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.setTag("jarTag", (NBTBase) this.jarTag);
    }

    @Override
    public int onWandRightClick(
            final World world,
            final ItemStack wandstack,
            final EntityPlayer player,
            final int x,
            final int y,
            final int z,
            final int side,
            final int md) {
        if (this.jarTag.getBoolean("isSoul")) {
            return 0;
        }
        if (!world.isRemote) {
            this.drop = false;
            world.setBlockToAir(x, y, z);
            final Entity ent = EntityList.createEntityFromNBT(this.jarTag, world);
            if (ent == null) {
                return 0;
            }
            if (ent instanceof EntityTameable && ((EntityTameable) ent).getOwner() == null) {
                ((EntityTameable) ent).func_152115_b(player.getUniqueID().toString());
            }
            ent.setLocationAndAngles(x + 0.5, y + 0.1, z + 0.5, 0.0f, 0.0f);
            world.spawnEntityInWorld(ent);
            this.markDirty();
        }
        world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(ConfigBlocks.blockJar) + 61440);
        player.worldObj.playSound(
                x + 0.5, y + 0.5, z + 0.5, "random.glass", 1.0f, 0.9f + player.worldObj.rand.nextFloat() * 0.2f, false);
        player.swingItem();
        return 0;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    @Override
    public ItemStack onWandRightClick(final World world, final ItemStack wandstack, final EntityPlayer player) {
        return null;
    }

    @Override
    public void onUsingWandTick(final ItemStack wandstack, final EntityPlayer player, final int count) {}

    @Override
    public void onWandStoppedUsing(
            final ItemStack wandstack, final World world, final EntityPlayer player, final int count) {}
}
