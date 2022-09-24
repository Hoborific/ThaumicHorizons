//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.tiles;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.lib.research.ResearchManager;

public class TileEssentiaDynamo extends TileVisNode implements IAspectContainer, IEssentiaTransport {
    AspectList primalsActuallyProvided;
    AspectList primalsProvided;
    public Aspect essentia;
    public int ticksProvided;
    public float rise;
    public float rotation;
    public float rotation2;

    public TileEssentiaDynamo() {
        this.primalsActuallyProvided = new AspectList();
        this.primalsProvided = new AspectList();
        this.essentia = null;
        this.ticksProvided = 0;
        this.rise = 0.0f;
        this.rotation = 0.0f;
        this.rotation2 = 0.0f;
    }

    @Override
    public AspectList getAspects() {
        if (this.primalsProvided.getAspects().length > 0 && this.primalsProvided.getAspects()[0] != null) {
            return this.primalsProvided;
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

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public boolean isSource() {
        return this.ticksProvided > 0;
    }

    @Override
    public int consumeVis(final Aspect aspect, final int amount) {
        final int drain = Math.min(this.primalsActuallyProvided.getAmount(aspect), amount);
        if (drain > 0) {
            this.primalsActuallyProvided.reduce(aspect, drain);
        }
        return drain;
    }

    @Override
    public boolean isConnectable(final ForgeDirection face) {
        return face == ForgeDirection.DOWN;
    }

    @Override
    public boolean canInputFrom(final ForgeDirection face) {
        return face == ForgeDirection.DOWN;
    }

    @Override
    public boolean canOutputTo(final ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(final Aspect aspect, final int amount) {}

    @Override
    public Aspect getSuctionType(final ForgeDirection face) {
        return null;
    }

    @Override
    public int getSuctionAmount(final ForgeDirection face) {
        if (this.ticksProvided <= 20) {
            return 128;
        }
        return 0;
    }

    @Override
    public int takeEssentia(final Aspect aspect, final int amount, final ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(final Aspect aspect, final int amount, final ForgeDirection face) {
        this.ticksProvided += 21;
        this.essentia = aspect;
        if (VisNetHandler.sources.get(this.worldObj.provider.dimensionId) == null) {
            VisNetHandler.sources.put(
                    this.worldObj.provider.dimensionId, new HashMap<WorldCoordinates, WeakReference<TileVisNode>>());
        }
        if (VisNetHandler.sources
                        .get(this.worldObj.provider.dimensionId)
                        .get(new WorldCoordinates(
                                this.xCoord, this.yCoord, this.zCoord, this.worldObj.provider.dimensionId))
                == null) {
            VisNetHandler.sources
                    .get(this.worldObj.provider.dimensionId)
                    .put(
                            new WorldCoordinates(
                                    this.xCoord, this.yCoord, this.zCoord, this.worldObj.provider.dimensionId),
                            new WeakReference<TileVisNode>(this));
        } else if (VisNetHandler.sources
                        .get(this.worldObj.provider.dimensionId)
                        .get(new WorldCoordinates(
                                this.xCoord, this.yCoord, this.zCoord, this.worldObj.provider.dimensionId))
                        .get()
                == null) {
            VisNetHandler.sources
                    .get(this.worldObj.provider.dimensionId)
                    .remove(new WorldCoordinates(
                            this.xCoord, this.yCoord, this.zCoord, this.worldObj.provider.dimensionId));
            VisNetHandler.sources
                    .get(this.worldObj.provider.dimensionId)
                    .put(
                            new WorldCoordinates(
                                    this.xCoord, this.yCoord, this.zCoord, this.worldObj.provider.dimensionId),
                            new WeakReference<TileVisNode>(this));
        }
        this.markDirty();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        return 1;
    }

    @Override
    public Aspect getEssentiaType(final ForgeDirection face) {
        return this.essentia;
    }

    @Override
    public int getEssentiaAmount(final ForgeDirection face) {
        return 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (this.ticksProvided >= 0) {
            if (this.rise < 0.3f) {
                this.rise += 0.02f;
            } else {
                this.rotation2 += 2.0f;
                if (this.rotation2 >= 360.0f) {
                    this.rotation2 -= 360.0f;
                }
            }
            this.rotation += 2.0f;
            if (this.rotation >= 360.0f) {
                this.rotation -= 360.0f;
            }
        } else if (this.ticksProvided < 0 && (this.rise > 0.0f || this.rotation2 != 0.0f)) {
            if (this.rotation2 > 0.0f) {
                this.rotation2 -= 8.0f;
                if (this.rotation2 < 0.0f) {
                    this.rotation2 = 0.0f;
                }
            } else if (this.rise > 0.0f) {
                this.rise -= 0.02f;
            }
        }
        if (this.ticksProvided > 0) {
            this.primalsProvided = ResearchManager.reduceToPrimals(new AspectList().add(this.essentia, 1));
            final int numEach = 12 / this.primalsProvided.size();
            for (final Aspect asp : this.primalsProvided.getAspects()) {
                final int num = this.primalsProvided.getAmount(asp);
                if (num > numEach) {
                    this.primalsProvided.reduce(asp, num - numEach);
                } else if (num < numEach) {
                    this.primalsProvided.add(asp, numEach - num);
                }
            }
            this.primalsActuallyProvided = this.primalsProvided.copy();
            --this.ticksProvided;
            if (!this.worldObj.isRemote) {
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                this.markDirty();
            }
        } else if (this.ticksProvided == 0) {
            if (!this.worldObj.isRemote
                    && !this.drawEssentia()
                    && VisNetHandler.sources.get(this.worldObj.provider.dimensionId) != null) {
                --this.ticksProvided;
                this.killMe();
                this.primalsProvided = new AspectList();
                this.primalsActuallyProvided = new AspectList();
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                this.markDirty();
            }
        } else if (!this.worldObj.isRemote && this.ticksProvided < 0) {
            this.drawEssentia();
        }
    }

    public void killMe() {
        if (VisNetHandler.sources != null
                && this.worldObj != null
                && this.worldObj.provider != null
                && VisNetHandler.sources.get(this.worldObj.provider.dimensionId) != null) {
            VisNetHandler.sources
                    .get(this.worldObj.provider.dimensionId)
                    .remove(new WorldCoordinates(
                            this.xCoord, this.yCoord, this.zCoord, this.worldObj.provider.dimensionId));
            this.removeThisNode();
        }
    }

    boolean drawEssentia() {
        final TileEntity te = ThaumcraftApiHelper.getConnectableTile(
                this.worldObj, this.xCoord, this.yCoord, this.zCoord, ForgeDirection.DOWN);
        if (te != null) {
            final IEssentiaTransport ic = (IEssentiaTransport) te;
            if (!ic.canOutputTo(ForgeDirection.UP)) {
                return false;
            }
            Aspect ta = null;
            if (ic.getEssentiaAmount(ForgeDirection.UP) > 0
                    && ic.getSuctionAmount(ForgeDirection.UP) < this.getSuctionAmount(ForgeDirection.DOWN)
                    && this.getSuctionAmount(ForgeDirection.DOWN) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(ForgeDirection.UP);
            }
            if (ta != null && ic.takeEssentia(ta, 1, ForgeDirection.UP) == 1) {
                this.addEssentia(ta, 1, ForgeDirection.DOWN);
                return true;
            }
        }
        return false;
    }

    public void debug() {}

    @Override
    public void writeCustomNBT(final NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        if (this.essentia != null) {
            nbttagcompound.setString("key", this.essentia.getTag());
        }
        nbttagcompound.setInteger("ticks", this.ticksProvided);
        final NBTTagList tlist = new NBTTagList();
        nbttagcompound.setTag("AspectsProvided", (NBTBase) tlist);
        for (final Aspect aspect : this.primalsProvided.getAspects()) {
            if (aspect != null) {
                final NBTTagCompound f = new NBTTagCompound();
                f.setString("key", aspect.getTag());
                f.setInteger("amount", this.primalsProvided.getAmount(aspect));
                tlist.appendTag((NBTBase) f);
            }
        }
    }

    @Override
    public void readCustomNBT(final NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.essentia = Aspect.getAspect(nbttagcompound.getString("key"));
        this.ticksProvided = nbttagcompound.getInteger("ticks");
        final AspectList al = new AspectList();
        final NBTTagList tlist = nbttagcompound.getTagList("AspectsProvided", 10);
        for (int j = 0; j < tlist.tagCount(); ++j) {
            final NBTTagCompound rs = tlist.getCompoundTagAt(j);
            if (rs.hasKey("key")) {
                al.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
            }
        }
        this.primalsProvided = al.copy();
        this.primalsActuallyProvided = this.primalsProvided.copy();
        if (this.ticksProvided < 0) {
            this.killMe();
        }
    }
}
