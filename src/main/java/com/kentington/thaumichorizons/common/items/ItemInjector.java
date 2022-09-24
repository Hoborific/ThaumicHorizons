//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityBlastPhial;
import com.kentington.thaumichorizons.common.entities.EntitySyringe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;

public class ItemInjector extends ItemBow implements IRepairable {
    public ItemInjector() {
        this.maxStackSize = 1;
        this.setMaxDamage(1000);
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setTextureName("thaumichorizons:injector");
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.injector";
    }

    public int getItemEnchantability() {
        return 3;
    }

    public EnumAction getItemUseAction(final ItemStack p_77661_1_) {
        return EnumAction.none;
    }

    public void onPlayerStoppedUsing(
            final ItemStack p_77615_1_, final World p_77615_2_, final EntityPlayer p_77615_3_, final int p_77615_4_) {
        final int j = this.getMaxItemUseDuration(null) - p_77615_4_;
        if (this.getAmmo(p_77615_1_, 0) != null) {
            float f = j / 30.0f;
            f = (f * f + f * 2.0f) / 3.0f;
            if (f < 0.1) {
                p_77615_1_.stackTagCompound.setInteger("usetime", 0);
                return;
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            IProjectile projectile = null;
            if (this.getAmmo(p_77615_1_, 0).getItemDamage() == 0) {
                final EntitySyringe entityarrow = new EntitySyringe(
                        p_77615_2_,
                        (EntityLivingBase) p_77615_3_,
                        f * 2.0f,
                        this.getAmmo(p_77615_1_, 0).stackTagCompound);
                if (f == 1.0f) {
                    entityarrow.setIsCritical(true);
                }
                final int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, p_77615_1_);
                if (k > 0) {
                    entityarrow.setDamage(entityarrow.getDamage() + k * 0.5 + 0.5);
                }
                final int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, p_77615_1_);
                if (l > 0) {
                    entityarrow.setKnockbackStrength(l);
                }
                if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, p_77615_1_) > 0) {
                    entityarrow.setFire(100);
                }
                projectile = (IProjectile) entityarrow;
            } else {
                projectile = (IProjectile) new EntityBlastPhial(
                        p_77615_2_, (EntityLivingBase) p_77615_3_, f * 2.0f, this.getAmmo(p_77615_1_, 0));
            }
            p_77615_1_.damageItem(1, (EntityLivingBase) p_77615_3_);
            p_77615_2_.playSoundAtEntity(
                    (Entity) p_77615_3_,
                    "random.bow",
                    1.0f,
                    1.0f / (ItemInjector.itemRand.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
            if (!p_77615_2_.isRemote) {
                p_77615_2_.spawnEntityInWorld((Entity) projectile);
                this.rotateAmmo(p_77615_1_);
            }
            p_77615_1_.stackTagCompound.setInteger("usetime", 0);
            p_77615_1_.stackTagCompound.setInteger(
                    "rotationTarget", p_77615_1_.stackTagCompound.getInteger("rotationTarget") + 90);
            if (p_77615_1_.stackTagCompound.getInteger("rotationTarget") > 360) {
                p_77615_1_.stackTagCompound.setInteger(
                        "rotationTarget", p_77615_1_.stackTagCompound.getInteger("rotationTarget") - 360);
            }
        }
    }

    public ItemStack onItemRightClick(
            final ItemStack p_77659_1_, final World p_77659_2_, final EntityPlayer p_77659_3_) {
        if (p_77659_3_.isSneaking()) {
            p_77659_3_.openGui(
                    (Object) ThaumicHorizons.instance,
                    6,
                    p_77659_2_,
                    MathHelper.floor_double(p_77659_3_.posX),
                    MathHelper.floor_double(p_77659_3_.posY),
                    MathHelper.floor_double(p_77659_3_.posZ));
        } else if (this.getAmmo(p_77659_1_, 0) == null) {
            this.rotateAmmo(p_77659_1_);
            p_77659_2_.playSoundEffect(
                    p_77659_3_.posX,
                    p_77659_3_.posY + p_77659_3_.getEyeHeight(),
                    p_77659_3_.posZ,
                    "random.click",
                    1.0f,
                    1.0f);
        } else {
            p_77659_3_.setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
        }
        return p_77659_1_;
    }

    public ItemStack getAmmo(final ItemStack stack, final int slot) {
        if (stack.stackTagCompound == null) {
            return null;
        }
        final NBTTagList ammo = stack.stackTagCompound.getTagList("ammo", 10);
        return ItemStack.loadItemStackFromNBT(ammo.getCompoundTagAt(slot));
    }

    void rotateAmmo(final ItemStack stack) {
        if (stack.stackTagCompound == null) {
            return;
        }
        final NBTTagList ammo = stack.stackTagCompound.getTagList("ammo", 10);
        final NBTTagList newAmmo = new NBTTagList();
        for (int i = 1; i < 7; ++i) {
            if (ammo.getCompoundTagAt(i) != null) {
                newAmmo.appendTag(ammo.getCompoundTagAt(i).copy());
            } else {
                newAmmo.appendTag((NBTBase) new NBTTagCompound());
            }
        }
        newAmmo.appendTag((NBTBase) new NBTTagCompound());
        stack.stackTagCompound.setTag("ammo", (NBTBase) newAmmo);
    }

    public void onUsingTick(final ItemStack stack, final EntityPlayer player, final int count) {
        if (player.worldObj.isRemote) {
            stack.stackTagCompound.setInteger("usetime", stack.stackTagCompound.getInteger("usetime") + 1);
            stack.stackTagCompound.setInteger("rotationTarget", stack.stackTagCompound.getInteger("rotation"));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister p_94581_1_) {}

    @SideOnly(Side.CLIENT)
    public IIcon getItemIconForUseDuration(final int p_94599_1_) {
        return null;
    }

    public int getMaxItemUseDuration(final ItemStack p_77626_1_) {
        return 72000;
    }

    public ItemStack onEaten(final ItemStack p_77654_1_, final World p_77654_2_, final EntityPlayer p_77654_3_) {
        return p_77654_1_;
    }
}
