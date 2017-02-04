// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.items;

import net.minecraft.util.ResourceLocation;
import thaumcraft.common.config.ConfigBlocks;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraft.entity.player.EntityPlayerMP;
import thaumcraft.api.aspects.Aspect;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.IBlockAccess;
import thaumcraft.common.items.wands.ItemWandCasting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.util.IIcon;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

public class ItemFocusAnimation extends ItemFocusBasic
{
    private static final AspectList cost;
    public static FocusUpgradeType berserk;
    public static FocusUpgradeType detonation;
    public IIcon ornamentIcon;
    
    public ItemFocusAnimation() {
        this.setMaxDamage(0);
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }
    
    @Override
    public IIcon getOrnament(final ItemStack focusstack) {
        return this.ornamentIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:focus_animation");
        this.ornamentIcon = ir.registerIcon("thaumcraft:focus_whatever_orn");
    }
    
    public String getItemStackDisplayName(final ItemStack stack) {
        return StatCollector.translateToLocal("item.focusAnimation.name");
    }
    
    @Override
    public int getFocusColor(final ItemStack focusstack) {
        return 15054592;
    }
    
    @Override
    public AspectList getVisCost(final ItemStack focusstack) {
        return ItemFocusAnimation.cost;
    }
    
    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(final ItemStack focusstack, final int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.extend };
            }
            case 2: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.extend };
            }
            case 3: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.extend, ItemFocusAnimation.berserk };
            }
            case 4: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.extend };
            }
            case 5: {
                return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.extend, ItemFocusAnimation.detonation };
            }
            default: {
                return null;
            }
        }
    }
    
    @Override
    public ItemStack onFocusRightClick(final ItemStack itemstack, final World world, final EntityPlayer player, final MovingObjectPosition mop) {
        final ItemWandCasting wand = (ItemWandCasting)itemstack.getItem();
        if (mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            final int x = mop.blockX;
            final int y = mop.blockY;
            final int z = mop.blockZ;
            final Block blocky = world.getBlock(x, y, z);
            final int md = world.getBlockMetadata(x, y, z);
            if (!blocky.hasTileEntity(md) && !blocky.isAir((IBlockAccess)world, x, y, z) && (blocky.isOpaqueCube() || isWhitelisted(blocky, md)) && blocky.getBlockHardness(world, x, y, z) != -1.0f) {
                WorldSettings.GameType gt = WorldSettings.GameType.SURVIVAL;
                if (player.capabilities.allowEdit) {
                    if (player.capabilities.isCreativeMode) {
                        gt = WorldSettings.GameType.CREATIVE;
                    }
                }
                else {
                    gt = WorldSettings.GameType.ADVENTURE;
                }
                if (!world.isRemote) {
                    final EntityGolemTH golem = new EntityGolemTH(world);
                    golem.loadGolem(x + 0.5, y, z + 0.5, blocky, md, 600 + wand.getFocusExtend(itemstack) * 200, false, this.getUpgradeLevel(wand.getFocusItem(itemstack), ItemFocusAnimation.berserk) > 0, this.getUpgradeLevel(wand.getFocusItem(itemstack), ItemFocusAnimation.detonation) > 0);
                    final AspectList cost = new AspectList().add(Aspect.FIRE, golem.type.visCost).add(Aspect.ORDER, golem.type.visCost).add(Aspect.AIR, golem.type.visCost).add(Aspect.EARTH, golem.type.visCost).add(Aspect.ENTROPY, golem.type.visCost).add(Aspect.WATER, golem.type.visCost);
                    if (!wand.consumeAllVis(itemstack, player, cost, false, false)) {
                        golem.setDead();
                        return itemstack;
                    }
                    final BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent(player.worldObj, gt, (EntityPlayerMP)player, x, y, z);
                    if (event.isCanceled() || !wand.consumeAllVis(itemstack, player, cost, true, false)) {
                        golem.setDead();
                        return itemstack;
                    }
                    world.setBlockToAir(x, y, z);
                    world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                    golem.setHomeArea((int)golem.posX, (int)golem.posY, (int)golem.posZ, 32);
                    golem.setOwner(player.getCommandSenderName());
                    world.spawnEntityInWorld((Entity)golem);
                    world.setEntityState((Entity)golem, (byte)7);
                }
                else {
                    Minecraft.getMinecraft().effectRenderer.addBlockDestroyEffects(x, y, z, blocky, md);
                    player.swingItem();
                }
            }
        }
        return itemstack;
    }
    
    public static boolean isWhitelisted(final Block blocky, final int md) {
        return blocky == Blocks.cake || blocky == Blocks.cactus || blocky == Blocks.glass || blocky == Blocks.packed_ice || blocky == Blocks.ice || blocky == Blocks.web || (blocky == ConfigBlocks.blockCosmeticOpaque && md < 2) || (blocky == ConfigBlocks.blockWoodenDevice && md == 6) || md == 7;
    }
    
    static {
        cost = new AspectList().add(Aspect.FIRE, 1000).add(Aspect.AIR, 1000).add(Aspect.ORDER, 1000).add(Aspect.WATER, 1000).add(Aspect.EARTH, 1000).add(Aspect.ENTROPY, 1000);
        ItemFocusAnimation.berserk = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/berserk.png"), "focus.upgrade.berserk.name", "focus.upgrade.berserk.text", new AspectList().add(Aspect.WEAPON, 8));
        ItemFocusAnimation.detonation = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/detonation.png"), "focus.upgrade.detonation.name", "focus.upgrade.detonation.text", new AspectList().add(Aspect.WEAPON, 8));
    }
}
