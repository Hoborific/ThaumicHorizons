//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.kentington.thaumichorizons.common.items.lenses.ILens;

public class InventoryCase implements IInventory {

    public ItemStack[] stackList;
    private final Container eventHandler;

    public InventoryCase(final Container par1Container) {
        this.stackList = new ItemStack[18];
        this.eventHandler = par1Container;
    }

    public int getSizeInventory() {
        return this.stackList.length;
    }

    public ItemStack getStackInSlot(final int par1) {
        return (par1 >= this.getSizeInventory()) ? null : this.stackList[par1];
    }

    public ItemStack getStackInSlotOnClosing(final int par1) {
        if (this.stackList[par1] != null) {
            final ItemStack var2 = this.stackList[par1];
            this.stackList[par1] = null;
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

    public void setInventorySlotContents(final int par1, final ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
        this.eventHandler.onCraftMatrixChanged((IInventory) this);
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean isUseableByPlayer(final EntityPlayer par1EntityPlayer) {
        return true;
    }

    public boolean isItemValidForSlot(final int i, final ItemStack itemstack) {
        return itemstack != null && itemstack.getItem() instanceof ILens;
    }

    public String getInventoryName() {
        return "container.lenscase";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public void markDirty() {}

    public void openInventory() {}

    public void closeInventory() {}
}
