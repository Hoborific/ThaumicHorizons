//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import com.kentington.thaumichorizons.common.tiles.TileSoulforge;

public class ContainerSoulforge extends Container {

    private EntityPlayer player;
    private TileSoulforge tile;

    public ContainerSoulforge(final EntityPlayer p, final TileSoulforge t) {
        this.player = p;
        this.tile = t;
    }

    public boolean canInteractWith(final EntityPlayer player) {
        return this.tile.isUseableByPlayer(player);
    }
}
