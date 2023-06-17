//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.entities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpawnerEgg extends Item {

    static ArrayList<EntityEggStuff> spawnList;

    @SideOnly(Side.CLIENT)
    private IIcon theIcon;

    public static void addMapping(final String name, final int c1, final int c2) {
        ItemSpawnerEgg.spawnList.add(new EntityEggStuff("ThaumicHorizons." + name, c1, c2));
    }

    public ItemSpawnerEgg() {
        this.setHasSubtypes(true);
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public String getItemStackDisplayName(final ItemStack par1ItemStack) {
        String s = ("" + StatCollector.translateToLocal("item.monsterPlacer.name")).trim();
        final String s2 = ItemSpawnerEgg.spawnList.get(par1ItemStack.getItemDamage()).name;
        if (s2 != null) {
            s = s + " " + StatCollector.translateToLocal("entity." + s2 + ".name");
        }
        return s;
    }

    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(final ItemStack stack, final int layer) {
        final EntityEggStuff entityegginfo = ItemSpawnerEgg.spawnList.get(stack.getItemDamage());
        return (entityegginfo != null) ? entityegginfo.color2 : ((layer == 0) ? entityegginfo.color1 : 16777215);
    }

    public boolean onItemUse(final ItemStack stack, final EntityPlayer player, final World world, int x, int y, int z,
            final int side, final float par8, final float par9, final float par10) {
        if (world.isRemote) {
            return true;
        }
        final Block block = world.getBlock(x, y, z);
        x += Facing.offsetsXForSide[side];
        y += Facing.offsetsYForSide[side];
        z += Facing.offsetsZForSide[side];
        double d0 = 0.0;
        if (side == 1 && block.getRenderType() == 11) {
            d0 = 0.5;
        }
        final Entity entity = spawnCreature(world, stack.getItemDamage(), x + 0.5, y + d0, z + 0.5);
        if (entity != null) {
            if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
                ((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
            }
            if (!player.capabilities.isCreativeMode) {
                --stack.stackSize;
            }
        }
        return true;
    }

    public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
        if (world.isRemote) {
            return stack;
        }
        final MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);
        if (movingobjectposition == null) {
            return stack;
        }
        if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            final int i = movingobjectposition.blockX;
            final int j = movingobjectposition.blockY;
            final int k = movingobjectposition.blockZ;
            if (!world.canMineBlock(player, i, j, k)) {
                return stack;
            }
            if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, stack)) {
                return stack;
            }
            if (world.getBlock(i, j, k) instanceof BlockLiquid) {
                final Entity entity = spawnCreature(world, stack.getItemDamage(), i, j, k);
                if (entity != null) {
                    if (entity instanceof EntityLivingBase && stack.hasDisplayName()) {
                        ((EntityLiving) entity).setCustomNameTag(stack.getDisplayName());
                    }
                    if (!player.capabilities.isCreativeMode) {
                        --stack.stackSize;
                    }
                }
            }
        }
        return stack;
    }

    public static Entity spawnCreature(final World par0World, final int par1, final double par2, final double par4,
            final double par6) {
        if (ItemSpawnerEgg.spawnList.get(par1) == null) {
            return null;
        }
        Entity entity = null;
        for (int j = 0; j < 1; ++j) {
            entity = EntityList.createEntityByName(ItemSpawnerEgg.spawnList.get(par1).name, par0World);
            if (entity instanceof EntityLivingBase) {
                final EntityLiving entityliving = (EntityLiving) entity;
                entity.setLocationAndAngles(
                        par2,
                        par4,
                        par6,
                        MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0f),
                        0.0f);
                entityliving.rotationYawHead = entityliving.rotationYaw;
                entityliving.renderYawOffset = entityliving.rotationYaw;
                entityliving.onSpawnWithEgg((IEntityLivingData) null);
                par0World.spawnEntityInWorld(entity);
                entityliving.playLivingSound();
            }
        }
        return entity;
    }

    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(final int par1, final int par2) {
        return (par2 > 0) ? this.theIcon : super.getIconFromDamageForRenderPass(par1, par2);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item p_150895_1_, final CreativeTabs p_150895_2_, final List p_150895_3_) {
        for (int a = 0; a < ItemSpawnerEgg.spawnList.size(); ++a) {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, a));
        }
    }

    protected String getIconString() {
        return "spawn_egg";
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
        this.theIcon = par1IconRegister.registerIcon(this.getIconString() + "_overlay");
    }

    static {
        ItemSpawnerEgg.spawnList = new ArrayList<>();
    }

    static class EntityEggStuff {

        String name;
        int color1;
        int color2;

        public EntityEggStuff(final String name, final int color1, final int color2) {
            this.name = name;
            this.color1 = color1;
            this.color2 = color2;
        }
    }
}
