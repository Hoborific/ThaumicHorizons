//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.tiles.TileSoulExtractor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerSoulExtractor extends Container {
    TileSoulExtractor tile;
    private int ticksLeft;

    public ContainerSoulExtractor(final InventoryPlayer p_i1812_1_, final TileSoulExtractor p_i1812_2_) {
        this.tile = p_i1812_2_;
        this.addSlotToContainer(
                (Slot) new SlotRestricted((IInventory) p_i1812_2_, 0, 64, 30, new ItemStack(Blocks.soul_sand)));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory) p_i1812_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory) p_i1812_1_, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(final ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate((Container) this, 0, this.tile.ticksLeft);
    }

    public boolean canInteractWith(final EntityPlayer p_75145_1_) {
        return this.tile.isUseableByPlayer(p_75145_1_);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            final ICrafting icrafting = (ICrafting) this.crafters.get(i);
            if (this.ticksLeft != this.tile.ticksLeft) {
                icrafting.sendProgressBarUpdate((Container) this, 0, this.tile.ticksLeft);
            }
        }
        this.ticksLeft = this.tile.ticksLeft;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(final int par1, final int par2) {
        if (par1 == 0) {
            this.tile.ticksLeft = par2;
        }
    }

    public ItemStack transferStackInSlot(final EntityPlayer p_82846_1_, final int p_82846_2_) {
        ItemStack itemstack = null;
        final Slot slot = (Slot) this.inventorySlots.get(p_82846_2_);
        if (slot != null && slot.getHasStack()) {
            final ItemStack itemstack2 = slot.getStack();
            itemstack = itemstack2.copy();
            if (p_82846_2_ == 0) {
                if (!this.mergeItemStack(itemstack2, 1, 37, true)) {
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
                if (itemstack.getItem() != Item.getItemFromBlock(Blocks.soul_sand)
                        || !this.mergeItemStack(itemstack2, 0, 1, false)) {
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
}
