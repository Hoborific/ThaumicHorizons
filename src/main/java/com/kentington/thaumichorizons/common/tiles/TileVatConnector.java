//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileVatConnector extends TileVatSlave implements IEssentiaTransport, ISidedInventory {

    public int getSizeInventory() {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getSizeInventory();
        }
        return 0;
    }

    public ItemStack getStackInSlot(final int p_70301_1_) {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getStackInSlot(p_70301_1_);
        }
        return null;
    }

    public ItemStack decrStackSize(final int p_70298_1_, final int p_70298_2_) {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.decrStackSize(p_70298_1_, p_70298_2_);
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(final int p_70304_1_) {
        return null;
    }

    public void setInventorySlotContents(final int p_70299_1_, final ItemStack p_70299_2_) {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            boss.setInventorySlotContents(p_70299_1_, p_70299_2_);
        }
    }

    public String getInventoryName() {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getInventoryName();
        }
        return null;
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getInventoryStackLimit();
        }
        return 0;
    }

    public boolean isUseableByPlayer(final EntityPlayer p_70300_1_) {
        return false;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(final int p_94041_1_, final ItemStack p_94041_2_) {
        final TileVat boss = this.getBoss(-1);
        return boss != null && boss.isItemValidForSlot(p_94041_1_, p_94041_2_);
    }

    public int[] getAccessibleSlotsFromSide(final int p_94128_1_) {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getAccessibleSlotsFromSide(p_94128_1_);
        }
        return null;
    }

    public boolean canInsertItem(final int p_102007_1_, final ItemStack p_102007_2_, final int p_102007_3_) {
        final TileVat boss = this.getBoss(-1);
        return boss != null && boss.canInsertItem(p_102007_1_, p_102007_2_, p_102007_3_);
    }

    public boolean canExtractItem(final int p_102008_1_, final ItemStack p_102008_2_, final int p_102008_3_) {
        final TileVat boss = this.getBoss(-1);
        return boss != null && boss.canExtractItem(p_102008_1_, p_102008_2_, p_102008_3_);
    }

    @Override
    public boolean isConnectable(final ForgeDirection face) {
        return true;
    }

    @Override
    public boolean canInputFrom(final ForgeDirection face) {
        return true;
    }

    @Override
    public boolean canOutputTo(final ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(final Aspect aspect, final int amount) {}

    @Override
    public Aspect getSuctionType(final ForgeDirection face) {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getSuctionType(face);
        }
        return null;
    }

    @Override
    public int getSuctionAmount(final ForgeDirection face) {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getSuctionAmount(face);
        }
        return 0;
    }

    @Override
    public int takeEssentia(final Aspect aspect, final int amount, final ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(final Aspect aspect, final int amount, final ForgeDirection face) {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.addEssentia(aspect, amount, face);
        }
        return 0;
    }

    @Override
    public Aspect getEssentiaType(final ForgeDirection face) {
        return null;
    }

    @Override
    public int getEssentiaAmount(final ForgeDirection face) {
        return 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }
}
