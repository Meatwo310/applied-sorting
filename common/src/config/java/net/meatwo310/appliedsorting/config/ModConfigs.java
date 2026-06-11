package net.meatwo310.appliedsorting.config;

import net.meatwo310.appliedsorting.mdk.config.ConfigDeclaration;
import net.meatwo310.appliedsorting.mdk.config.ConfigSide;

import java.util.List;

public final class ModConfigs {
    public static final ConfigDeclaration CLIENT = ConfigDeclaration.of(ConfigSide.CLIENT, ClientConfig.ENTRIES);

    public static final List<ConfigDeclaration> ALL = List.of(CLIENT);

    private ModConfigs() {}
}
