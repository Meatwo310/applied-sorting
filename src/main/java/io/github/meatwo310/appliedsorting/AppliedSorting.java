package io.github.meatwo310.appliedsorting;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import io.github.meatwo310.appliedsorting.config.ClientConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Comparator;

@Mod(AppliedSorting.MODID)
public class AppliedSorting {
    public static final String MODID = "appliedsorting";
    public static final Comparator<AEKey> RESOURCELOC_ASC = Comparator
            .comparing(AEKey::getId, ResourceLocation::compareNamespaced);
    public static final Comparator<AEKey> RESOURCELOC_DESC = RESOURCELOC_ASC
            .reversed();
    public static final Comparator<AEKey> RESOURCELOC_ASC_MC_FIRST = Comparator
            .comparing((AEKey key) -> !key.getId().getNamespace().equals("minecraft"))
            .thenComparing(AEKey::getId, ResourceLocation::compareNamespaced);
    public static final Comparator<AEKey> RESOURCELOC_DESC_MC_LAST = RESOURCELOC_ASC_MC_FIRST
            .reversed();

    @SuppressWarnings("deprecation")
    public static final Comparator<AEKey> ID_ASC = Comparator
            .comparing(key -> key, (left, right) -> {
                if (left instanceof AEItemKey leftItemKey && right instanceof AEItemKey rightItemKey) {
                    return Integer.compare(
                            Item.getId(leftItemKey.getItem()),
                            Item.getId(rightItemKey.getItem())
                    );
                }

                if (left instanceof AEFluidKey leftFluidKey && right instanceof AEFluidKey rightFluidKey) {
                    return Integer.compare(
                            BuiltInRegistries.FLUID.getId(leftFluidKey.getFluid()),
                            BuiltInRegistries.FLUID.getId(rightFluidKey.getFluid())
                    );
                }

                return 0;
            });
    public static final Comparator<AEKey> ID_DESC = ID_ASC.reversed();

    public AppliedSorting(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
    }
}
