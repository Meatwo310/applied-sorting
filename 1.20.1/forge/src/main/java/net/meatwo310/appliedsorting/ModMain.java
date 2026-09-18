package net.meatwo310.appliedsorting;

import net.meatwo310.appliedsorting.config.ModConfigs;
import net.meatwo310.appliedsorting.mdk.config.PlatformConfigRegistrar;
import net.meatwo310.appliedsorting.mdk.config.VersionedConfigSpec;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MODID)
public class ModMain {
    public ModMain(FMLJavaModLoadingContext context) {
        PlatformConfigRegistrar.registerAll(context, VersionedConfigSpec.bindAll(ModConfigs.ALL));
    }
}
