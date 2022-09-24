//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;

public class TileSpike extends TileThaumcraft {
    public byte direction;
    public byte spikeType;

    public TileSpike(final byte metadata, final byte type) {
        this.direction = 1;
        this.spikeType = 0;
        this.direction = metadata;
        this.spikeType = type;
    }

    @Override
    public void writeCustomNBT(final NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.setByte("dir", this.direction);
        nbttagcompound.setByte("type", this.spikeType);
    }

    @Override
    public void readCustomNBT(final NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.direction = nbttagcompound.getByte("dir");
        this.spikeType = nbttagcompound.getByte("type");
    }
}
