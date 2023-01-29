//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import com.kentington.thaumichorizons.common.tiles.TileVisDynamo;

public class ContainerVisDynamo extends Container {

    private EntityPlayer player;
    private TileVisDynamo tile;

    public ContainerVisDynamo(final EntityPlayer p, final TileVisDynamo t) {
        this.player = p;
        this.tile = t;
    }

    public boolean canInteractWith(final EntityPlayer p_75145_1_) {
        return this.tile.isUseableByPlayer(p_75145_1_);
    }

    public boolean enchantItem(final EntityPlayer par1EntityPlayer, final int button) {
        switch (button) {
            case 1: {
                this.tile.provideAer = !this.tile.provideAer;
                this.tile.markDirty();
                return true;
            }
            case 2: {
                this.tile.provideTerra = !this.tile.provideTerra;
                this.tile.markDirty();
                return true;
            }
            case 3: {
                this.tile.provideIgnis = !this.tile.provideIgnis;
                this.tile.markDirty();
                return true;
            }
            case 4: {
                this.tile.provideAqua = !this.tile.provideAqua;
                this.tile.markDirty();
                return true;
            }
            case 5: {
                this.tile.provideOrdo = !this.tile.provideOrdo;
                this.tile.markDirty();
                return true;
            }
            case 6: {
                this.tile.providePerditio = !this.tile.providePerditio;
                this.tile.markDirty();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
