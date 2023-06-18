//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.network.NetworkRegistry;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileNode;

public class TileRecombinator extends TileThaumcraft {

    public int count;
    public boolean activated;
    public boolean shouldActivate;
    boolean fireOnce;

    public TileRecombinator() {
        this.count = -1;
        this.activated = false;
        this.shouldActivate = false;
        this.fireOnce = false;
    }

    public void updateEntity() {
        super.updateEntity();
        if (!this.fireOnce) {
            this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord)
                    .onNeighborBlockChange(this.worldObj, this.xCoord, this.yCoord, this.zCoord, null);
            this.fireOnce = true;
        }
        if (this.activated) {
            ++this.count;
        } else if (!this.activated && this.count > 0) {
            if (this.count > 50) {
                this.count = 50;
            }
            --this.count;
        }
        if (this.shouldActivate && !this.activated) {
            this.activated = true;
            this.markDirty();
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        } else if (!this.shouldActivate && this.activated) {
            this.activated = false;
            this.markDirty();
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
        if (!this.worldObj.isRemote && this.activated
                && this.count > 50
                && this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof final TileNode tile) {
            final int x = this.worldObj.rand.nextInt(5) - this.worldObj.rand.nextInt(5);
            final int y = this.worldObj.rand.nextInt(5) - this.worldObj.rand.nextInt(5) - 1;
            final int z = this.worldObj.rand.nextInt(5) - this.worldObj.rand.nextInt(5);
            if (x != 0 || y != -1 || z != 0) {
                final TileEntity te = this.worldObj.getTileEntity(this.xCoord + x, this.yCoord + y, this.zCoord + z);
                if (te instanceof final TileNode nd && this.worldObj.getBlock(this.xCoord + x, this.yCoord + y, this.zCoord + z)
                        == ConfigBlocks.blockAiry) {
                    if (te instanceof TileNode && ((TileNode) te).getLock() > 0) {
                        return;
                    }
                    if (nd.getAspects().size() == 0) {
                        return;
                    }
                    this.processCombos(nd, tile, x, y, z);
                }
            }
        }
    }

    void processCombos(final TileNode nd, final TileNode tile, final int x, final int y, final int z) {
        final AspectList possibleCombos = new AspectList();
        for (final Aspect asp : tile.getAspectsBase().getAspects()) {
            if (asp.isPrimal()) {
                for (final Aspect asp2 : nd.getAspectsBase().getAspects()) {
                    if (asp2.isPrimal() && ResearchManager.getCombinationResult(asp, asp2) != null) {
                        possibleCombos.add(ResearchManager.getCombinationResult(asp, asp2), 1);
                    }
                }
            }
        }
        if (possibleCombos.size() > 0 && possibleCombos.getAspects()[0] != null) {
            this.doMerge(possibleCombos, nd, tile, x, y, z);
            return;
        }
        for (final Aspect asp : tile.getAspectsBase().getAspects()) {
            if (asp.isPrimal()) {
                for (final Aspect asp2 : nd.getAspectsBase().getAspects()) {
                    if (ResearchManager.getCombinationResult(asp, asp2) != null) {
                        possibleCombos.add(ResearchManager.getCombinationResult(asp, asp2), 1);
                    }
                }
            }
        }
        if (possibleCombos.size() > 0 && possibleCombos.getAspects()[0] != null) {
            this.doMerge(possibleCombos, nd, tile, x, y, z);
            return;
        }
        for (final Aspect asp : tile.getAspectsBase().getAspects()) {
            for (final Aspect asp2 : nd.getAspectsBase().getAspects()) {
                if (asp2.isPrimal() && ResearchManager.getCombinationResult(asp, asp2) != null) {
                    possibleCombos.add(ResearchManager.getCombinationResult(asp, asp2), 1);
                }
            }
        }
        if (possibleCombos.size() > 0 && possibleCombos.getAspects()[0] != null) {
            this.doMerge(possibleCombos, nd, tile, x, y, z);
            return;
        }
        for (final Aspect asp : tile.getAspectsBase().getAspects()) {
            for (final Aspect asp2 : nd.getAspectsBase().getAspects()) {
                if (ResearchManager.getCombinationResult(asp, asp2) != null) {
                    possibleCombos.add(ResearchManager.getCombinationResult(asp, asp2), 1);
                }
            }
        }
        if (possibleCombos.size() > 0 && possibleCombos.getAspects()[0] != null) {
            this.doMerge(possibleCombos, nd, tile, x, y, z);
        }
    }

    public void doMerge(final AspectList possibleCombos, final TileNode nd, final TileNode tile, final int x,
            final int y, final int z) {
        final int which = this.worldObj.rand.nextInt(possibleCombos.getAspects().length);
        final Aspect toAdd = possibleCombos.getAspects()[which];
        tile.getAspectsBase().add(toAdd, 1);
        tile.getAspects().add(toAdd, 1);
        Aspect aspA;
        Aspect aspB;
        if (tile.getAspectsBase().getAmount(toAdd.getComponents()[0]) > 0) {
            aspA = toAdd.getComponents()[0];
            aspB = toAdd.getComponents()[1];
        } else {
            aspA = toAdd.getComponents()[1];
            aspB = toAdd.getComponents()[0];
        }
        tile.getAspectsBase().remove(aspA, 1);
        tile.getAspects().remove(aspA, 1);
        nd.getAspects().remove(aspB, 1);
        if (this.worldObj.rand.nextInt(3) == 0) {
            nd.setNodeVisBase(aspB, (short) (nd.getNodeVisBase(aspB) - 1));
        }
        this.worldObj.markBlockForUpdate(this.xCoord + x, this.yCoord + y, this.zCoord + z);
        nd.markDirty();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord - 1, this.zCoord);
        tile.markDirty();
        PacketHandler.INSTANCE.sendToAllAround(
                new PacketFXBlockZap(
                        this.xCoord + x + 0.5f,
                        this.yCoord + y + 0.5f,
                        this.zCoord + z + 0.5f,
                        this.xCoord + 0.5f,
                        this.yCoord + 0.5f,
                        this.zCoord + 0.5f),
                new NetworkRegistry.TargetPoint(
                        this.worldObj.provider.dimensionId,
                        this.xCoord,
                        this.yCoord,
                        this.zCoord,
                        32.0));
    }

    @Override
    public void writeCustomNBT(final NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.setBoolean("active", this.activated);
        nbttagcompound.setBoolean("shouldactivate", this.shouldActivate);
        nbttagcompound.setInteger("count", this.count);
    }

    @Override
    public void readCustomNBT(final NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.activated = nbttagcompound.getBoolean("active");
        this.shouldActivate = nbttagcompound.getBoolean("shouldactivate");
        this.count = nbttagcompound.getInteger("count");
    }
}
