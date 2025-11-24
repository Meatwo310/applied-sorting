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
 * - Meatwo310: Changed package/class name.
 * - Meatwo310: Generalized to render any icon via an abstract `getBlitter()` method.
 * - Mochi_753: Modified to match the design of AE2 in Minecraft 1.21.1.
 *
 * See NOTICE file for license details.
 */

package io.github.meatwo310.appliedsorting.ae2;

import appeng.client.gui.Icon;
import appeng.client.gui.style.Blitter;
import appeng.client.gui.widgets.ITooltip;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public abstract class CustomIconButton extends Button implements ITooltip {
    private boolean halfSize = false;
    private boolean disableClickSound = false;
    private boolean disableBackground = false;

    protected CustomIconButton(OnPress onPress) {
        super(0, 0, 16, 16, Component.empty(), onPress, Button.DEFAULT_NARRATION);
    }

    public void setVisibility(boolean visibility) {
        this.visible = visibility;
        this.active = visibility;
    }

    @Override
    public void playDownSound(@NotNull SoundManager handler) {
        if (!this.disableClickSound) super.playDownSound(handler);
    }

    @Override
    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (this.visible) {
            Blitter blitter = this.getBlitter();
            if (!this.active) blitter.opacity(0.5F);

            if (this.halfSize) {
                this.width = 8;
                this.height = 8;
            }

            RenderSystem.disableDepthTest();
            RenderSystem.enableBlend();

            if (this.halfSize) {
                PoseStack pose = guiGraphics.pose();
                pose.pushPose();
                pose.translate(getX(), getY(), 0.0F);
                pose.scale(0.5f, 0.5f, 1.f);

                if (!disableBackground) renderBackground(guiGraphics);
                renderIcon(guiGraphics);
                pose.popPose();
            } else {
                if (!disableBackground) renderBackground(guiGraphics);
                renderIcon(guiGraphics);
            }
            RenderSystem.enableDepthTest();

            Item item = this.getItemOverlay();
            if (item != null) guiGraphics.renderItem(new ItemStack(item), getX(), getY());
        }
    }

    private void renderBackground(GuiGraphics guiGraphics) {
        if (isHovered()) {
            Icon.TOOLBAR_BUTTON_BACKGROUND_HOVER.getBlitter().dest(getX() - 1, getY() + 1).blit(guiGraphics);
        } else if (isFocused()) {
            Icon.TOOLBAR_BUTTON_BACKGROUND_FOCUS.getBlitter().dest(getX() - 1, getY()).blit(guiGraphics);
        } else {
            Icon.TOOLBAR_BUTTON_BACKGROUND.getBlitter().dest(getX() - 1, getY()).blit(guiGraphics);
        }
    }

    private void renderIcon(GuiGraphics guiGraphics) {
        if (isHovered) {
            this.getBlitter().dest(getX(), getY() + 2).blit(guiGraphics);
        } else {
            this.getBlitter().dest(getX(), getY() + 1).blit(guiGraphics);
        }
    }

    /**
     * @deprecated Use {@link #getBlitter()} instead.
     */
    @Deprecated
    protected Icon getIcon() {
        throw new UnsupportedOperationException("Use getBlitter() instead");
    }

    protected abstract Blitter getBlitter();

    @Nullable
    protected Item getItemOverlay() {
        return null;
    }

    @Override
    public List<Component> getTooltipMessage() {
        return Collections.singletonList(getMessage());
    }

    @Override
    public Rect2i getTooltipArea() {
        return new Rect2i(getX(), getY(), this.halfSize ? 8 : 16, this.halfSize ? 8 : 16);
    }

    public boolean isHalfSize() {
        return this.halfSize;
    }

    public void setHalfSize(boolean halfSize) {
        this.halfSize = halfSize;
    }

    public boolean isDisableClickSound() {
        return disableClickSound;
    }

    public void setDisableClickSound(boolean disableClickSound) {
        this.disableClickSound = disableClickSound;
    }

    public boolean isDisableBackground() {
        return disableBackground;
    }

    public void setDisableBackground(boolean disableBackground) {
        this.disableBackground = disableBackground;
    }
}
