package net.meatwo310.appliedsorting.config;

import net.meatwo310.appliedsorting.mdk.config.ConfigEntries;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntry;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntryBuilder;

public final class ClientConfig {
    private static final ConfigEntryBuilder RESOURCE_LOCATION_BUILDER = new ConfigEntryBuilder();
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntry.EnumEntry<SortBy> ALTERNATIVE_SORT = BUILDER
            .comment(ClientConfigKeys.ALTERNATIVE_SORT_COMMENT)
            .defineEnum(ClientConfigKeys.ALTERNATIVE_SORT, ClientConfigKeys.ALTERNATIVE_SORT_DEFAULT);

    public static final ConfigEntry.BooleanEntry REMOVE_DEFAULT_SORT_BUTTON = BUILDER
            .comment(ClientConfigKeys.REMOVE_DEFAULT_SORT_BUTTON_COMMENT)
            .define(ClientConfigKeys.REMOVE_DEFAULT_SORT_BUTTON, ClientConfigKeys.REMOVE_DEFAULT_SORT_BUTTON_DEFAULT);

    public static final ConfigEntry.BooleanEntry RESOURCE_LOCATION_MINECRAFT_FIRST = RESOURCE_LOCATION_BUILDER
            .comment(ClientConfigKeys.RESOURCE_LOCATION_MINECRAFT_FIRST_COMMENT)
            .define(
                    ClientConfigKeys.RESOURCE_LOCATION_MINECRAFT_FIRST,
                    ClientConfigKeys.RESOURCE_LOCATION_MINECRAFT_FIRST_DEFAULT);

    public static final ConfigEntries ENTRIES = buildEntries();

    private ClientConfig() {}

    private static ConfigEntries buildEntries() {
        BUILDER.category(ClientConfigKeys.RESOURCE_LOCATION, RESOURCE_LOCATION_BUILDER.build());
        return BUILDER.build();
    }
}
