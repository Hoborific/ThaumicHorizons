//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSoulJar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSoulJarItem extends ItemBlock {
    @SideOnly(Side.CLIENT)
    public IIcon icon;

    public BlockSoulJarItem(final Block p_i45328_1_) {
        super(p_i45328_1_);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(final Item par1, final CreativeTabs par2CreativeTabs, final List par3List) {
        final ItemStack soul = new ItemStack((Item) this, 1, 0);
        (soul.stackTagCompound = new NBTTagCompound()).setBoolean("isSoul", true);
        par3List.add(soul);
    }

    public String getItemStackDisplayName(final ItemStack stack) {
        String stringy = StatCollector.translateToLocal("item.jarredSoul.jarred");
        if (stack.hasTagCompound()) {
            stringy = stringy + " " + stack.getTagCompound().getString("jarredCritterName");
            if (stack.getTagCompound().getBoolean("isSoul")) {
                stringy = stringy + " " + StatCollector.translateToLocal("item.jarredSoul.soul");
            }
        } else {
            stringy = StatCollector.translateToLocal("item.jarredSoul.0.name");
        }
        return stringy;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumcraft:blank");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(final int par1) {
        return this.icon;
    }

    public int getMetadata(final int par1) {
        return par1;
    }

    public boolean onItemUse(
            final ItemStack stack,
            final EntityPlayer player,
            final World world,
            int x,
            int y,
            int z,
            int side,
            final float par8,
            final float par9,
            final float par10) {
        final Block block = world.getBlock(x, y, z);
        if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 0x7) < 1) {
            side = 1;
        } else if (block != Blocks.vine
                && block != Blocks.tallgrass
                && block != Blocks.deadbush
                && !block.isReplaceable((IBlockAccess) world, x, y, z)) {
            if (side == 0) {
                --y;
            }
            if (side == 1) {
                ++y;
            }
            if (side == 2) {
                --z;
            }
            if (side == 3) {
                ++z;
            }
            if (side == 4) {
                --x;
            }
            if (side == 5) {
                ++x;
            }
        }
        if (stack.stackSize == 0) {
            return false;
        }
        if (!player.canPlayerEdit(x, y, z, side, stack)) {
            return false;
        }
        if (world.canPlaceEntityOnSide(ThaumicHorizons.blockJar, x, y, z, false, side, (Entity) player, stack)) {
            final Block var12 = ThaumicHorizons.blockJar;
            final int var13 = this.getMetadata(stack.getItemDamage());
            final int var14 = ThaumicHorizons.blockJar.onBlockPlaced(world, x, y, z, side, par8, par9, par10, var13);
            if (this.placeBlockAt(stack, player, world, x, y, z, side, par8, par9, par10, var14)) {
                final TileEntity te = world.getTileEntity(x, y, z);
                if (te != null && te instanceof TileSoulJar && stack.hasTagCompound()) {
                    ((TileSoulJar) te).jarTag = stack.getTagCompound();
                }
                world.playSoundEffect(
                        (double) (x + 0.5f),
                        (double) (y + 0.5f),
                        (double) (z + 0.5f),
                        var12.stepSound.func_150496_b(),
                        (var12.stepSound.getVolume() + 1.0f) / 2.0f,
                        var12.stepSound.getPitch() * 0.8f);
                --stack.stackSize;
            }
            return true;
        }
        return false;
    }

    public boolean placeBlockAt(
            final ItemStack stack,
            final EntityPlayer player,
            final World world,
            final int x,
            final int y,
            final int z,
            final int side,
            final float hitX,
            final float hitY,
            final float hitZ,
            final int metadata) {
        if (!world.setBlock(x, y, z, ThaumicHorizons.blockJar, metadata, 3)) {
            return false;
        }
        if (world.getBlock(x, y, z) == ThaumicHorizons.blockJar) {
            ThaumicHorizons.blockJar.onBlockPlacedBy(world, x, y, z, (EntityLivingBase) player, stack);
            ThaumicHorizons.blockJar.onPostBlockPlaced(world, x, y, z, metadata);
        }
        return true;
    }

    public boolean getShareTag() {
        return true;
    }
}
