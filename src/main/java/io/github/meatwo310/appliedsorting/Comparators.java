package io.github.meatwo310.appliedsorting;

import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.Comparator;

public class Comparators {
    public static final Comparator<AEKey> RESOURCELOC_ASC = Comparator
            .comparing(AEKey::getId, ResourceLocation::compareNamespaced);
    public static final Comparator<AEKey> RESOURCELOC_DESC = RESOURCELOC_ASC
            .reversed();
    public static final Comparator<AEKey> RESOURCELOC_ASC_MC_FIRST = Comparator
            .comparing((AEKey key) -> !key.getId().getNamespace().equals("minecraft"))
            .thenComparing(AEKey::getId, ResourceLocation::compareNamespaced);
    public static final Comparator<AEKey> RESOURCELOC_DESC_MC_LAST = RESOURCELOC_ASC_MC_FIRST
            .reversed();

    public static final Comparator<AEKey> ID_ASC = Comparator.comparing(Comparators::getSortableInfo);
    public static final Comparator<AEKey> ID_DESC = ID_ASC.reversed();

    @SuppressWarnings("deprecation")
    private static SortableKeyInfo getSortableInfo(AEKey key) {
        if (key instanceof AEItemKey itemKey) {
            int id = BuiltInRegistries.ITEM.getId(itemKey.getItem());
            return new SortableKeyInfo(KeyType.ITEM, id);
        } else if (key instanceof AEFluidKey fluidKey) {
            int id = BuiltInRegistries.FLUID.getId(fluidKey.getFluid());
            return new SortableKeyInfo(KeyType.FLUID, id);
        }
        return new SortableKeyInfo(KeyType.UNKNOWN, 0);
    }

    private enum KeyType {
        ITEM,
        FLUID,
        UNKNOWN,
    }

    private record SortableKeyInfo(KeyType type, int id) implements Comparable<SortableKeyInfo> {
        @Override
        public int compareTo(SortableKeyInfo other) {
            // 最初にKeyTypeで比較し、同じならidで比較
            int typeCompare = this.type.compareTo(other.type);
            if (typeCompare != 0) return typeCompare;
            return Integer.compare(this.id, other.id);
        }
    }
}