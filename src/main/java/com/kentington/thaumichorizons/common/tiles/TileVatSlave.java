//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.tiles;

import net.minecraft.entity.player.EntityPlayer;

import com.kentington.thaumichorizons.common.ThaumicHorizons;

import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;

public class TileVatSlave extends TileThaumcraft implements IAspectContainer {

    boolean bossFound;
    int bossX;
    int bossY;
    int bossZ;
    int renderMe;

    public boolean activate(final EntityPlayer player) {
        final TileVat boss = this.getBoss(-1);
        if (boss == null) {
            return false;
        }
        if (this.blockMetadata != 0) {
            return boss.activate(player, false);
        }
        return boss.activate(player, true);
    }

    public TileVat getBoss(final int mdOverride) {
        if (!this.bossFound) {
            int md = mdOverride;
            if (md == -1) {
                md = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
            }
            if (md == 0) {
                this.bossX = this.xCoord;
                this.bossZ = this.zCoord;
                if (this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileVat) {
                    this.bossY = this.yCoord + 1;
                    this.bossFound = true;
                } else if (this.worldObj.getTileEntity(this.xCoord, this.yCoord + 2, this.zCoord) instanceof TileVat) {
                    this.bossY = this.yCoord + 2;
                    this.bossFound = true;
                }
            } else if (md == 10) {
                for (int x = -1; x < 2; ++x) {
                    for (int z = -1; z < 2; ++z) {
                        if (this.worldObj.getBlock(this.xCoord + x, this.yCoord, this.zCoord + z)
                                == ThaumicHorizons.blockVatInterior
                                && this.worldObj.getBlockMetadata(this.xCoord + x, this.yCoord, this.zCoord + z) == 0
                                && this.worldObj.getTileEntity(
                                        this.xCoord + x,
                                        this.yCoord,
                                        this.zCoord + z) instanceof TileVatSlave) {
                            final TileVat boss = ((TileVatSlave) this.worldObj
                                    .getTileEntity(this.xCoord + x, this.yCoord, this.zCoord + z)).getBoss(-1);
                            if (boss != null) {
                                this.bossX = boss.xCoord;
                                this.bossY = boss.yCoord;
                                this.bossZ = boss.zCoord;
                                this.bossFound = true;
                            }
                            return boss;
                        }
                    }
                }
            } else if (md == 4) {
                if (this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == ThaumicHorizons.blockVat
                        && this.worldObj
                                .getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileVatSlave) {
                    final TileVat boss2 = ((TileVatSlave) this.worldObj
                            .getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord)).getBoss(-1);
                    if (boss2 != null) {
                        this.bossX = boss2.xCoord;
                        this.bossY = boss2.yCoord;
                        this.bossZ = boss2.zCoord;
                        this.bossFound = true;
                    }
                    return boss2;
                }
            } else if (md == 5) {
                if (this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == ThaumicHorizons.blockVat
                        && this.worldObj.getBlockMetadata(this.xCoord, this.yCoord + 1, this.zCoord) == 10
                        && this.worldObj
                                .getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof TileVatSlave) {
                    final TileVat boss2 = ((TileVatSlave) this.worldObj
                            .getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord)).getBoss(-1);
                    if (boss2 != null) {
                        this.bossX = boss2.xCoord;
                        this.bossY = boss2.yCoord;
                        this.bossZ = boss2.zCoord;
                        this.bossFound = true;
                    }
                    return boss2;
                }
                if (this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == ThaumicHorizons.blockVat
                        && this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord) == 10
                        && this.worldObj
                                .getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof TileVatSlave) {
                    final TileVat boss2 = ((TileVatSlave) this.worldObj
                            .getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord)).getBoss(-1);
                    if (boss2 != null) {
                        this.bossX = boss2.xCoord;
                        this.bossY = boss2.yCoord;
                        this.bossZ = boss2.zCoord;
                        this.bossFound = true;
                    }
                    return boss2;
                }
            } else if (md == 6) {
                this.bossX = this.xCoord;
                this.bossZ = this.zCoord;
                if (this.worldObj.getTileEntity(this.xCoord, this.yCoord + 3, this.zCoord) instanceof TileVat) {
                    this.bossY = this.yCoord + 3;
                    this.bossFound = true;
                }
            }
        }
        if (this.worldObj.getTileEntity(this.bossX, this.bossY, this.bossZ) instanceof TileVat) {
            return (TileVat) this.worldObj.getTileEntity(this.bossX, this.bossY, this.bossZ);
        }
        return null;
    }

    public void killMyBoss(final int mdOverride) {
        final TileVat boss = this.getBoss(mdOverride);
        if (boss != null) {
            boss.killMe();
        }
    }

    @Override
    public AspectList getAspects() {
        final TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getAspects();
        }
        return null;
    }

    @Override
    public void setAspects(final AspectList aspects) {}

    @Override
    public boolean doesContainerAccept(final Aspect tag) {
        return false;
    }

    @Override
    public int addToContainer(final Aspect tag, final int amount) {
        return 0;
    }

    @Override
    public boolean takeFromContainer(final Aspect tag, final int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(final AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(final Aspect tag, final int amount) {
        return false;
    }

    @Override
    public boolean doesContainerContain(final AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(final Aspect tag) {
        return 0;
    }
}
