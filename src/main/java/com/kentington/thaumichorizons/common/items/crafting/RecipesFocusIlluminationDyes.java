//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items.crafting;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.items.ItemFocusIllumination;

public class RecipesFocusIlluminationDyes implements IRecipe {

    public boolean matches(final InventoryCrafting par1InventoryCrafting, final World p_77569_2_) {
        ItemStack itemstack = null;
        final ArrayList<ItemStack> arraylist = new ArrayList<>();
        for (int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i) {
            final ItemStack itemstack2 = par1InventoryCrafting.getStackInSlot(i);
            if (itemstack2 != null) {
                if (itemstack2.getItem() instanceof final ItemFocusIllumination itemarmor) {
                    if (itemstack != null) {
                        return false;
                    }
                    itemstack = itemstack2;
                } else {
                    if (itemstack2.getItem() != Items.dye) {
                        return false;
                    }
                    arraylist.add(itemstack2);
                }
            }
        }
        return itemstack != null && !arraylist.isEmpty();
    }

    public ItemStack getCraftingResult(final InventoryCrafting par1InventoryCrafting) {
        ItemStack itemstack = null;
        ItemFocusIllumination itemarmor = null;
        int color = 0;
        for (int k = 0; k < par1InventoryCrafting.getSizeInventory(); ++k) {
            final ItemStack itemstack2 = par1InventoryCrafting.getStackInSlot(k);
            if (itemstack2 != null) {
                if (itemstack2.getItem() instanceof ItemFocusIllumination) {
                    itemarmor = (ItemFocusIllumination) itemstack2.getItem();
                    if (itemstack != null) {
                        return null;
                    }
                    itemstack = itemstack2.copy();
                    itemstack.stackSize = 1;
                } else {
                    if (itemstack2.getItem() != Items.dye) {
                        return null;
                    }
                    color = itemstack2.getItemDamage();
                }
            }
        }
        if (itemarmor == null) {
            return null;
        }
        itemstack.setItemDamage(color);
        return itemstack;
    }

    public int getRecipeSize() {
        return 10;
    }

    public ItemStack getRecipeOutput() {
        return null;
    }
}
