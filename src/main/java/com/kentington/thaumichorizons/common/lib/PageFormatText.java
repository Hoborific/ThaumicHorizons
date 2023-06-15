package com.kentington.thaumichorizons.common.lib;

import net.minecraft.client.resources.I18n;

import thaumcraft.api.research.ResearchPage;

public class PageFormatText extends ResearchPage {

    private final Object[] args;

    public PageFormatText(String text, Object... args) {
        super(text);
        this.args = args;
    }

    public String getTranslatedText() {
        return I18n.format(super.getTranslatedText(), args);
    }
}
