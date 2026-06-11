package net.meatwo310.appliedsorting;

import net.meatwo310.appliedsorting.config.ClientConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

@Mod(Constants.MODID)
public class ModMain {
    public ModMain(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }

    public static <E> int indexOfOr(List<E> list, E element, int or) {
        int index = list.indexOf(element);
        return index == -1 ? or : index;
    }
}
