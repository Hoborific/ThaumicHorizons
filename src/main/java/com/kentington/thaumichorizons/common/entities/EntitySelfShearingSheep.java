// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.item.EntityItem;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.world.IBlockAccess;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import net.minecraft.entity.passive.EntitySheep;

public class EntitySelfShearingSheep extends EntitySheep
{
    public EntitySelfShearingSheep(final World p_i1691_1_) {
        super(p_i1691_1_);
    }
    
    public void onLivingUpdate() {
        if (!this.worldObj.isRemote && !this.getSheared() && this.ticksExisted % 100 == 0) {
            final ArrayList<ItemStack> drops = (ArrayList<ItemStack>)this.onSheared(new ItemStack((Item)Items.shears), (IBlockAccess)this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
            final Random rand = new Random();
            for (final ItemStack stack : drops) {
                final EntityItem entityDropItem;
                final EntityItem ent = entityDropItem = this.entityDropItem(stack, 1.0f);
                entityDropItem.motionY += rand.nextFloat() * 0.05f;
                final EntityItem entityItem = ent;
                entityItem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1f;
                final EntityItem entityItem2 = ent;
                entityItem2.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1f;
            }
        }
        super.onLivingUpdate();
    }
}
