// 
// Decompiled by Procyon v0.5.30
// 

package com.kentington.thaumichorizons.common.lib;

import net.minecraftforge.common.config.ConfigElement;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.client.config.GuiConfig;

public class THConfigGUI extends GuiConfig
{
    public THConfigGUI(final GuiScreen parent) {
        super(parent, new ConfigElement(ThaumicHorizons.config.getCategory("general")).getChildElements(), "ThaumicHorizons", false, false, GuiConfig.getAbridgedConfigPath(ThaumicHorizons.config.toString()));
    }
}
