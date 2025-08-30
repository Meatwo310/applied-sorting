package io.github.meatwo310.appliedsorting.datagen;

import io.github.meatwo310.appliedsorting.AppliedSorting;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AppliedSorting.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ASDataGens {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();

        ASLangGen.register(generator, output);
    }
}
