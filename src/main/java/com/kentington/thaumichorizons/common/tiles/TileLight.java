// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.ItemFocusIllumination;
import java.awt.Color;
import net.minecraft.tileentity.TileEntity;

public class TileLight extends TileEntity
{
    int md;
    Color col;
    
    public TileLight() {
        this.md = -1;
        this.col = null;
    }
    
    public boolean canUpdate() {
        return true;
    }
    
    public void updateEntity() {
        super.updateEntity();
        if (this.worldObj.isRemote) {
            if (this.md == -1) {
                this.md = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
                this.col = new Color(ItemFocusIllumination.colors[this.md]);
            }
            ThaumicHorizons.proxy.illuminationFX(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.md, this.col);
        }
    }
}
