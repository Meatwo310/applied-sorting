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
        ResourceLocation typeId = key.getType().getId();
        int id;
        if (key instanceof AEItemKey itemKey) {
            id = BuiltInRegistries.ITEM.getId(itemKey.getItem());
        } else if (key instanceof AEFluidKey fluidKey) {
            id = BuiltInRegistries.FLUID.getId(fluidKey.getFluid());
        } else {
            id = 0;
        }
        return new SortableKeyInfo(typeId, id, key.getId());
    }

    private record SortableKeyInfo(ResourceLocation typeId, int id, ResourceLocation loc) implements Comparable<SortableKeyInfo> {
        @Override
        public int compareTo(SortableKeyInfo other) {
            // 特定のResourceLocationの優先順位を設定
            int thisPriority = getTypePriority(this.typeId);
            int otherPriority = getTypePriority(other.typeId);

            // 優先度で比較
            int priorityCompare = Integer.compare(thisPriority, otherPriority);
            if (priorityCompare != 0) return priorityCompare;

            // 同じ優先度の場合はtypeで比較
            int typeCompare = this.typeId.compareNamespaced(other.typeId);
            if (typeCompare != 0) return typeCompare;

            // idで比較
            int idCompare = Integer.compare(this.id, other.id);
            if (idCompare != 0) return idCompare;

            // locで比較
            return this.loc.compareNamespaced(other.loc);
        }

        private static int getTypePriority(ResourceLocation typeId) {
            String typeIdString = typeId.toString();
            if ("ae2:i".equals(typeIdString)) return 0; // 最優先
            if ("ae2:f".equals(typeIdString)) return 1; // 2番目
            return Integer.MAX_VALUE; // その他
        }
    }
}