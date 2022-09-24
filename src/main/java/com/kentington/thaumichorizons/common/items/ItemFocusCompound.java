//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.BlockUtils;

public class ItemFocusCompound extends ItemFocusBasic {
    public static FocusUpgradeType fission;
    private static final AspectList cost;

    public ItemFocusCompound() {
        this.setCreativeTab(ThaumicHorizons.tabTH);
    }

    public String getItemStackDisplayName(final ItemStack stack) {
        return StatCollector.translateToLocal("item.focusCompound.name");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister ir) {
        this.icon = ir.registerIcon("thaumichorizons:focus_containment");
    }

    @Override
    public int getFocusColor(final ItemStack focusstack) {
        return 15054592;
    }

    @Override
    public String getSortingHelper(final ItemStack itemstack) {
        return "CR" + super.getSortingHelper(itemstack);
    }

    @Override
    public AspectList getVisCost(final ItemStack focusstack) {
        return ItemFocusCompound.cost.copy();
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(final ItemStack focusstack, final int rank) {
        return new FocusUpgradeType[] {ItemFocusCompound.fission};
    }

    @Override
    public ItemStack onFocusRightClick(
            final ItemStack itemstack,
            final World world,
            final EntityPlayer p,
            final MovingObjectPosition movingobjectposition) {
        final ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
        p.setItemInUse(itemstack, Integer.MAX_VALUE);
        return itemstack;
    }

    public Aspect chooseRandomFilteredFromSource(
            final AspectList filter, final boolean preserve, final AspectList nodeAspects, final World world) {
        final int min = preserve ? 1 : 0;
        final ArrayList<Aspect> validaspects = new ArrayList<Aspect>();
        for (final Aspect prim : nodeAspects.getAspects()) {
            if (filter.getAmount(prim) > 0 && nodeAspects.getAmount(prim) > min) {
                validaspects.add(prim);
            }
        }
        if (validaspects.size() == 0) {
            return null;
        }
        final Aspect asp = validaspects.get(world.rand.nextInt(validaspects.size()));
        if (asp != null && nodeAspects.getAmount(asp) > min) {
            return asp;
        }
        return null;
    }

    @Override
    public void onUsingFocusTick(final ItemStack wandstack, final EntityPlayer player, final int count) {
        boolean mfu = false;
        final ItemWandCasting wand = (ItemWandCasting) wandstack.getItem();
        final MovingObjectPosition movingobjectposition =
                BlockUtils.getTargetBlock(player.worldObj, (Entity) player, true);
        int i = 0;
        int j = 0;
        int k = 0;
        AspectList nodeAsp = new AspectList();
        INode node = null;
        int color = 0;
        if (movingobjectposition == null
                || movingobjectposition.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {
            player.stopUsingItem();
        } else {
            i = movingobjectposition.blockX;
            j = movingobjectposition.blockY;
            k = movingobjectposition.blockZ;
            if (!(player.worldObj.getTileEntity(i, j, k) instanceof INode)) {
                player.stopUsingItem();
                return;
            }
            node = (INode) player.worldObj.getTileEntity(i, j, k);
            nodeAsp = node.getAspects();
        }
        if (count % 5 == 0) {
            int tap = 1;
            if (ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODETAPPER1")) {
                ++tap;
            }
            if (ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODETAPPER2")) {
                ++tap;
            }
            final boolean preserve = !player.isSneaking()
                    && ResearchManager.isResearchComplete(player.getCommandSenderName(), "NODEPRESERVE")
                    && !wand.getRod(wandstack).getTag().equals("wood")
                    && !wand.getCap(wandstack).getTag().equals("iron");
            boolean success = false;
            Aspect aspect = null;
            if ((aspect = this.chooseRandomFilteredFromSource(
                            wand.getAspectsWithRoom(wandstack), preserve, nodeAsp, player.worldObj))
                    != null) {
                final int amt = nodeAsp.getAmount(aspect);
                if (tap > amt) {
                    tap = amt;
                }
                if (preserve && tap == amt) {
                    --tap;
                }
                if (tap > 0) {
                    final int rem = wand.addVis(wandstack, aspect, tap, !player.worldObj.isRemote);
                    if (rem < tap) {
                        color = aspect.getColor();
                        if (!player.worldObj.isRemote) {
                            node.takeFromContainer(aspect, tap - rem);
                            mfu = true;
                        }
                        success = true;
                    }
                }
            }
            if (success) {
                final Color col = new Color(color);
                Thaumcraft.proxy.beamPower(
                        player.worldObj,
                        i + 0.5,
                        j + 0.5,
                        k + 0.5,
                        player.posX,
                        player.posY + player.eyeHeight,
                        player.posZ,
                        col.getRed() / 255.0f,
                        col.getGreen() / 255.0f,
                        col.getBlue() / 255.0f,
                        true,
                        (Object) node);
            }
            if (mfu) {
                player.worldObj.markBlockForUpdate(i, j, k);
                ((TileEntity) node).markDirty();
            }
        }
    }

    static {
        ItemFocusCompound.fission = new FocusUpgradeType(
                FocusUpgradeType.types.length,
                new ResourceLocation("thaumichorizons", "textures/foci/fission.png"),
                "focus.upgrade.fission.name",
                "focus.upgrade.fission.text",
                new AspectList().add(Aspect.EXCHANGE, 8));
        cost = new AspectList()
                .add(Aspect.FIRE, 0)
                .add(Aspect.WATER, 0)
                .add(Aspect.AIR, 0)
                .add(Aspect.EARTH, 0)
                .add(Aspect.ORDER, 0)
                .add(Aspect.ENTROPY, 0);
    }
}
