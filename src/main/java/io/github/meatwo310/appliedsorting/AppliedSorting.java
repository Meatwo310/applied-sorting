package io.github.meatwo310.appliedsorting;

import io.github.meatwo310.appliedsorting.config.ClientConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

@Mod(AppliedSorting.MODID)
public class AppliedSorting {
    public static final String MODID = "appliedsorting";

    public AppliedSorting(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }

    public static <E> int indexOfOr(List<E> list, E element, int or) {
        int index = list.indexOf(element);
        return index == -1 ? or : index;
    }
}
