// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;
import net.minecraft.world.Teleporter;

public class GatewayTeleporter extends Teleporter
{
    private WorldServer worldServerInstance;
    private int x;
    private int y;
    private int z;
    private float yaw;
    
    public GatewayTeleporter(final WorldServer p_i1963_1_, final int x, final int y, final int z, final float yaw) {
        super(p_i1963_1_);
        this.worldServerInstance = p_i1963_1_;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
    }
    
    public void placeInPortal(final Entity p_77185_1_, final double p_77185_2_, final double p_77185_4_, final double p_77185_6_, final float p_77185_8_) {
        p_77185_1_.setPositionAndRotation(this.x + 0.5, (double)this.y, this.z + 0.5, this.yaw, 0.0f);
    }
}
