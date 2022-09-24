//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryInjector implements IInventory {
    private ItemStack[] stackList;
    private Container eventHandler;

    public InventoryInjector(final Container cont) {
        this.eventHandler = cont;
        this.stackList = new ItemStack[7];
    }

    public int getSizeInventory() {
        return 7;
    }

    public ItemStack getStackInSlot(final int par1) {
        return (par1 >= this.getSizeInventory()) ? null : this.stackList[par1];
    }

    public ItemStack decrStackSize(final int par1, final int par2) {
        if (this.stackList[par1] == null) {
            return null;
        }
        if (this.stackList[par1].stackSize <= par2) {
            final ItemStack var3 = this.stackList[par1];
            this.stackList[par1] = null;
            this.eventHandler.onCraftMatrixChanged((IInventory) this);
            return var3;
        }
        final ItemStack var3 = this.stackList[par1].splitStack(par2);
        if (this.stackList[par1].stackSize == 0) {
            this.stackList[par1] = null;
        }
        this.eventHandler.onCraftMatrixChanged((IInventory) this);
        return var3;
    }

    public ItemStack getStackInSlotOnClosing(final int par1) {
        if (this.stackList[par1] != null) {
            final ItemStack var2 = this.stackList[par1];
            this.stackList[par1] = null;
            return var2;
        }
        return null;
    }

    public void setInventorySlotContents(final int par1, final ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
        this.eventHandler.onCraftMatrixChanged((IInventory) this);
    }

    public String getInventoryName() {
        return "container.injector";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public void markDirty() {}

    public boolean isUseableByPlayer(final EntityPlayer p_70300_1_) {
        return true;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(final int p_94041_1_, final ItemStack p_94041_2_) {
        return p_94041_2_ != null && p_94041_2_.getItem() == ThaumicHorizons.itemSyringeInjection;
    }
}
