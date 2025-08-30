package io.github.meatwo310.appliedsorting.datagen;

import appeng.core.localization.LocalizationEnum;
import io.github.meatwo310.appliedsorting.AppliedSorting;
import io.github.meatwo310.appliedsorting.ae2.CustomButtonTooltips;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ASLangGen {
    protected ASLangGen() {}

    public static void register(DataGenerator generator, PackOutput output) {
        generator.addProvider(true, new EnUs(output));
        generator.addProvider(true, new JaJp(output));
    }

    public static class EnUs extends CustomLanguageProvider {
        protected EnUs(PackOutput output) {
            super(output, "en_us");
        }

        @Override
        protected void addTranslations() {
            addEnum(CustomButtonTooltips.class);
        }
    }

    public static class JaJp extends CustomLanguageProvider {
        protected JaJp(PackOutput output) {
            super(output, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            for (var value : CustomButtonTooltips.class.getEnumConstants()) {
                this.add(value.getTranslationKey(), switch (value) {
                    case InternalId -> "数値ID";
                    case ResourceLocation -> "リソース名";
                });
            }
        }
    }

    private static abstract class CustomLanguageProvider extends LanguageProvider {
        protected CustomLanguageProvider(PackOutput output, String locale) {
            this(output, AppliedSorting.MODID, locale);
        }

        protected CustomLanguageProvider(PackOutput output, String modid, String locale) {
            super(output, modid, locale);
        }

        protected <T extends Enum<T> & LocalizationEnum> void addEnum(Class<T> localizedEnum) {
            for (var value : localizedEnum.getEnumConstants()) {
                this.add(value.getTranslationKey(), value.getEnglishText());
            }
        }
    }
}
