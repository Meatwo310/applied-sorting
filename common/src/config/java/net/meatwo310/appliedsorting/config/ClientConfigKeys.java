package net.meatwo310.appliedsorting.config;

public final class ClientConfigKeys {
    public static final String ALTERNATIVE_SORT = "alternativeSort";
    public static final SortBy ALTERNATIVE_SORT_DEFAULT = SortBy.INTERNAL_ID;
    public static final String ALTERNATIVE_SORT_COMMENT = "Replaces the default sorting with the selected option";

    public static final String REMOVE_DEFAULT_SORT_BUTTON = "removeDefaultSortButton";
    public static final boolean REMOVE_DEFAULT_SORT_BUTTON_DEFAULT = true;
    public static final String REMOVE_DEFAULT_SORT_BUTTON_COMMENT = "Removes the default sort button from the ME terminal";

    public static final String RESOURCE_LOCATION = "resourceLocation";
    public static final String RESOURCE_LOCATION_MINECRAFT_FIRST = "minecraftFirst";
    public static final boolean RESOURCE_LOCATION_MINECRAFT_FIRST_DEFAULT = true;
    public static final String RESOURCE_LOCATION_MINECRAFT_FIRST_COMMENT =
            "Sort Minecraft items first when sorting by resource location";

    private ClientConfigKeys() {}
}
