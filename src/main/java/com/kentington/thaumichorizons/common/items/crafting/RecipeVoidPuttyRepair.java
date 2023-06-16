//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items.crafting;

import java.util.ArrayList;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

public class RecipeVoidPuttyRepair implements IRecipe {

    public ItemStack getCraftingResult(final InventoryCrafting par1InventoryCrafting) {
        ItemStack itemstack = null;
        final ArrayList<ItemStack> arraylist = new ArrayList<>();
        for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i) {
            final ItemStack itemstack2 = par1InventoryCrafting.getStackInSlot(i);
            if (itemstack2 != null) {
                if (itemstack2.getItem().isDamageable() && itemstack2.getItem().isRepairable()
                        && itemstack2.getItem().isDamaged(itemstack2)) {
                    if (itemstack != null) {
                        return null;
                    }
                    itemstack = itemstack2;
                } else {
                    if (itemstack2.getItem() != ThaumicHorizons.itemVoidPutty) {
                        return null;
                    }
                    arraylist.add(itemstack2);
                }
            }
        }
        final ItemStack output = itemstack.copy();
        output.setItemDamage(0);
        return output;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }

    public int getRecipeSize() {
        return 10;
    }

    public boolean matches(final InventoryCrafting par1InventoryCrafting, final World arg1) {
        ItemStack itemstack = null;
        final ArrayList<ItemStack> arraylist = new ArrayList<>();
        for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i) {
            final ItemStack itemstack2 = par1InventoryCrafting.getStackInSlot(i);
            if (itemstack2 != null) {
                if (itemstack2.getItem().isDamageable() && itemstack2.getItem().isDamaged(itemstack2)) {
                    if (itemstack != null) {
                        return false;
                    }
                    itemstack = itemstack2;
                } else {
                    if (itemstack2.getItem() != ThaumicHorizons.itemVoidPutty) {
                        return false;
                    }
                    arraylist.add(itemstack2);
                }
            }
        }
        return itemstack != null && !arraylist.isEmpty();
    }
}
