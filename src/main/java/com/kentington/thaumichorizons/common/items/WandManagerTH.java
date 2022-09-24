//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;

public class WandManagerTH implements IWandTriggerManager {
    @Override
    public boolean performTrigger(
            final World world,
            final ItemStack wand,
            final EntityPlayer player,
            final int x,
            final int y,
            final int z,
            final int side,
            final int event) {
        switch (event) {
            case 0: {
                if (ResearchManager.isResearchComplete(player.getCommandSenderName(), "healingVat")) {
                    return this.constructVat(world, wand, player, x, y, z, side);
                }
                break;
            }
        }
        return false;
    }

    boolean constructVat(
            final World world,
            final ItemStack itemstack,
            final EntityPlayer player,
            final int x,
            final int y,
            final int z,
            final int side) {
        final ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
        for (int xx = x - 2; xx <= x; ++xx) {
            for (int yy = y - 3; yy <= y; ++yy) {
                int zz = z - 2;
                while (zz <= z) {
                    if (this.fitVat(world, xx, yy, zz)
                            && wand.consumeAllVisCrafting(
                                    itemstack,
                                    player,
                                    new AspectList()
                                            .add(Aspect.WATER, 50)
                                            .add(Aspect.EARTH, 50)
                                            .add(Aspect.ORDER, 50),
                                    true)) {
                        if (!world.isRemote) {
                            this.replaceVat(world, xx, yy, zz);
                            return true;
                        }
                        return false;
                    } else {
                        ++zz;
                    }
                }
            }
        }
        return false;
    }

    boolean fitVat(final World world, final int x, final int y, final int z) {
        final Block g = Blocks.glass;
        final Block w = Blocks.water;
        final Block p = ConfigBlocks.blockWoodenDevice;
        final Block a = ConfigBlocks.blockMetalDevice;
        final Block[][][] blueprint = {
            {{p, p, p}, {p, a, p}, {p, p, p}},
            {{g, g, g}, {g, w, g}, {g, g, g}},
            {{g, g, g}, {g, w, g}, {g, g, g}},
            {{p, p, p}, {p, a, p}, {p, p, p}}
        };
        for (int yy = 0; yy < 4; ++yy) {
            for (int xx = 0; xx < 3; ++xx) {
                for (int zz = 0; zz < 3; ++zz) {
                    Block block = world.getBlock(x + xx, y - yy + 3, z + zz);
                    if (world.isAirBlock(x + xx, y - yy + 3, z + zz)) {
                        block = Blocks.air;
                    }
                    if (block != blueprint[yy][xx][zz]
                            || (block == p && world.getBlockMetadata(x + xx, y - yy + 3, z + zz) != 6)
                            || (block == a && world.getBlockMetadata(x + xx, y - yy + 3, z + zz) != 9)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void replaceVat(final World world, final int x, final int y, final int z) {
        for (int yy = 0; yy < 4; ++yy) {
            for (int zz = 0; zz < 3; ++zz) {
                for (int xx = 0; xx < 3; ++xx) {
                    int md = 0;
                    if (world.getBlock(x + xx, y + yy, z + zz) == Blocks.water
                            || world.getBlock(x + xx, y + yy, z + zz) == Blocks.flowing_water) {
                        md = 0;
                    } else if (world.getBlock(x + xx, y + yy, z + zz) == Blocks.glass) {
                        md = 10;
                    } else if (world.getBlock(x + xx, y + yy, z + zz) == ConfigBlocks.blockWoodenDevice) {
                        if (yy == 0
                                && ((xx == 1 && zz == 0)
                                        || (xx == 1 && zz == 2)
                                        || (xx == 0 && zz == 1)
                                        || (xx == 2 && zz == 1))) {
                            md = 4;
                        } else {
                            md = 5;
                        }
                    } else if (world.getBlock(x + xx, y + yy, z + zz) == ConfigBlocks.blockMetalDevice) {
                        if (yy == 0) {
                            md = 6;
                        } else {
                            md = 7;
                        }
                    }
                    if (!world.isAirBlock(x + xx, y + yy, z + zz)) {
                        if (md == 4 || md == 5 || md == 6 || md == 7) {
                            world.setBlock(x + xx, y + yy, z + zz, ThaumicHorizons.blockVatSolid, md, 3);
                        } else if (md != 0) {
                            world.setBlock(x + xx, y + yy, z + zz, ThaumicHorizons.blockVat, md, 3);
                        } else {
                            world.setBlock(x + xx, y + yy, z + zz, ThaumicHorizons.blockVatInterior, md, 3);
                        }
                        world.addBlockEvent(x + xx, y + yy, z + zz, ThaumicHorizons.blockVat, 1, 4);
                    }
                }
            }
        }
        for (int yy = 0; yy < 4; ++yy) {
            for (int zz = 0; zz < 3; ++zz) {
                for (int xx = 0; xx < 3; ++xx) {
                    world.markBlockForUpdate(x + xx, y + yy, z + zz);
                }
            }
        }
        world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
    }
}
