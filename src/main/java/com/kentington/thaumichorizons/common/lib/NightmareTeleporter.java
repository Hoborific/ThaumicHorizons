//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class NightmareTeleporter extends Teleporter {

    int dim;
    private WorldServer worldServerInstance;
    private Random random;

    public NightmareTeleporter(final WorldServer p_i1963_1_) {
        super(p_i1963_1_);
        this.worldServerInstance = p_i1963_1_;
        this.random = new Random(p_i1963_1_.getSeed());
    }

    public void placeInPortal(final Entity p_77185_1_, final double p_77185_2_, final double p_77185_4_,
            final double p_77185_6_, final float p_77185_8_) {
        this.moveToHole(p_77185_1_);
    }

    public void moveToHole(final Entity p_85188_1_) {
        final byte b0 = 16;
        double d0 = -1.0;
        final int i = MathHelper.floor_double(p_85188_1_.posX);
        final int j = MathHelper.floor_double(p_85188_1_.posY);
        final int k = MathHelper.floor_double(p_85188_1_.posZ);
        int l = i;
        int i2 = j;
        int j2 = k;
        int k2 = 0;
        final int l2 = this.random.nextInt(4);
        for (int i3 = i - b0; i3 <= i + b0; ++i3) {
            final double d2 = i3 + 0.5 - p_85188_1_.posX;
            for (int k3 = k - b0; k3 <= k + b0; ++k3) {
                final double d3 = k3 + 0.5 - p_85188_1_.posZ;
                Label_0433: for (int i4 = this.worldServerInstance.getActualHeight() - 1; i4 >= 0; --i4) {
                    if (this.worldServerInstance.isAirBlock(i3, i4, k3)) {
                        while (i4 > 0 && this.worldServerInstance.isAirBlock(i3, i4 - 1, k3)) {
                            --i4;
                        }
                        for (int j3 = l2; j3 < l2 + 4; ++j3) {
                            int k4 = j3 % 2;
                            int l3 = 1 - k4;
                            if (j3 % 4 >= 2) {
                                k4 = -k4;
                                l3 = -l3;
                            }
                            for (int i5 = 0; i5 < 3; ++i5) {
                                for (int j4 = 0; j4 < 4; ++j4) {
                                    for (int k5 = -1; k5 < 4; ++k5) {
                                        final int l4 = i3 + (j4 - 1) * k4 + i5 * l3;
                                        final int i6 = i4 + k5;
                                        final int j5 = k3 + (j4 - 1) * l3 - i5 * k4;
                                        if (k5 < 0 && !this.worldServerInstance.getBlock(l4, i6, j5).getMaterial()
                                                .isSolid()) {
                                            continue Label_0433;
                                        }
                                        if (k5 >= 0 && !this.worldServerInstance.isAirBlock(l4, i6, j5)) {
                                            continue Label_0433;
                                        }
                                    }
                                }
                            }
                            final double d4 = i4 + 0.5 - p_85188_1_.posY;
                            final double d5 = d2 * d2 + d4 * d4 + d3 * d3;
                            if (d0 < 0.0 || d5 < d0) {
                                d0 = d5;
                                l = i3;
                                i2 = i4;
                                j2 = k3;
                                k2 = j3 % 4;
                            }
                        }
                    }
                }
            }
        }
        if (d0 < 0.0) {
            for (int i3 = i - b0; i3 <= i + b0; ++i3) {
                final double d2 = i3 + 0.5 - p_85188_1_.posX;
                for (int k3 = k - b0; k3 <= k + b0; ++k3) {
                    final double d3 = k3 + 0.5 - p_85188_1_.posZ;
                    Label_0786: for (int i4 = this.worldServerInstance.getActualHeight() - 1; i4 >= 0; --i4) {
                        if (this.worldServerInstance.isAirBlock(i3, i4, k3)) {
                            while (i4 > 0 && this.worldServerInstance.isAirBlock(i3, i4 - 1, k3)) {
                                --i4;
                            }
                            for (int j3 = l2; j3 < l2 + 2; ++j3) {
                                final int k4 = j3 % 2;
                                final int l3 = 1 - k4;
                                for (int i5 = 0; i5 < 4; ++i5) {
                                    for (int j4 = -1; j4 < 4; ++j4) {
                                        final int k5 = i3 + (i5 - 1) * k4;
                                        final int l4 = i4 + j4;
                                        final int i6 = k3 + (i5 - 1) * l3;
                                        if (j4 < 0 && !this.worldServerInstance.getBlock(k5, l4, i6).getMaterial()
                                                .isSolid()) {
                                            continue Label_0786;
                                        }
                                        if (j4 >= 0 && !this.worldServerInstance.isAirBlock(k5, l4, i6)) {
                                            continue Label_0786;
                                        }
                                    }
                                }
                                final double d4 = i4 + 0.5 - p_85188_1_.posY;
                                final double d5 = d2 * d2 + d4 * d4 + d3 * d3;
                                if (d0 < 0.0 || d5 < d0) {
                                    d0 = d5;
                                    l = i3;
                                    i2 = i4;
                                    j2 = k3;
                                    k2 = j3 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }
        final int k6 = l;
        final int j6 = i2;
        int k3 = j2;
        int l5 = k2 % 2;
        int l6 = 1 - l5;
        if (k2 % 4 >= 2) {
            l5 = -l5;
            l6 = -l6;
        }
        p_85188_1_.setPosition((double) k6, (double) j6, (double) k3);
    }
}
