package io.github.meatwo310.appliedsorting;

import appeng.api.config.SortDir;
import appeng.api.stacks.AEKey;
import io.github.meatwo310.appliedsorting.config.ClientConfig;
import io.github.meatwo310.appliedsorting.config.SortBy;

import java.util.Comparator;
import java.util.Optional;

public class Sorter {
    public static Optional<Comparator<AEKey>> sort(SortDir dir, SortBy sortBy) {
        return switch (sortBy) {
            case UNCHANGED -> Optional.empty();
            case RESOURCE_LOCATION -> byResourceLocation(dir);
            case INTERNAL_ID -> ascOrDesc(dir, AppliedSorting.ID_ASC, AppliedSorting.ID_DESC);
        };
    }

    public static Optional<Comparator<AEKey>> ascOrDesc(SortDir dir, Comparator<AEKey> left, Comparator<AEKey> right) {
        return Optional.of(dir == SortDir.ASCENDING ? left : right);
    }

    public static Optional<Comparator<AEKey>> byResourceLocation(SortDir dir) {
        if (ClientConfig.RESOURCE_LOCATION_MINECRAFT_FIRST.get()) {
            return ascOrDesc(dir, AppliedSorting.RESOURCELOC_ASC_MC_FIRST, AppliedSorting.RESOURCELOC_DESC_MC_LAST);
        } else {
            return ascOrDesc(dir, AppliedSorting.RESOURCELOC_ASC, AppliedSorting.RESOURCELOC_DESC);
        }
    }
}
