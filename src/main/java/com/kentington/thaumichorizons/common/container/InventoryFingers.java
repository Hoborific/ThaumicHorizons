//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileMagicWorkbench;

public class InventoryFingers extends TileMagicWorkbench implements IInventory {
    public int getSizeInventory() {
        return this.stackList.length;
    }

    public ItemStack getStackInSlot(final int par1) {
        return (par1 >= this.getSizeInventory()) ? null : this.stackList[par1];
    }

    public ItemStack getStackInRowAndColumn(final int par1, final int par2) {
        if (par1 >= 0 && par1 < 3) {
            final int var3 = par1 + par2 * 3;
            return this.getStackInSlot(var3);
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(final int par1) {
        if (this.stackList[par1] != null) {
            final ItemStack var2 = this.stackList[par1];
            this.stackList[par1] = null;
            this.markDirty();
            return var2;
        }
        return null;
    }

    public ItemStack decrStackSize(final int par1, final int par2) {
        if (this.stackList[par1] == null) {
            return null;
        }
        if (this.stackList[par1].stackSize <= par2) {
            final ItemStack var3 = this.stackList[par1];
            this.stackList[par1] = null;
            if (this.eventHandler != null) {
                this.eventHandler.onCraftMatrixChanged((IInventory) this);
            }
            this.markDirty();
            return var3;
        }
        final ItemStack var3 = this.stackList[par1].splitStack(par2);
        if (this.stackList[par1].stackSize == 0) {
            this.stackList[par1] = null;
        }
        if (this.eventHandler != null) {
            this.eventHandler.onCraftMatrixChanged((IInventory) this);
        }
        this.markDirty();
        return var3;
    }

    public void setInventorySlotContents(final int par1, final ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
        this.markDirty();
        if (this.eventHandler != null) {
            this.eventHandler.onCraftMatrixChanged((IInventory) this);
        }
    }

    public void setInventorySlotContentsSoftly(final int par1, final ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public String getInventoryName() {
        return null;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean hasCustomInventoryName() {
        return false;
    }

    public boolean isItemValidForSlot(final int i, final ItemStack itemstack) {
        if (i != 10 || itemstack == null) {
            return true;
        }
        if (!(itemstack.getItem() instanceof ItemWandCasting)) {
            return false;
        }
        final ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
        return !wand.isStaff(itemstack);
    }

    public int[] getAccessibleSlotsFromSide(final int var1) {
        return new int[] {10};
    }

    public boolean canInsertItem(final int i, final ItemStack itemstack, final int j) {
        if (i != 10 || itemstack == null || !(itemstack.getItem() instanceof ItemWandCasting)) {
            return false;
        }
        final ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
        return !wand.isStaff(itemstack);
    }

    public boolean canExtractItem(final int i, final ItemStack itemstack, final int j) {
        return i == 10;
    }

    public void markDirty() {}

    public boolean isUseableByPlayer(final EntityPlayer p_70300_1_) {
        return true;
    }
}
