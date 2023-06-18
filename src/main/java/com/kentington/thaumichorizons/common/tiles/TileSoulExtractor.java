//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileJarBrain;

public class TileSoulExtractor extends TileVisNode implements ISidedInventory {

    public ItemStack soulsand;
    public int ticksLeft;
    public boolean extracting;
    public static final int MAX_TICKS = 1200;
    public int sieveMotion;

    public TileSoulExtractor() {
        this.soulsand = null;
        this.ticksLeft = 0;
        this.extracting = false;
        this.sieveMotion = 0;
    }

    public int getSizeInventory() {
        return 1;
    }

    public ItemStack getStackInSlot(final int p_70301_1_) {
        return this.soulsand;
    }

    public ItemStack decrStackSize(final int p_70298_1_, final int p_70298_2_) {
        final int oldsize = this.soulsand.stackSize;
        final ItemStack soulsand = this.soulsand;
        soulsand.stackSize -= p_70298_2_;
        if (this.soulsand.stackSize <= 0) {
            this.soulsand = null;
        }
        return new ItemStack(Blocks.soul_sand, Math.min(p_70298_2_, oldsize));
    }

    public ItemStack getStackInSlotOnClosing(final int p_70304_1_) {
        return null;
    }

    public void setInventorySlotContents(final int p_70299_1_, final ItemStack p_70299_2_) {
        this.soulsand = p_70299_2_;
    }

    public String getInventoryName() {
        return "container.soulsieve";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(final EntityPlayer p_70300_1_) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this
                && p_70300_1_.getDistanceSq(this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(final int p_94041_1_, final ItemStack p_94041_2_) {
        return p_94041_2_.isItemEqual(new ItemStack(Blocks.soul_sand));
    }

    public void writeCustomNBT(final NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.setInteger("ticks", this.ticksLeft);
        nbttagcompound.setBoolean("extracting", this.extracting);
        final NBTTagList nbttaglist = new NBTTagList();
        final NBTTagCompound nbttagcompound2 = new NBTTagCompound();
        if (this.soulsand != null) {
            this.soulsand.writeToNBT(nbttagcompound2);
        }
        nbttaglist.appendTag((NBTBase) nbttagcompound2);
        nbttagcompound.setTag("Items", (NBTBase) nbttaglist);
    }

    public void readCustomNBT(final NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.ticksLeft = nbttagcompound.getInteger("ticks");
        this.extracting = nbttagcompound.getBoolean("extracting");
        final NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);
        final NBTTagCompound nbttagcompound2 = nbttaglist.getCompoundTagAt(0);
        this.soulsand = ItemStack.loadItemStackFromNBT(nbttagcompound2);
    }

    public int[] getAccessibleSlotsFromSide(final int p_94128_1_) {
        return new int[] { 0 };
    }

    public boolean canInsertItem(final int p_102007_1_, final ItemStack p_102007_2_, final int p_102007_3_) {
        return this.isItemValidForSlot(0, p_102007_2_);
    }

    public boolean canExtractItem(final int p_102008_1_, final ItemStack p_102008_2_, final int p_102008_3_) {
        return false;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        this.extracting = false;
        if (this.ticksLeft <= 0) {
            if (this.soulsand == null) {
                return;
            }
            final ItemStack soulsand = this.soulsand;
            --soulsand.stackSize;
            if (this.soulsand.stackSize <= 0) {
                this.soulsand = null;
            }
            this.ticksLeft = 1200;
        }
        final TileEntity above = this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
        if (above == null) {
            return;
        }
        if ((above instanceof TileJarBrain && ((TileJarBrain) above).xp < ((TileJarBrain) above).xpMax)
                || (above instanceof ISoulReceiver && ((ISoulReceiver) above).canAcceptSouls())) {
            this.extracting = true;
            if (!this.worldObj.isRemote && this.ticksLeft > 0) {
                final int visBoost = VisNetHandler
                        .drainVis(this.worldObj, this.xCoord, this.yCoord, this.zCoord, Aspect.AIR, 10);
                this.ticksLeft -= 1 + visBoost;
                if (above instanceof TileJarBrain) {
                    for (int i = 0; i < 1 + visBoost; ++i) {
                        if (Math.random() > 0.99) {
                            final TileJarBrain tileJarBrain = (TileJarBrain) above;
                            ++tileJarBrain.xp;
                            if (((TileJarBrain) above).xp >= ((TileJarBrain) above).xpMax) {
                                ((TileJarBrain) above).xp = ((TileJarBrain) above).xpMax;
                            }
                        }
                    }
                } else {
                    ((ISoulReceiver) above).addSoulBits(1 + visBoost);
                }
                if (this.ticksLeft <= 0) {
                    final TileEntity below = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
                    final ItemStack sand = new ItemStack((Block) Blocks.sand);
                    if (below != null && below instanceof ISidedInventory) {
                        final int[] accessibleSlotsFromSide;
                        final int[] slots = accessibleSlotsFromSide = ((ISidedInventory) below)
                                .getAccessibleSlotsFromSide(1);
                        for (final int j : accessibleSlotsFromSide) {
                            if (((ISidedInventory) below).canInsertItem(j, sand, 1)) {
                                InventoryUtils.placeItemStackIntoInventory(sand, (IInventory) below, 1, true);
                                break;
                            }
                        }
                    } else if (below != null && below instanceof IInventory) {
                        for (int slots2 = ((IInventory) below).getSizeInventory(), k = 0; k < slots2; ++k) {
                            if (((IInventory) below).getStackInSlot(k) == null
                                    || ((IInventory) below).getStackInSlot(k).getItem() == sand.getItem()) {
                                InventoryUtils.placeItemStackIntoInventory(sand, (IInventory) below, 1, true);
                                break;
                            }
                        }
                    } else {
                        final EntityItem fallenSand = new EntityItem(
                                this.worldObj,
                                this.xCoord + 0.5,
                                (double) this.yCoord,
                                this.zCoord + 0.5,
                                sand);
                        this.worldObj.spawnEntityInWorld((Entity) fallenSand);
                    }
                }
                this.markDirty();
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            } else if (this.ticksLeft > 0) {
                ++this.sieveMotion;
                if (this.sieveMotion >= 360) {
                    this.sieveMotion -= 360;
                    this.worldObj.playSound(
                            this.xCoord + 0.5,
                            this.yCoord + 0.5,
                            this.zCoord + 0.5,
                            "dig.sand",
                            1.0f,
                            0.0f,
                            false);
                    final ThaumicHorizons instance = ThaumicHorizons.instance;
                    ThaumicHorizons.proxy.soulParticles(this.xCoord, this.yCoord, this.zCoord, this.worldObj);
                }
            }
        }
    }

    @Override
    public int getRange() {
        return 8;
    }

    @Override
    public boolean isSource() {
        return false;
    }

    public boolean isExtracting() {
        return this.extracting;
    }

    @SideOnly(Side.CLIENT)
    public int getTimeRemainingScaled(final int p_145955_1_) {
        return this.ticksLeft * p_145955_1_ / 1200;
    }
}
