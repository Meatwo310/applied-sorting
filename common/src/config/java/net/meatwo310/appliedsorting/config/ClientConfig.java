package net.meatwo310.appliedsorting.config;

import net.meatwo310.appliedsorting.mdk.config.ConfigEntries;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntry;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntryBuilder;

public final class ClientConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntry.EnumEntry<SortBy> ALTERNATIVE_SORT = BUILDER
            .comment("Replaces the default sorting with the selected option")
            .defineEnum("alternativeSort", SortBy.INTERNAL_ID);

    public static final ConfigEntry.BooleanEntry REMOVE_DEFAULT_SORT_BUTTON = BUILDER
            .comment("Removes the default sort button from the ME terminal")
            .define("removeDefaultSortButton", true);

    private static final ConfigEntries RESOURCE_LOCATION = BUILDER
            .category("byResourceLocation", ByResourceLocation.ENTRIES);

    public static final class ByResourceLocation {
        private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

        public static final ConfigEntry.BooleanEntry MINECRAFT_FIRST = BUILDER
                .comment("Sort Minecraft items first when sorting by resource location")
                .define("minecraftFirst", true);

        private static final ConfigEntries ENTRIES = BUILDER.build();
    }

    public static final ConfigEntries ENTRIES = BUILDER.build();

    private ClientConfig() {}
}
