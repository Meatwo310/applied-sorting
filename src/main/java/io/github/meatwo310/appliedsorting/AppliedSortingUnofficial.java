package io.github.meatwo310.appliedsorting;

import io.github.meatwo310.appliedsorting.config.ClientConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

import java.util.List;

@Mod(AppliedSortingUnofficial.MOD_ID)
public class AppliedSortingUnofficial {
    public static final String MOD_ID = "appliedsorting";

    public AppliedSortingUnofficial(ModContainer container, IEventBus eventBus) {
        container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }

    public static <E> int indexOfOr(List<E> list, E element, int or) {
        int index = list.indexOf(element);
        return index == -1 ? or : index;
    }
}
