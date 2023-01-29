//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import thaumcraft.common.lib.research.ResearchManager;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVat;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerVat extends Container {

    TileVat tile;
    EntityPlayer player;
    SlotSample slotSample;

    public ContainerVat(final EntityPlayer p, final TileVat t) {
        this.tile = t;
        this.player = p;
        if (ResearchManager.isResearchComplete(this.player.getCommandSenderName(), "incarnationVat")) {
            this.addSlotToContainer((Slot) (this.slotSample = new SlotSample(this.tile, 0, 63, 32)));
            this.addSlotToContainer(
                    (Slot) new SlotRestricted(
                            (IInventory) this.tile,
                            1,
                            96,
                            32,
                            new ItemStack(ThaumicHorizons.itemNutrients)));
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory) p.inventory, j + i * 9 + 9, 8 + j * 18, 127 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory) p.inventory, i, 8 + i * 18, 185));
        }
    }

    public boolean canInteractWith(final EntityPlayer p_75145_1_) {
        return this.tile.isUseableByPlayer(p_75145_1_);
    }

    public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int p_82846_2_) {
        ItemStack itemstack = null;
        final Slot slot = (Slot) this.inventorySlots.get(p_82846_2_);
        if (slot != null && slot.getHasStack()) {
            final ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();
            if (this.slotSample != null && p_82846_2_ == 0) {
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
            } else if (this.slotSample != null && p_82846_2_ == 1) {
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
                if (this.slotSample == null) {
                    return null;
                }
                if ((itemstack.getItem() != ThaumicHorizons.itemNutrients
                        || !this.mergeItemStack(itemstack2, 1, 2, false))
                        && (!this.slotSample.isItemValid(itemstack2)
                                || !this.mergeItemStack(itemstack2, 0, 1, false))) {
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

    public void detectAndSendChanges() {}

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(final int par1, final int par2) {}

    public void addCraftingToCrafters(final ICrafting par1ICrafting) {}
}
