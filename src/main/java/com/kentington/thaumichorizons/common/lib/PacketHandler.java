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
        PacketHandler.INSTANCE.registerMessage((Class)PacketLensChangeToServer.class, (Class)PacketLensChangeToServer.class, idx++, Side.SERVER);
        PacketHandler.INSTANCE.registerMessage((Class)PacketFXEssentiaBubble.class, (Class)PacketFXEssentiaBubble.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketInfusionFX.class, (Class)PacketInfusionFX.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketFXInfusionDone.class, (Class)PacketFXInfusionDone.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketFXDeadCreature.class, (Class)PacketFXDeadCreature.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketFXContainment.class, (Class)PacketFXContainment.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketNoMoreItems.class, (Class)PacketNoMoreItems.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketFXBlocksplosion.class, (Class)PacketFXBlocksplosion.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketRemoveNightvision.class, (Class)PacketRemoveNightvision.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketFingersToServer.class, (Class)PacketFingersToServer.class, idx++, Side.SERVER);
        PacketHandler.INSTANCE.registerMessage((Class)PacketPlayerInfusionSync.class, (Class)PacketPlayerInfusionSync.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketMountNightmare.class, (Class)PacketMountNightmare.class, idx++, Side.CLIENT);
        PacketHandler.INSTANCE.registerMessage((Class)PacketToggleClimbToServer.class, (Class)PacketToggleClimbToServer.class, idx++, Side.SERVER);
        PacketHandler.INSTANCE.registerMessage((Class)PacketToggleInvisibleToServer.class, (Class)PacketToggleInvisibleToServer.class, idx++, Side.SERVER);
    }
    
    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("ThaumicHorizons".toLowerCase());
    }
}
