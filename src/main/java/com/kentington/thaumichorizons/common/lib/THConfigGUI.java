//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class THConfigGUI extends GuiConfig {
    public THConfigGUI(final GuiScreen parent) {
        super(
                parent,
                new ConfigElement(ThaumicHorizons.config.getCategory("general")).getChildElements(),
                "ThaumicHorizons",
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ThaumicHorizons.config.toString()));
    }
}
