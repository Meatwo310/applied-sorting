package net.meatwo310.appliedsorting.config;

import net.meatwo310.appliedsorting.mdk.config.ConfigEntries;
import net.meatwo310.appliedsorting.mdk.config.ConfigEntryBuilder;

public class ServerConfig {
    private static final ConfigEntryBuilder BUILDER = new ConfigEntryBuilder();

    public static final ConfigEntries ENTRIES = BUILDER.build();
}
