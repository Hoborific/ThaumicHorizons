//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileBloodInfuser;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.container.SlotOutput;

public class ContainerBloodInfuser extends Container {
    TileBloodInfuser tile;
    EntityPlayer player;
    AspectList aspectsKnown;

    public ContainerBloodInfuser(final EntityPlayer p, final TileBloodInfuser tileEntity) {
        this.player = p;
        final InventoryPlayer inv = p.inventory;
        this.tile = tileEntity;
        this.aspectsKnown = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(p.getCommandSenderName());
        this.addSlotToContainer((Slot)
                new SlotRestricted((IInventory) this.tile, 0, 16, 37, new ItemStack(ThaumicHorizons.itemSyringeHuman)));
        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 3; ++y) {
                this.addSlotToContainer(
                        (Slot) new SlotOutput((IInventory) this.tile, x * 3 + y + 1, 108 + x * 18, 19 + y * 18));
            }
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory) inv, j + i * 9 + 9, 8 + j * 18, 137 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory) inv, i, 8 + i * 18, 195));
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
            if (p_82846_2_ < 10) {
                if (!this.mergeItemStack(itemstack2, 10, 46, true)) {
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
                if (itemstack2.getItem() != ThaumicHorizons.itemSyringeHuman
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

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(final int par1, final int par2) {
        super.updateProgressBar(par1, par2);
    }

    public void addCraftingToCrafters(final ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
    }

    public boolean enchantItem(final EntityPlayer par1EntityPlayer, int button) {
        if (button < 0) {
            button = -1 - button;
            this.tile.aspectsSelected.add(this.aspectsKnown.getAspectsSorted()[button], 1);
            this.tile.markDirty();
            return true;
        }
        if (button > 2) {
            button -= 3;
            this.tile.aspectsSelected.remove(this.aspectsKnown.getAspectsSorted()[button], 1);
            this.tile.markDirty();
            return true;
        }
        switch (button) {
            case 0: {
                this.tile.mode = 0;
                this.tile.markDirty();
                return true;
            }
            case 1: {
                this.tile.mode = 1;
                this.tile.markDirty();
                return true;
            }
            case 2: {
                this.tile.mode = 2;
                this.tile.markDirty();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
