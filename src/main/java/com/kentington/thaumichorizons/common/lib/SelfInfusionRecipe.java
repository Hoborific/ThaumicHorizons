// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import net.minecraftforge.oredict.OreDictionary;
import java.util.Iterator;
import thaumcraft.api.ThaumcraftApiHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;

public class SelfInfusionRecipe
{
    protected AspectList aspects;
    protected String research;
    private ItemStack[] components;
    protected int instability;
    protected int id;
    
    public SelfInfusionRecipe(final String research, final int inst, final AspectList aspects2, final ItemStack[] recipe, final int id) {
        this.research = research;
        this.instability = inst;
        this.aspects = aspects2.copy();
        this.components = recipe;
        this.id = id;
    }
    
    public boolean matches(final ArrayList<ItemStack> input, final World world, final EntityPlayer player) {
        if (this.research.length() > 0 && !ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), this.research)) {
            return false;
        }
        final ArrayList<ItemStack> ii = new ArrayList<ItemStack>();
        for (final ItemStack is : input) {
            ii.add(is.copy());
        }
        for (final ItemStack comp : this.getComponents()) {
            boolean b = false;
            for (int a = 0; a < ii.size(); ++a) {
                final ItemStack i2 = ii.get(a).copy();
                if (comp.getItemDamage() == 32767) {
                    i2.setItemDamage(32767);
                }
                if (areItemStacksEqual(i2, comp, true)) {
                    ii.remove(a);
                    b = true;
                    break;
                }
            }
            if (!b) {
                return false;
            }
        }
        return ii.size() == 0;
    }
    
    public static boolean areItemStacksEqual(final ItemStack stack0, final ItemStack stack1, final boolean fuzzy) {
        if (stack0 == null && stack1 != null) {
            return false;
        }
        if (stack0 != null && stack1 == null) {
            return false;
        }
        if (stack0 == null && stack1 == null) {
            return true;
        }
        final boolean t1 = ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(stack0, stack1);
        if (!t1) {
            return false;
        }
        if (fuzzy) {
            final int od = OreDictionary.getOreID(stack0);
            if (od != -1) {
                final ItemStack[] ores = OreDictionary.getOres(od).toArray(new ItemStack[0]);
                if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { stack1 }, ores)) {
                    return true;
                }
            }
        }
        final boolean damage = stack0.getItemDamage() == stack1.getItemDamage() || stack1.getItemDamage() == 32767;
        return stack0.getItem() == stack1.getItem() && damage && stack0.stackSize <= stack0.getMaxStackSize();
    }
    
    public AspectList getAspects() {
        return this.aspects;
    }
    
    public int getInstability() {
        return this.instability;
    }
    
    public String getResearch() {
        return this.research;
    }
    
    public ItemStack[] getComponents() {
        return this.components;
    }
    
    public int getID() {
        return this.id;
    }
}
