//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.IIcon;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;
import thaumcraft.common.entities.golems.ItemGolemBell;
import thaumcraft.common.entities.golems.Marker;

public class ItemGolemBellTH extends ItemGolemBell {

    public ItemGolemBellTH() {
        this.setHasSubtypes(false);
        this.setCreativeTab(ThaumicHorizons.tabTH);
        this.setMaxStackSize(1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(final int par1) {
        return ((ItemGolemPlacer) ThaumicHorizons.itemGolemPlacer).newBell;
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.GolemBellTH";
    }

    @Override
    public boolean itemInteractionForEntity(final ItemStack stack, final EntityPlayer player,
            final EntityLivingBase target) {
        if (target instanceof EntityGolemBase) {
            if (stack.hasTagCompound()) {
                stack.getTagCompound().removeTag("golemid");
                stack.getTagCompound().removeTag("markers");
                stack.getTagCompound().removeTag("golemhomex");
                stack.getTagCompound().removeTag("golemhomey");
                stack.getTagCompound().removeTag("golemhomez");
                stack.getTagCompound().removeTag("golemhomeface");
            }
            if (target.worldObj.isRemote) {
                if (player != null) {
                    player.swingItem();
                }
            } else {
                final ArrayList<Marker> markers = ((EntityGolemBase) target).getMarkers();
                final NBTTagList tl = new NBTTagList();
                for (final Marker l : markers) {
                    final NBTTagCompound nbtc = new NBTTagCompound();
                    nbtc.setInteger("x", l.x);
                    nbtc.setInteger("y", l.y);
                    nbtc.setInteger("z", l.z);
                    nbtc.setInteger("dim", l.dim);
                    nbtc.setByte("side", l.side);
                    nbtc.setByte("color", l.color);
                    tl.appendTag(nbtc);
                }
                stack.setTagInfo("markers", tl);
                stack.getTagCompound().setInteger("golemid", target.getEntityId());
                stack.getTagCompound().setInteger("golemhomex", ((EntityGolemBase) target).getHomePosition().posX);
                stack.getTagCompound().setInteger("golemhomey", ((EntityGolemBase) target).getHomePosition().posY);
                stack.getTagCompound().setInteger("golemhomez", ((EntityGolemBase) target).getHomePosition().posZ);
                stack.getTagCompound().setInteger("golemhomeface", ((EntityGolemBase) target).homeFacing);
                target.worldObj
                        .playSoundAtEntity(target, "random.orb", 0.7f, 1.0f + target.worldObj.rand.nextFloat() * 0.1f);
                if (player != null && player.capabilities.isCreativeMode) {
                    player.setCurrentItemOrArmor(0, stack.copy());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {
        if (entity instanceof EntityTravelingTrunk && !entity.isDead) {
            final byte upgrade = (byte) ((EntityTravelingTrunk) entity).getUpgrade();
            if (upgrade == 3
                    && !((EntityTravelingTrunk) entity).func_152113_b().equals(player.getCommandSenderName())) {
                return false;
            }
            if (entity.worldObj.isRemote && entity instanceof EntityLiving) {
                ((EntityLiving) entity).spawnExplosionParticle();
                return false;
            }
            final ItemStack dropped = new ItemStack(ConfigItems.itemTrunkSpawner);
            if (player.isSneaking()) {
                if (upgrade > -1 && entity.worldObj.rand.nextBoolean()) {
                    entity.entityDropItem(new ItemStack(ConfigItems.itemGolemUpgrade, 1, upgrade), 0.5f);
                }
            } else {
                if (((EntityTravelingTrunk) entity).hasCustomNameTag()) {
                    dropped.setStackDisplayName(((EntityTravelingTrunk) entity).getCustomNameTag());
                }
                dropped.setTagInfo("upgrade", new NBTTagByte(upgrade));
                if (upgrade == 4) {
                    dropped.setTagInfo(
                            "inventory",
                            ((EntityTravelingTrunk) entity).inventory.writeToNBT(new NBTTagList()));
                }
            }
            entity.entityDropItem(dropped, 0.5f);
            if (upgrade != 4 || player.isSneaking()) {
                ((EntityTravelingTrunk) entity).inventory.dropAllItems();
            }
            entity.worldObj.playSoundAtEntity(entity, "thaumcraft:zap", 0.5f, 1.0f);
            entity.setDead();
            return true;
        } else if (entity instanceof EntityGolemBase && !(entity instanceof EntityGolemTH) && !entity.isDead) {
            if (entity.worldObj.isRemote && entity instanceof EntityLiving) {
                ((EntityLiving) entity).spawnExplosionParticle();
                return false;
            }
            final int type = ((EntityGolemBase) entity).golemType.ordinal();
            final String deco = ((EntityGolemBase) entity).decoration;
            final byte core = ((EntityGolemBase) entity).getCore();
            final byte[] upgrades = ((EntityGolemBase) entity).upgrades;
            final boolean advanced = ((EntityGolemBase) entity).advanced;
            final ItemStack dropped2 = new ItemStack(ConfigItems.itemGolemPlacer, 1, type);
            if (advanced) {
                dropped2.setTagInfo("advanced", new NBTTagByte((byte) 1));
            }
            if (player.isSneaking()) {
                if (core > -1) {
                    entity.entityDropItem(new ItemStack(ConfigItems.itemGolemCore, 1, core), 0.5f);
                }
                for (final byte b : upgrades) {
                    if (b > -1 && entity.worldObj.rand.nextBoolean()) {
                        entity.entityDropItem(new ItemStack(ConfigItems.itemGolemUpgrade, 1, b), 0.5f);
                    }
                }
            } else {
                if (((EntityGolemBase) entity).hasCustomNameTag()) {
                    dropped2.setStackDisplayName(((EntityGolemBase) entity).getCustomNameTag());
                }
                if (deco.length() > 0) {
                    dropped2.setTagInfo("deco", new NBTTagString(deco));
                }
                if (core > -1) {
                    dropped2.setTagInfo("core", new NBTTagByte(core));
                }
                dropped2.setTagInfo("upgrades", new NBTTagByteArray(upgrades));
                final ArrayList<Marker> markers = ((EntityGolemBase) entity).getMarkers();
                final NBTTagList tl = new NBTTagList();
                for (final Marker l : markers) {
                    final NBTTagCompound nbtc = new NBTTagCompound();
                    nbtc.setInteger("x", l.x);
                    nbtc.setInteger("y", l.y);
                    nbtc.setInteger("z", l.z);
                    nbtc.setInteger("dim", l.dim);
                    nbtc.setByte("side", l.side);
                    nbtc.setByte("color", l.color);
                    tl.appendTag(nbtc);
                }
                dropped2.setTagInfo("markers", tl);
                dropped2.setTagInfo("Inventory", ((EntityGolemBase) entity).inventory.writeToNBT(new NBTTagList()));
            }
            entity.entityDropItem(dropped2, 0.5f);
            ((EntityGolemBase) entity).dropStuff();
            entity.worldObj.playSoundAtEntity(entity, "thaumcraft:zap", 0.5f, 1.0f);
            entity.setDead();
            return true;
        } else {
            if (!(entity instanceof EntityGolemTH) || entity.isDead) {
                return false;
            }
            if (entity.worldObj.isRemote && entity instanceof EntityLiving) {
                ((EntityLiving) entity).spawnExplosionParticle();
                return false;
            }
            final EntityGolemTH golem = (EntityGolemTH) entity;
            if (golem.getCore() == -1) {
                golem.ticksAlive = 0;
                return true;
            }
            final int type2 = golem.type.ordinal();
            final String deco2 = golem.decoration;
            final byte core2 = golem.getCore();
            final byte[] upgrades2 = golem.upgrades;
            final int[] blockData = new int[2];
            final int n = 0;
            final Block blocky = golem.blocky;
            blockData[n] = Block.getIdFromBlock(golem.blocky);
            blockData[1] = golem.md;
            final boolean advanced2 = golem.advanced;
            final ItemStack dropped3 = new ItemStack(ThaumicHorizons.itemGolemPlacer, 1, type2);
            if (advanced2) {
                dropped3.setTagInfo("advanced", new NBTTagByte((byte) 1));
            }
            if (player.isSneaking()) {
                if (core2 > -1) {
                    entity.entityDropItem(new ItemStack(ConfigItems.itemGolemCore, 1, core2), 0.5f);
                }
                for (final byte b2 : upgrades2) {
                    if (b2 > -1 && entity.worldObj.rand.nextBoolean()) {
                        entity.entityDropItem(new ItemStack(ConfigItems.itemGolemUpgrade, 1, b2), 0.5f);
                    }
                }
                golem.die();
                return true;
            }
            if (((EntityGolemBase) entity).hasCustomNameTag()) {
                dropped3.setStackDisplayName(((EntityGolemBase) entity).getCustomNameTag());
            }
            if (deco2.length() > 0) {
                dropped3.setTagInfo("deco", new NBTTagString(deco2));
            }
            if (core2 > -1) {
                dropped3.setTagInfo("core", new NBTTagByte(core2));
            }
            dropped3.setTagInfo("upgrades", new NBTTagByteArray(upgrades2));
            dropped3.setTagInfo("block", new NBTTagIntArray(blockData));
            dropped3.stackTagCompound.setBoolean("berserk", golem.berserk);
            dropped3.stackTagCompound.setBoolean("explosive", golem.kaboom);
            final ArrayList<Marker> markers2 = ((EntityGolemBase) entity).getMarkers();
            final NBTTagList tl2 = new NBTTagList();
            for (final Marker i : markers2) {
                final NBTTagCompound nbtc2 = new NBTTagCompound();
                nbtc2.setInteger("x", i.x);
                nbtc2.setInteger("y", i.y);
                nbtc2.setInteger("z", i.z);
                nbtc2.setInteger("dim", i.dim);
                nbtc2.setByte("side", i.side);
                nbtc2.setByte("color", i.color);
                tl2.appendTag(nbtc2);
            }
            dropped3.setTagInfo("markers", tl2);
            dropped3.setTagInfo("Inventory", ((EntityGolemBase) entity).inventory.writeToNBT(new NBTTagList()));
            entity.entityDropItem(dropped3, 0.5f);
            ((EntityGolemBase) entity).dropStuff();
            entity.worldObj.playSoundAtEntity(entity, "thaumcraft:zap", 0.5f, 1.0f);
            entity.setDead();
            return true;
        }
    }
}
