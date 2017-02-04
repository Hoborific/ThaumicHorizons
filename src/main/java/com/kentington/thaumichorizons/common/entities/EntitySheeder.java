// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import java.util.ArrayList;
import net.minecraft.world.IBlockAccess;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.init.Items;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraftforge.common.IShearable;
import net.minecraft.entity.monster.EntitySpider;

public class EntitySheeder extends EntitySpider implements IShearable
{
    private int sheepTimer;
    private EntityAIEatGrass field_146087_bs;
    
    public EntitySheeder(final World p_i1743_1_) {
        super(p_i1743_1_);
        this.field_146087_bs = new EntityAIEatGrass((EntityLiving)this);
        this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 0.5));
        this.tasks.addTask(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 0.44, Items.wheat, false));
        this.tasks.addTask(5, (EntityAIBase)this.field_146087_bs);
        this.tasks.addTask(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.4));
        this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, (Class)EntityPlayer.class, 6.0f));
        this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    }
    
    public void onUpdate() {
        super.onUpdate();
        if (this.isDead && !this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL && this.getHealth() > 0.0f) {
            this.isDead = false;
        }
    }
    
    protected Entity findPlayerToAttack() {
        return null;
    }
    
    protected String getLivingSound() {
        return "mob.sheep.say";
    }
    
    public boolean isShearable(final ItemStack item, final IBlockAccess world, final int x, final int y, final int z) {
        return !this.getSheared() && !this.isChild();
    }
    
    public ArrayList<ItemStack> onSheared(final ItemStack item, final IBlockAccess world, final int x, final int y, final int z, final int fortune) {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        this.setSheared(true);
        for (int i = 2 + this.rand.nextInt(4), j = 0; j < i; ++j) {
            ret.add(new ItemStack(Items.string, 1, 0));
        }
        this.playSound("mob.sheep.shear", 1.0f, 1.0f);
        return ret;
    }
    
    public void setSheared(final boolean p_70893_1_) {
        final byte b0 = this.dataWatcher.getWatchableObjectByte(16);
        if (p_70893_1_) {
            this.dataWatcher.updateObject(16, (Object)(byte)(b0 | 0x10));
        }
        else {
            this.dataWatcher.updateObject(16, (Object)(byte)(b0 & 0xFFFFFFEF));
        }
    }
    
    public boolean getSheared() {
        return (this.dataWatcher.getWatchableObjectByte(16) & 0x10) != 0x0;
    }
    
    public void eatGrassBonus() {
        this.setSheared(false);
    }
    
    public void writeEntityToNBT(final NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
        p_70014_1_.setBoolean("Sheared", this.getSheared());
    }
    
    public void readEntityFromNBT(final NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        this.setSheared(p_70037_1_.getBoolean("Sheared"));
    }
    
    protected void dropFewItems(final boolean p_70628_1_, final int p_70628_2_) {
        if (!this.getSheared()) {
            this.entityDropItem(new ItemStack(Items.string), 0.0f);
        }
    }
    
    protected Item getDropItem() {
        return Items.string;
    }
    
    protected boolean isAIEnabled() {
        return true;
    }
    
    protected void updateAITasks() {
        this.sheepTimer = this.field_146087_bs.func_151499_f();
        super.updateAITasks();
    }
    
    public void onLivingUpdate() {
        if (this.worldObj.isRemote) {
            this.sheepTimer = Math.max(0, this.sheepTimer - 1);
        }
        super.onLivingUpdate();
    }
    
    @SideOnly(Side.CLIENT)
    public void handleHealthUpdate(final byte p_70103_1_) {
        if (p_70103_1_ == 10) {
            this.sheepTimer = 40;
        }
        else {
            super.handleHealthUpdate(p_70103_1_);
        }
    }
}
