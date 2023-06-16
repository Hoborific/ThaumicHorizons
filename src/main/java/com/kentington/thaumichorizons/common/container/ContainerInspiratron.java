//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.kentington.thaumichorizons.common.tiles.TileInspiratron;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.config.ConfigItems;

public class ContainerInspiratron extends Container {

    TileInspiratron tile;
    int progress;

    public ContainerInspiratron(final InventoryPlayer p_i1812_1_, final TileInspiratron p_i1812_2_) {
        this.tile = p_i1812_2_;
        this.addSlotToContainer(
                (Slot) new SlotRestricted((IInventory) p_i1812_2_, 0, 15, 42, new ItemStack(Items.paper)));
        this.addSlotToContainer(
                (Slot) new SlotRestricted(
                        (IInventory) p_i1812_2_,
                        1,
                        146,
                        42,
                        new ItemStack(ConfigItems.itemResource, 1, 9)));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory) p_i1812_1_, j + i * 9 + 9, 8 + j * 18, 137 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory) p_i1812_1_, i, 8 + i * 18, 195));
        }
    }

    public void addCraftingToCrafters(final ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate((Container) this, 0, this.tile.progress);
    }

    public boolean canInteractWith(final EntityPlayer p_75145_1_) {
        return this.tile.isUseableByPlayer(p_75145_1_);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (Object crafter : this.crafters) {
            final ICrafting icrafting = (ICrafting) crafter;
            if (this.progress != this.tile.progress) {
                icrafting.sendProgressBarUpdate((Container) this, 0, this.tile.progress);
            }
        }
        this.progress = this.tile.progress;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(final int par1, final int par2) {
        if (par1 == 0) {
            this.tile.progress = par2;
        }
    }

    public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int p_82846_2_) {
        ItemStack itemstack = null;
        final Slot slot = (Slot) this.inventorySlots.get(p_82846_2_);
        if (slot != null && slot.getHasStack()) {
            final ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();
            if (p_82846_2_ == 0) {
                if (!this.mergeItemStack(itemstack2, 2, 38, true)) {
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
            } else if (p_82846_2_ == 1) {
                if (!this.mergeItemStack(itemstack2, 2, 38, true)) {
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
                if (!this.mergeItemStack(itemstack2, 0, 1, false)) {
                    return null;
                }
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
}
