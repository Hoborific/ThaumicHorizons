//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.ItemInjector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ContainerInjector extends Container {
    EntityPlayer player;
    ItemStack[] ammo;
    public IInventory ammoInv;
    ItemStack injector;
    int blockSlot;

    public ContainerInjector(final EntityPlayer p) {
        this.ammo = new ItemStack[7];
        this.ammoInv = (IInventory) new InventoryInjector(this);
        this.injector = null;
        this.player = p;
        this.blockSlot = this.player.inventory.currentItem + 34;
        this.injector = this.player.inventory.getCurrentItem();
        for (int i = 0; i < 7; ++i) {
            this.ammoInv.setInventorySlotContents(
                    i, ((ItemInjector) ThaumicHorizons.itemInjector).getAmmo(this.injector, i));
        }
        this.addSlotToContainer((Slot)
                new SlotRestricted(this.ammoInv, 0, 73, 10, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.addSlotToContainer((Slot)
                new SlotRestricted(this.ammoInv, 1, 99, 20, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.addSlotToContainer((Slot)
                new SlotRestricted(this.ammoInv, 2, 107, 47, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.addSlotToContainer((Slot)
                new SlotRestricted(this.ammoInv, 3, 92, 70, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.addSlotToContainer((Slot)
                new SlotRestricted(this.ammoInv, 4, 64, 72, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.addSlotToContainer((Slot)
                new SlotRestricted(this.ammoInv, 5, 45, 51, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.addSlotToContainer((Slot)
                new SlotRestricted(this.ammoInv, 6, 49, 24, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory) p.inventory, j + i * 9 + 9, 8 + j * 18, 108 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory) p.inventory, i, 8 + i * 18, 166));
        }
        if (!this.player.worldObj.isRemote) {}
    }

    public boolean canInteractWith(final EntityPlayer p_75145_1_) {
        return true;
    }

    public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int p_82846_2_) {
        ItemStack itemstack = null;
        final Slot slot = (Slot) this.inventorySlots.get(p_82846_2_);
        if (slot != null && slot.getHasStack()) {
            final ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();
            if (p_82846_2_ < 7) {
                if (!this.mergeItemStack(itemstack2, 7, 43, true)) {
                    return null;
                }
                slot.onSlotChange(itemstack2, itemstack);
                if (itemstack2.stackSize == 0) {
                    slot.putStack((ItemStack) null);
                }
                if (itemstack2.stackSize == itemstack.stackSize) {
                    return null;
                }
                slot.onPickupFromSlot(p_82846_1_, itemstack2);
            } else {
                if (itemstack2.getItem() != ThaumicHorizons.itemSyringeInjection
                        || !this.mergeItemStack(itemstack2, 0, 7, false)) {
                    return null;
                }
                slot.onSlotChange(itemstack2, itemstack);
                if (itemstack2.stackSize == 0) {
                    slot.putStack((ItemStack) null);
                }
                if (itemstack2.stackSize == itemstack.stackSize) {
                    return null;
                }
            }
        }
        return itemstack;
    }

    public ItemStack slotClick(final int par1, final int par2, final int par3, final EntityPlayer par4EntityPlayer) {
        if (par1 == this.blockSlot) {
            return null;
        }
        final InventoryPlayer inventoryplayer = par4EntityPlayer.inventory;
        if (par1 != 0
                || this.ammoInv.isItemValidForSlot(par1, inventoryplayer.getItemStack())
                || (par1 == 0 && inventoryplayer.getItemStack() == null)) {
            return super.slotClick(par1, par2, par3, par4EntityPlayer);
        }
        return null;
    }

    public void onContainerClosed(final EntityPlayer par1EntityPlayer) {
        if (!this.player.worldObj.isRemote) {
            final NBTTagList ammo = new NBTTagList();
            for (int i = 0; i < 7; ++i) {
                final ItemStack var3 = this.ammoInv.getStackInSlotOnClosing(i);
                if (var3 != null) {
                    final NBTTagCompound var4 = new NBTTagCompound();
                    var3.writeToNBT(var4);
                    ammo.appendTag((NBTBase) var4);
                } else {
                    ammo.appendTag((NBTBase) new NBTTagCompound());
                }
            }
            final NBTTagCompound newTag = new NBTTagCompound();
            newTag.setTag("ammo", (NBTBase) ammo);
            this.injector.setTagCompound(newTag);
            if (this.player == null) {
                return;
            }
            if (this.player.getHeldItem() != null && this.player.getHeldItem().isItemEqual(this.injector)) {
                this.player.setCurrentItemOrArmor(0, this.injector);
            }
            this.player.inventory.markDirty();
        }
    }

    public void putStackInSlot(final int par1, final ItemStack par2ItemStack) {
        if (this.ammoInv.isItemValidForSlot(par1, par2ItemStack)) {
            super.putStackInSlot(par1, par2ItemStack);
        }
    }
}
