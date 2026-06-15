package net.meatwo310.appliedsorting.sort;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.util.Comparator;

public class Comparators {
    public static final Comparator<AEKey> RESOURCELOC_ASC = Comparator
            .comparing(AEKey::getId, Identifier::compareNamespaced);
    public static final Comparator<AEKey> RESOURCELOC_DESC = RESOURCELOC_ASC
            .reversed();
    public static final Comparator<AEKey> RESOURCELOC_ASC_MC_FIRST = Comparator
            .comparing((AEKey key) -> !key.getId().getNamespace().equals("minecraft"))
            .thenComparing(AEKey::getId, Identifier::compareNamespaced);
    public static final Comparator<AEKey> RESOURCELOC_DESC_MC_LAST = RESOURCELOC_ASC_MC_FIRST
            .reversed();

    public static final Comparator<AEKey> ID_ASC = Comparator
            .comparing(Comparators::isNotItem)
            .thenComparing(Comparators::isNotFluid)
            .thenComparing(Comparators::getTypeId, Identifier::compareNamespaced)
            .thenComparing(Comparators::getRegistryId)
            .thenComparing(AEKey::getId, Identifier::compareNamespaced);
    public static final Comparator<AEKey> ID_DESC = ID_ASC.reversed();


    private static boolean isNotItem(AEKey key) {
        return !(key instanceof AEItemKey);
    }

    private static boolean isNotFluid(AEKey key) {
        return !(key instanceof AEFluidKey);
    }

    private static Identifier getTypeId(AEKey key) {
        return key.getType().getId();
    }

    private static int getRegistryId(AEKey key) {
        if (key instanceof AEItemKey itemKey) {
            return BuiltInRegistries.ITEM.getId(itemKey.getItem());
        } else if (key instanceof AEFluidKey fluidKey) {
            return BuiltInRegistries.FLUID.getId(fluidKey.getFluid());
        } else {
            return 0;
        }
    }
}
