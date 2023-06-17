//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.common.lib;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import cpw.mods.fml.client.IModGuiFactory;

public class THGuiFactory implements IModGuiFactory {

    public void initialize(final Minecraft minecraftInstance) {}

    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return THConfigGUI.class;
    }

    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(
            final IModGuiFactory.RuntimeOptionCategoryElement element) {
        return null;
    }
}
