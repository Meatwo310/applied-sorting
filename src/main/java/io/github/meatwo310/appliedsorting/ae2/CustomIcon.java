/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2021, TeamAppliedEnergistics, All rights reserved.
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
 * - Meatwo310: Replaced AE2's icons with custom ones.
 *
 * See NOTICE file for license details.
 */

package io.github.meatwo310.appliedsorting.ae2;

import appeng.client.gui.style.Blitter;
import io.github.meatwo310.appliedsorting.AppliedSorting;
import net.minecraft.resources.ResourceLocation;

/**
 * Edit in {@code assets/appliedsorting/textures/guis/states.png}.
 */
public enum CustomIcon {
    SORT_BY_ID(0, 0),
    SORT_BY_RESOURCE_LOCATION(16, 0);

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            AppliedSorting.MOD_ID,
            "textures/guis/states.png"
    );
    public static final int TEXTURE_WIDTH = 32;
    public static final int TEXTURE_HEIGHT = 32;
    public final int x;
    public final int y;
    public final int width;
    public final int height;

    CustomIcon(int x, int y) {
        this(x, y, 16, 16);
    }

    CustomIcon(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Blitter getBlitter() {
        return Blitter.texture(TEXTURE, TEXTURE_WIDTH, TEXTURE_HEIGHT)
                .src(x, y, width, height);
    }
}
