package net.meatwo310.appliedsorting.client.ae2;

import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.Icon;
import appeng.client.gui.style.Blitter;
import appeng.client.gui.widgets.ITooltip;
import appeng.core.localization.ButtonToolTips;
import com.mojang.blaze3d.systems.RenderSystem;
import net.meatwo310.appliedsorting.Constants;
import net.meatwo310.appliedsorting.config.ClientConfig;
import net.meatwo310.appliedsorting.config.SortBy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SortOverrideButton extends Button implements ITooltip {
    private static final SortBy[] VALUES = {
            SortBy.DEFAULT,
            SortBy.INTERNAL_ID,
            SortBy.RESOURCE_LOCATION
    };

    private final Runnable onChanged;
    private SortBy value;

    public SortOverrideButton(SortBy value, Runnable onChanged) {
        super(0, 0, 16, 16, Component.empty(), SortOverrideButton::onPress, Button.DEFAULT_NARRATION);
        this.value = sanitize(value);
        this.onChanged = onChanged;
    }

    private static void onPress(Button button) {
        if (button instanceof SortOverrideButton sortButton) {
            sortButton.toggle();
        }
    }

    private void toggle() {
        this.value = nextValue(isBackwardsClick());
        ClientConfig.SORT_OVERRIDE.set(this.value);
        this.onChanged.run();
    }

    private static boolean isBackwardsClick() {
        Screen currentScreen = Minecraft.getInstance().screen;
        return currentScreen instanceof AEBaseScreen<?> screen && screen.isHandlingRightClick();
    }

    private SortBy nextValue(boolean backwards) {
        int currentIndex = 0;
        for (int i = 0; i < VALUES.length; i++) {
            if (VALUES[i] == this.value) {
                currentIndex = i;
                break;
            }
        }

        int offset = backwards ? VALUES.length - 1 : 1;
        return VALUES[(currentIndex + offset) % VALUES.length];
    }

    public static SortBy sanitize(SortBy value) {
        for (SortBy allowed : VALUES) {
            if (allowed == value) {
                return value;
            }
        }
        return SortBy.DEFAULT;
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!this.visible) {
            return;
        }

        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();

        background().dest(getX() - 1, getY() + (isHovered() ? 1 : 0)).blit(guiGraphics);
        icon().dest(getX(), getY() + (isHovered() ? 2 : 1)).blit(guiGraphics);

        RenderSystem.enableDepthTest();
    }

    private Blitter background() {
        if (isHovered()) {
            return Icon.TOOLBAR_BUTTON_BACKGROUND_HOVER.getBlitter();
        }
        if (isFocused()) {
            return Icon.TOOLBAR_BUTTON_BACKGROUND_FOCUS.getBlitter();
        }
        return Icon.TOOLBAR_BUTTON_BACKGROUND.getBlitter();
    }

    private Blitter icon() {
        return switch (this.value) {
            case DEFAULT -> Icon.SORT_BY_NAME.getBlitter();
            case INTERNAL_ID -> CustomIcon.SORT_BY_ID.getBlitter();
            case RESOURCE_LOCATION -> CustomIcon.SORT_BY_RESOURCE_LOCATION.getBlitter();
        };
    }

    @Override
    public List<Component> getTooltipMessage() {
        return switch (this.value) {
            case DEFAULT -> List.of(
                    ButtonToolTips.SortBy.text(),
                    Component.translatable(Constants.TOOLTIP_DEFAULT_SORT),
                    Component.translatable(Constants.TOOLTIP_DEFAULT_SORT_HINT));
            case INTERNAL_ID -> List.of(
                    ButtonToolTips.SortBy.text(),
                    Component.translatable(Constants.TOOLTIP_INTERNAL_ID),
                    Component.translatable(Constants.TOOLTIP_INTERNAL_ID_HINT));
            case RESOURCE_LOCATION -> List.of(
                    ButtonToolTips.SortBy.text(),
                    Component.translatable(Constants.TOOLTIP_RESOURCE_LOCATION),
                    Component.translatable(Constants.TOOLTIP_RESOURCE_LOCATION_HINT));
        };
    }

    @Override
    public Rect2i getTooltipArea() {
        return new Rect2i(getX(), getY(), 16, 16);
    }

    @Override
    public boolean isTooltipAreaVisible() {
        return this.visible;
    }
}
