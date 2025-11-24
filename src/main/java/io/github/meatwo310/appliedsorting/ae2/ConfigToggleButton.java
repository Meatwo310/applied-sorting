/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2014, AlgorithmX2, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

/*
 * MODFICATIONS:
 * - Meatwo310: Changed package/class name and texture location.
 * - Meatwo310: Heavily modified to use ForgeConfigSpec.EnumValue and support dynamic icons/tooltips.
 * - Mochi_753: Change `ForgeConfigSpec` to `ModConfigSpec`.
 * See NOTICE file for license details.
 */

package io.github.meatwo310.appliedsorting.ae2;

import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.Icon;
import appeng.client.gui.style.Blitter;
import appeng.core.localization.ButtonToolTips;
import appeng.core.localization.LocalizationEnum;
import appeng.util.EnumCycler;
import io.github.meatwo310.appliedsorting.config.ClientConfig;
import io.github.meatwo310.appliedsorting.config.SortBy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.ModConfigSpec;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class ConfigToggleButton<T extends Enum<T>> extends CustomIconButton {
    private static Map<EnumPair<?>, ButtonAppearance> appearances;
    private final ModConfigSpec.EnumValue<T> configSpec;
    private final IHandler<ConfigToggleButton<T>> onPress;
    private final EnumSet<T> validValues;
    private T currentValue;

    public ConfigToggleButton(ModConfigSpec.EnumValue<T> configSpec, IHandler<ConfigToggleButton<T>> onPress) {
        this(configSpec, configSpec.get(), onPress);
    }

    public ConfigToggleButton(ModConfigSpec.EnumValue<T> configSpec, T val, IHandler<ConfigToggleButton<T>> onPress) {
        this(configSpec, val, t -> true, onPress);
    }

    public ConfigToggleButton(ModConfigSpec.EnumValue<T> configSpec, T val, Predicate<T> isValidValue, IHandler<ConfigToggleButton<T>> onPress) {
        super(ConfigToggleButton::onPress);
        this.onPress = onPress;

        EnumSet<T> validValues = EnumSet.allOf(val.getDeclaringClass());
        validValues.removeIf(isValidValue.negate());
        this.validValues = validValues;

        this.configSpec = configSpec;
        this.currentValue = val;

        if (appearances == null) {
            appearances = new HashMap<>();

            registerApp(
                    CustomIcon.SORT_BY_ID.getBlitter(),
                    ClientConfig.ALTERNATIVE_SORT,
                    SortBy.INTERNAL_ID,
                    ButtonToolTips.SortBy,
                    CustomButtonTooltips.InternalId
            );
            registerApp(
                    CustomIcon.SORT_BY_RESOURCE_LOCATION.getBlitter(),
                    ClientConfig.ALTERNATIVE_SORT,
                    SortBy.RESOURCE_LOCATION,
                    ButtonToolTips.SortBy,
                    CustomButtonTooltips.ResourceLocation
            );
            registerApp(
                    Icon.SORT_BY_NAME.getBlitter(),
                    ClientConfig.ALTERNATIVE_SORT,
                    SortBy.NAME,
                    ButtonToolTips.SortBy,
                    ButtonToolTips.ItemName
            );
            registerApp(
                    Icon.SORT_BY_AMOUNT.getBlitter(),
                    ClientConfig.ALTERNATIVE_SORT,
                    SortBy.AMOUNT,
                    ButtonToolTips.SortBy,
                    ButtonToolTips.NumberOfItems
            );
            registerApp(
                    Icon.SORT_BY_MOD.getBlitter(),
                    ClientConfig.ALTERNATIVE_SORT,
                    SortBy.MOD,
                    ButtonToolTips.SortBy,
                    ButtonToolTips.Mod
            );
        }
    }

    private static void onPress(Button button) {
        if (button instanceof ConfigToggleButton) {
            ((ConfigToggleButton<?>) button).triggerPress();
        }
    }

    private static <T extends Enum<T>> void registerApp(Blitter blitter, ModConfigSpec.EnumValue<T> setting, T val, LocalizationEnum title, Component... tooltipLines) {
        var lines = new ArrayList<Component>();
        lines.add(title.text());
        Collections.addAll(lines, tooltipLines);

        appearances.put(new EnumPair<>(setting, val), new ButtonAppearance(blitter, null, lines));
    }

    private static <T extends Enum<T>> void registerApp(ItemLike item, ModConfigSpec.EnumValue<T> setting, T val, LocalizationEnum title, Component... tooltipLines) {
        var lines = new ArrayList<Component>();
        lines.add(title.text());
        Collections.addAll(lines, tooltipLines);

        appearances.put(new EnumPair<>(setting, val), new ButtonAppearance(null, item.asItem(), lines));
    }

    private static <T extends Enum<T>> void registerApp(Blitter blitter, ModConfigSpec.EnumValue<T> setting, T val, LocalizationEnum title, LocalizationEnum hint) {
        registerApp(blitter, setting, val, title, hint.text());
    }

    private void triggerPress() {
        boolean backwards = false;
        Screen currentScreen = Minecraft.getInstance().screen;
        if (currentScreen instanceof AEBaseScreen) {
            backwards = ((AEBaseScreen<?>) currentScreen).isHandlingRightClick();
        }
        onPress.handle(this, backwards);
    }

    private ButtonAppearance getAppearance() {
        if (this.configSpec != null && this.currentValue != null) {
            return appearances.get(new EnumPair<>(this.configSpec, this.currentValue));
        }
        return null;
    }

    @Override
    protected Blitter getBlitter() {
        ButtonAppearance appearance = getAppearance();
        if (appearance != null && appearance.blitter != null) {
            return appearance.blitter();
        }
        return Icon.TOOLBAR_BUTTON_BACKGROUND.getBlitter();
    }

    @Override
    protected @org.jetbrains.annotations.Nullable Item getItemOverlay() {
        ButtonAppearance appearance = getAppearance();
        if (appearance != null && appearance.item != null) {
            return appearance.item();
        }
        return null;
    }

    public ModConfigSpec.EnumValue<T> getSetting() {
        return this.configSpec;
    }

    public T getCurrentValue() {
        return this.currentValue;
    }

    public void set(T e) {
        if (this.currentValue != e) this.currentValue = e;
    }

    public T getNextValue(boolean backwards) {
        return EnumCycler.rotateEnum(currentValue, backwards, validValues);
    }

    /**
     * Util method to toggle the button and update the config value.
     * @param backwards If true, cycle backwards.
     * @author Meatwo310
     */
    public void toggleConfig(boolean backwards) {
        T next = getNextValue(backwards);
        this.configSpec.set(next);
        this.set(next);
    }

    @Override
    public List<Component> getTooltipMessage() {
        if (this.configSpec == null || this.currentValue == null) {
            return Collections.emptyList();
        }

        ButtonAppearance buttonAppearance = appearances.get(new EnumPair<>(this.configSpec, this.currentValue));
        if (buttonAppearance == null) {
            return Collections.singletonList(ButtonToolTips.NoSuchMessage.text());
        }

        return buttonAppearance.tooltipLines;
    }

    public interface IHandler<T extends ConfigToggleButton<?>> {
        void handle(T button, boolean backwards);
    }

    private static final class EnumPair<T extends Enum<T>> {
        final ModConfigSpec.EnumValue<T> setting;
        final T value;

        public EnumPair(ModConfigSpec.EnumValue<T> setting, T value) {
            this.setting = setting;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return this.setting.hashCode() ^ this.value.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (this.getClass() != obj.getClass()) return false;
            final EnumPair<?> other = (EnumPair<?>) obj;
            return other.setting == this.setting && other.value == this.value;
        }
    }

    private record ButtonAppearance(@Nullable Blitter blitter, @Nullable Item item, List<Component> tooltipLines) {
    }
}
