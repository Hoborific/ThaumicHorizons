//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class EventHandlerWorld {
    @SubscribeEvent
    public void worldLoad(final WorldEvent.Load event) {
        if (!event.world.isRemote && event.world.provider.dimensionId == 0) {
            PocketPlaneData.loadPocketPlanes(event.world);
        }
    }

    @SubscribeEvent
    public void worldSave(final WorldEvent.Save event) {
        if (!event.world.isRemote && event.world.provider.dimensionId == 0) {
            PocketPlaneData.savePocketPlanes(event.world);
        }
    }
}
