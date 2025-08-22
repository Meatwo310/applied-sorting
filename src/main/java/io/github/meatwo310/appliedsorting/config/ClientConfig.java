package io.github.meatwo310.appliedsorting.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.EnumValue<SortBy> SORT_BY = BUILDER
            .comment("Sort items by the specified method")
            .defineEnum("sortBy", SortBy.INTERNAL_ID);

    public static final ForgeConfigSpec.BooleanValue RESOURCE_LOCATION_MINECRAFT_FIRST = BUILDER
            .push("resourceLocation")
            .comment("Sort Minecraft items first when sorting by resource location")
            .define("minecraftFirst", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
