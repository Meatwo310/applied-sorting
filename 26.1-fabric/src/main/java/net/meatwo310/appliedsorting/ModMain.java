package net.meatwo310.appliedsorting;

import net.fabricmc.api.ModInitializer;
import net.meatwo310.appliedsorting.config.ModConfigs;
import net.meatwo310.appliedsorting.mdk.config.PlatformConfigRegistrar;
import net.meatwo310.appliedsorting.mdk.config.VersionedConfigSpec;

public class ModMain implements ModInitializer {
    @Override
    public void onInitialize() {
        Constants.LOGGER.debug(Constants.INITIALIZING, ModUtils.id("26.1-fabric"));
        PlatformConfigRegistrar.registerAll(Constants.MODID, VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
