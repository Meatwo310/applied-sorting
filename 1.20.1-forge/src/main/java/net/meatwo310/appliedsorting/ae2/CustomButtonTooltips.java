package net.meatwo310.appliedsorting.ae2;

import appeng.core.localization.LocalizationEnum;

public enum CustomButtonTooltips implements LocalizationEnum {
    DefaultSort("Default"),
    DefaultSortHint("Let AE2 handle sorting"),
    InternalId("Internal ID"),
    InternalIdHint("Sort by the order registered to the game"),
    ResourceLocation("Resource Location"),
    ResourceLocationHint("Sort by identifier")
    ;

    private final String englishText;

    CustomButtonTooltips(String englishText) {
        this.englishText = englishText;
    }

    @Override
    public String getTranslationKey() {
        return "gui.tooltips.appliedsorting." + name();
    }

    @Override
    public String getEnglishText() {
        return englishText;
    }
}
