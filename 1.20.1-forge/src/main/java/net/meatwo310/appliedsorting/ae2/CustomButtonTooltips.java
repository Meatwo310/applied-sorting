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
 * - Meatwo310: Changed package/class name and translation key prefix.
 * - Meatwo310: Replaced AE2's translation enum with a custom one.
 * See NOTICE file for license details.
 */

package net.meatwo310.appliedsorting.ae2;

import appeng.core.localization.LocalizationEnum;

public enum CustomButtonTooltips implements LocalizationEnum {
    InternalId("Internal ID"),
    ResourceLocation("Resource Location")
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
