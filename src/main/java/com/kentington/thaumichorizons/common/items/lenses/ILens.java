//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.items.lenses;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public interface ILens {
    String lensName();

    @SideOnly(Side.CLIENT)
    void handleRender(final Minecraft p0, final float p1);

    void handleRemoval(final EntityPlayer p0);
}
