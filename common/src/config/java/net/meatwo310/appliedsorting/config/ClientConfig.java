package net.meatwo310.appliedsorting.config;

import net.meatwo310.appliedsorting.mdk.config.ConfigEntries;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntry;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntryBuilder;

public final class ClientConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntry.EnumEntry<SortBy> SORT_OVERRIDE = BUILDER
            .comment("Overrides the AE2 sort method when set to a non-default option")
            .defineEnum("sortOverride", SortBy.DEFAULT);

    private static final ConfigEntries BY_RESOURCE_LOCATION = BUILDER
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
