//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockNodeMonitor;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.INode;

public class TileNodeMonitor extends TileThaumcraft {
    public byte direction;
    public boolean activated;
    boolean lastActivated;
    public int rotation;
    public boolean switchy;

    public TileNodeMonitor() {
        this.direction = -1;
        this.rotation = 0;
        this.switchy = false;
        this.activated = false;
        this.lastActivated = false;
        this.direction = (byte) this.blockMetadata;
    }

    public void updateEntity() {
        if (!this.activated) {
            this.switchy = false;
            ++this.rotation;
            if (this.rotation > 360) {
                this.rotation -= 360;
            }
        } else if (this.worldObj.isRemote && Minecraft.getMinecraft().thePlayer.ticksExisted % 15 == 0) {
            this.switchy = !this.switchy;
        }
        if (this.direction == -1) {
            this.direction = (byte) this.getBlockMetadata();
        }
        final ForgeDirection dir = ForgeDirection.getOrientation((int) this.direction);
        if (dir == ForgeDirection.UP
                && this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof INode) {
            this.activated = this.aspectCritical(this.xCoord, this.yCoord - 1, this.zCoord);
        } else if (dir == ForgeDirection.DOWN
                && this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof INode) {
            this.activated = this.aspectCritical(this.xCoord, this.yCoord + 1, this.zCoord);
        } else if (dir == ForgeDirection.NORTH
                && this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof INode) {
            this.activated = this.aspectCritical(this.xCoord, this.yCoord, this.zCoord + 1);
        } else if (dir == ForgeDirection.SOUTH
                && this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof INode) {
            this.activated = this.aspectCritical(this.xCoord, this.yCoord, this.zCoord - 1);
        } else if (dir == ForgeDirection.WEST
                && this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof INode) {
            this.activated = this.aspectCritical(this.xCoord + 1, this.yCoord, this.zCoord);
        } else if (dir == ForgeDirection.EAST
                && this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof INode) {
            this.activated = this.aspectCritical(this.xCoord - 1, this.yCoord, this.zCoord);
        } else {
            ((BlockNodeMonitor) ThaumicHorizons.blockNodeMonitor)
                    .killMe(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
        if (this.activated != this.lastActivated) {
            this.worldObj.notifyBlocksOfNeighborChange(
                    this.xCoord, this.yCoord, this.zCoord, ThaumicHorizons.blockNodeMonitor);
        }
        this.lastActivated = this.activated;
    }

    private boolean aspectCritical(final int x, final int y, final int z) {
        final TileEntity node = this.worldObj.getTileEntity(x, y, z);
        if (node instanceof INode) {
            for (final Aspect asp : ((INode) node).getAspects().getAspects()) {
                if (((INode) node).getAspects().getAmount(asp) <= 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void writeCustomNBT(final NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.setBoolean("active", this.activated);
        nbttagcompound.setBoolean("lastactive", this.lastActivated);
        nbttagcompound.setByte("dir", this.direction);
    }

    @Override
    public void readCustomNBT(final NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.activated = nbttagcompound.getBoolean("active");
        this.lastActivated = nbttagcompound.getBoolean("lastactive");
        this.direction = nbttagcompound.getByte("dir");
    }
}
