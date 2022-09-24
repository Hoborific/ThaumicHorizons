//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemWandCastingDisposable extends ItemWandCasting {
    public ItemStack wand;

    public ItemWandCastingDisposable() {
        this.maxStackSize = 1;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(ThaumicHorizons.tabTH);
        final ItemStack w1 = new ItemStack((Item) this);
        this.setCap(w1, ThaumicHorizons.CAP_CRYSTAL);
        this.setRod(w1, ThaumicHorizons.ROD_CRYSTAL);
        for (final Aspect aspect : Aspect.getPrimalAspects()) {
            this.storeVis(w1, aspect, 25000);
        }
        this.wand = w1;
    }

    public int getMaxVis(final ItemStack stack) {
        final StackTraceElement[] stackTrace;
        final StackTraceElement[] above = stackTrace = Thread.currentThread().getStackTrace();
        for (final StackTraceElement el : stackTrace) {
            if (el.getMethodName().equals("onWornTick") || el.getMethodName().equals("updateEntity")) {
                return 0;
            }
        }
        return 25000;
    }

    public AspectList getAspectsWithRoom(final ItemStack wandstack) {
        return new AspectList();
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item par1, final CreativeTabs par2CreativeTabs, final List par3List) {
        par3List.add(this.wand);
    }
}
