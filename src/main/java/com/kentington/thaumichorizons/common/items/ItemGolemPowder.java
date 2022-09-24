//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;

public class ItemGolemPowder extends Item {
    @SideOnly(Side.CLIENT)
    public IIcon icon;

    public ItemGolemPowder() {
        this.setMaxStackSize(64);
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:golempowder");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }

    public String getUnlocalizedName(final ItemStack par1ItemStack) {
        return "item.golemPowder";
    }

    public boolean onItemUse(
            final ItemStack p_77648_1_,
            final EntityPlayer player,
            final World world,
            final int p_77648_4_,
            final int p_77648_5_,
            final int p_77648_6_,
            final int p_77648_7_,
            final float p_77648_8_,
            final float p_77648_9_,
            final float p_77648_10_) {
        final Block blocky = world.getBlock(p_77648_4_, p_77648_5_, p_77648_6_);
        final int md = world.getBlockMetadata(p_77648_4_, p_77648_5_, p_77648_6_);
        if (!player.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_)) {
            return false;
        }
        if (world.isRemote) {
            return true;
        }
        if (!blocky.hasTileEntity(md)
                && !blocky.isAir((IBlockAccess) world, p_77648_4_, p_77648_5_, p_77648_6_)
                && (blocky.isOpaqueCube() || ItemFocusAnimation.isWhitelisted(blocky, md))
                && blocky.getBlockHardness(world, p_77648_4_, p_77648_5_, p_77648_6_) != -1.0f) {
            WorldSettings.GameType gt = WorldSettings.GameType.SURVIVAL;
            if (player.capabilities.allowEdit) {
                if (player.capabilities.isCreativeMode) {
                    gt = WorldSettings.GameType.CREATIVE;
                }
            } else {
                gt = WorldSettings.GameType.ADVENTURE;
            }
            if (!world.isRemote) {
                final EntityGolemTH golem = new EntityGolemTH(world);
                golem.loadGolem(p_77648_4_ + 0.5, p_77648_5_, p_77648_6_ + 0.5, blocky, md, 1200, false, false, false);
                final BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent(
                        player.worldObj, gt, (EntityPlayerMP) player, p_77648_4_, p_77648_5_, p_77648_6_);
                if (event.isCanceled()) {
                    golem.setDead();
                    return false;
                }
                world.setBlockToAir(p_77648_4_, p_77648_5_, p_77648_6_);
                world.playSoundEffect(
                        p_77648_4_ + 0.5, p_77648_5_ + 0.5, p_77648_6_ + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                golem.setHomeArea((int) golem.posX, (int) golem.posY, (int) golem.posZ, 32);
                golem.setOwner(player.getCommandSenderName());
                world.spawnEntityInWorld((Entity) golem);
                world.setEntityState((Entity) golem, (byte) 7);
            } else {
                Minecraft.getMinecraft()
                        .effectRenderer
                        .addBlockDestroyEffects(p_77648_4_, p_77648_5_, p_77648_6_, blocky, md);
                player.swingItem();
            }
            return true;
        }
        return false;
    }
}
