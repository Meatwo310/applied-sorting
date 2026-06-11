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
 * - Meatwo310: Heavily modified to support dynamic config-backed icons/tooltips.
 * See NOTICE file for license details.
 */

package net.meatwo310.appliedsorting.ae2;

import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.Icon;
import appeng.client.gui.style.Blitter;
import appeng.core.localization.ButtonToolTips;
import appeng.core.localization.LocalizationEnum;
import appeng.util.EnumCycler;
import net.meatwo310.appliedsorting.config.ClientConfig;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntry;
import net.meatwo310.appliedsorting.config.SortBy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Predicate;

public class ConfigToggleButton<T extends Enum<T>> extends CustomIconButton {
    private static Map<EnumPair<?>, ButtonAppearance> appearances;
    private final ConfigEntry.EnumEntry<T> configValue;
    private final IHandler<ConfigToggleButton<T>> onPress;
    private final EnumSet<T> validValues;
    private T currentValue;

    @FunctionalInterface
    public interface IHandler<T extends ConfigToggleButton<?>> {
        void handle(T button, boolean backwards);
    }

    public ConfigToggleButton(ConfigEntry.EnumEntry<T> configValue, IHandler<ConfigToggleButton<T>> onPress) {
        this(configValue, configValue.get(), onPress);
    }

    public ConfigToggleButton(ConfigEntry.EnumEntry<T> configValue, T val,
                               IHandler<ConfigToggleButton<T>> onPress) {
        this(configValue, val, t -> true, onPress);
    }

    public ConfigToggleButton(ConfigEntry.EnumEntry<T> configValue, T val, Predicate<T> isValidValue,
                               IHandler<ConfigToggleButton<T>> onPress) {
        super(ConfigToggleButton::onPress);
        this.onPress = onPress;

        // Build a list of values (in order) that are valid w.r.t. the given predicate
        EnumSet<T> validValues = EnumSet.allOf(val.getDeclaringClass());
        validValues.removeIf(isValidValue.negate());
        this.validValues = validValues;

        this.configValue = configValue;
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

    private static void onPress(Button btn) {
        if (btn instanceof ConfigToggleButton) {
            ((ConfigToggleButton<?>) btn).triggerPress();
        }
    }

    private void triggerPress() {
        boolean backwards = false;
        // This isn't great, but we don't get any information about right-clicks
        // otherwise
        Screen currentScreen = Minecraft.getInstance().screen;
        if (currentScreen instanceof AEBaseScreen) {
            backwards = ((AEBaseScreen<?>) currentScreen).isHandlingRightClick();
        }
        onPress.handle(this, backwards);
    }

    private static <T extends Enum<T>> void registerApp(Blitter blitter, ConfigEntry.EnumEntry<T> setting, T val,
                                                        LocalizationEnum title, Component... tooltipLines) {
        var lines = new ArrayList<Component>();
        lines.add(title.text());
        Collections.addAll(lines, tooltipLines);

        appearances.put(
                new EnumPair<>(setting, val),
                new ButtonAppearance(blitter, null, lines));
    }

    private static <T extends Enum<T>> void registerApp(ItemLike item, ConfigEntry.EnumEntry<T> setting, T val,
                                                        LocalizationEnum title, Component... tooltipLines) {
        var lines = new ArrayList<Component>();
        lines.add(title.text());
        Collections.addAll(lines, tooltipLines);

        appearances.put(
                new EnumPair<>(setting, val),
                new ButtonAppearance(null, item.asItem(), lines));
    }

    private static <T extends Enum<T>> void registerApp(Blitter blitter, ConfigEntry.EnumEntry<T> setting, T val,
                                                        LocalizationEnum title, LocalizationEnum hint) {
        registerApp(blitter, setting, val, title, hint.text());
    }

    @Nullable
    private ButtonAppearance getApperance() {
        if (this.configValue != null && this.currentValue != null) {
            return appearances.get(new EnumPair<>(this.configValue, this.currentValue));
        }
        return null;
    }

    @Override
    protected Blitter getBlitter() {
        var app = getApperance();
        if (app != null && app.blitter != null) {
            return app.blitter();
        }
        return Icon.TOOLBAR_BUTTON_BACKGROUND.getBlitter();
    }

    @Override
    protected Item getItemOverlay() {
        var app = getApperance();
        if (app != null && app.item != null) {
            return app.item;
        }
        return null;
    }

    public ConfigEntry.EnumEntry<T> getSetting() {
        return this.configValue;
    }

    public T getCurrentValue() {
        return this.currentValue;
    }

    public void set(T e) {
        if (this.currentValue != e) {
            this.currentValue = e;
        }
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
        this.configValue.set(next);
        this.set(next);
    }

    @Override
    public List<Component> getTooltipMessage() {

        if (this.configValue == null || this.currentValue == null) {
            return Collections.emptyList();
        }

        var buttonAppearance = appearances.get(new EnumPair<>(this.configValue, this.currentValue));
        if (buttonAppearance == null) {
            return Collections.singletonList(ButtonToolTips.NoSuchMessage.text());
        }

        return buttonAppearance.tooltipLines;
    }

    private static final class EnumPair<T extends Enum<T>> {

        final ConfigEntry.EnumEntry<T> setting;
        final T value;

        public EnumPair(ConfigEntry.EnumEntry<T> setting, T value) {
            this.setting = setting;
            this.value = value;
        }

        @Override
        public int hashCode() {
            return this.setting.hashCode() ^ this.value.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            final EnumPair<?> other = (EnumPair<?>) obj;
            return other.setting == this.setting && other.value == this.value;
        }
    }

    private record ButtonAppearance(@Nullable Blitter blitter, @Nullable Item item, List<Component> tooltipLines) {
    }
}
