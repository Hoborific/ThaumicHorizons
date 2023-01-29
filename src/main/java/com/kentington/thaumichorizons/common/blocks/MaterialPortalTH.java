//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialPortalTH extends Material {

    public MaterialPortalTH(final MapColor p_i2118_1_) {
        super(p_i2118_1_);
        this.setImmovableMobility();
    }

    public boolean isSolid() {
        return false;
    }

    public boolean getCanBlockGrass() {
        return false;
    }

    public boolean blocksMovement() {
        return false;
    }
}
