// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE;
    
    public static void init() {
        int idx = 0;
        PacketHandler.INSTANCE.registerMessage(PacketLensChangeToServer.class, PacketLensChangeToServer.class, idx++, Side.SERVER);
        PacketHandler.INSTANCE.registerMessage(PacketFXEssentiaBubble.class, PacketFXEssentiaBubble.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketInfusionFX.class, PacketInfusionFX.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketFXInfusionDone.class, PacketFXInfusionDone.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketFXDeadCreature.class, PacketFXDeadCreature.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketFXContainment.class, PacketFXContainment.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketNoMoreItems.class, PacketNoMoreItems.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketFXBlocksplosion.class, PacketFXBlocksplosion.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketRemoveNightvision.class, PacketRemoveNightvision.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketFingersToServer.class, PacketFingersToServer.class, idx++, Side.SERVER);
        PacketHandler.INSTANCE.registerMessage(PacketPlayerInfusionSync.class, PacketPlayerInfusionSync.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketMountNightmare.class, PacketMountNightmare.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage(PacketToggleClimbToServer.class, PacketToggleClimbToServer.class, idx++, Side.SERVER);
        PacketHandler.INSTANCE.registerMessage(PacketToggleInvisibleToServer.class, PacketToggleInvisibleToServer.class, idx++, Side.SERVER);
        PacketHandler.INSTANCE.registerMessage(PacketRainState.class,PacketRainState.class,idx++,Side.CLIENT);
    }
    
    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("ThaumicHorizons".toLowerCase());
    }
}
