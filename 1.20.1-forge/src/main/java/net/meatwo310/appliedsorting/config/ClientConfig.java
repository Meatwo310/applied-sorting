package net.meatwo310.appliedsorting.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.EnumValue<SortBy> ALTERNATIVE_SORT = BUILDER
            .comment("Replaces the default sorting with the selected option")
            .defineEnum("alternativeSort", SortBy.INTERNAL_ID);

    public static final ForgeConfigSpec.BooleanValue REMOVE_DEFAULT_SORT_BUTTON = BUILDER
            .comment("Removes the default sort button from the ME terminal")
            .define("removeDefaultSortButton", true);

    public static final ForgeConfigSpec.BooleanValue RESOURCE_LOCATION_MINECRAFT_FIRST = BUILDER
            .push("resourceLocation")
            .comment("Sort Minecraft items first when sorting by resource location")
            .define("minecraftFirst", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
