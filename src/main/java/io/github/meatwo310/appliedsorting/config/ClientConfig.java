package io.github.meatwo310.appliedsorting.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.EnumValue<SortBy> ALTERNATIVE_SORT = BUILDER
            .comment("Replaces the default sorting with the selected option")
            .defineEnum("alternativeSort", SortBy.INTERNAL_ID);

    public static final ModConfigSpec.BooleanValue REMOVE_DEFAULT_SORT_BUTTON = BUILDER
            .comment("Removes the default sort button from the ME terminal")
            .define("removeDefaultSortButton", true);

    public static final ModConfigSpec.BooleanValue RESOURCE_LOCATION_MINECRAFT_FIRST = BUILDER
            .push("resourceLocation")
            .comment("Sort Minecraft items first when sorting by resource location")
            .define("minecraftFirst", true);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
