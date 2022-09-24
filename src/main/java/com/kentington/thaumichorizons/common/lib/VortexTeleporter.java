//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class VortexTeleporter extends Teleporter {
    private WorldServer worldServerInstance;
    private int planeNum;

    public VortexTeleporter(final WorldServer p_i1963_1_, final int num) {
        super(p_i1963_1_);
        this.worldServerInstance = p_i1963_1_;
        this.planeNum = num;
    }

    public void placeInPortal(
            final Entity p_77185_1_,
            final double p_77185_2_,
            final double p_77185_4_,
            final double p_77185_6_,
            final float p_77185_8_) {
        if (this.worldServerInstance.provider.dimensionId == ThaumicHorizons.dimensionPocketId) {
            p_77185_1_.setPosition(0.5, 129.0, (double) (256 * this.planeNum + 0.5f));
        } else {
            p_77185_1_.setPosition(
                    PocketPlaneData.positions.get(this.planeNum).xCoord,
                    PocketPlaneData.positions.get(this.planeNum).yCoord,
                    PocketPlaneData.positions.get(this.planeNum).zCoord);
        }
    }
}
