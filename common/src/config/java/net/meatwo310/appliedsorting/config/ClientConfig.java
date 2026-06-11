package net.meatwo310.appliedsorting.config;

import net.meatwo310.appliedsorting.mdk.config.ConfigEntries;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntry;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntryBuilder;

public final class ClientConfig {
    private static final ConfigEntryBuilder RESOURCE_LOCATION_BUILDER = new ConfigEntryBuilder();
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntry.EnumEntry<SortBy> ALTERNATIVE_SORT = BUILDER
            .comment("Replaces the default sorting with the selected option")
            .defineEnum("alternativeSort", SortBy.INTERNAL_ID);

    public static final ConfigEntry.BooleanEntry REMOVE_DEFAULT_SORT_BUTTON = BUILDER
            .comment("Removes the default sort button from the ME terminal")
            .define("removeDefaultSortButton", true);

    public static final ConfigEntry.BooleanEntry RESOURCE_LOCATION_MINECRAFT_FIRST = RESOURCE_LOCATION_BUILDER
            .comment("Sort Minecraft items first when sorting by resource location")
            .define(
                    "minecraftFirst",
                    true);

    public static final ConfigEntries ENTRIES = buildEntries();

    private ClientConfig() {}

    private static ConfigEntries buildEntries() {
        BUILDER.category("resourceLocation", RESOURCE_LOCATION_BUILDER.build());
        return BUILDER.build();
    }
}
