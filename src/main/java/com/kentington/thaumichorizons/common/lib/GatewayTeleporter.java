//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class GatewayTeleporter extends Teleporter {

    private final WorldServer worldServerInstance;
    private final int x;
    private final int y;
    private final int z;
    private final float yaw;

    public GatewayTeleporter(final WorldServer worldServer, final int x, final int y, final int z, final float yaw) {
        super(worldServer);
        this.worldServerInstance = worldServer;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
    }

    public void placeInPortal(final Entity p_77185_1_, final double p_77185_2_, final double p_77185_4_,
            final double p_77185_6_, final float p_77185_8_) {
        p_77185_1_.setPositionAndRotation(this.x + 0.5, (double) this.y, this.z + 0.5, this.yaw, 0.0f);
    }
}
