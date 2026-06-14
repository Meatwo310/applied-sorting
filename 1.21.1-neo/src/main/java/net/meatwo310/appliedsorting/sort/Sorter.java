package net.meatwo310.appliedsorting.sort;

import appeng.api.config.SortDir;
import appeng.api.stacks.AEKey;
import net.meatwo310.appliedsorting.config.ClientConfig;
import net.meatwo310.appliedsorting.config.SortBy;

import java.util.Comparator;
import java.util.Optional;

public class Sorter {
    public static Optional<Comparator<AEKey>> sort(SortDir dir, SortBy sortBy) {
        return switch (sortBy) {
            case DEFAULT, NAME, AMOUNT, MOD -> Optional.empty();
            case RESOURCE_LOCATION -> byResourceLocation(dir);
            case INTERNAL_ID -> ascOrDesc(dir, Comparators.ID_ASC, Comparators.ID_DESC);
        };
    }

    private static Optional<Comparator<AEKey>> ascOrDesc(SortDir dir, Comparator<AEKey> left, Comparator<AEKey> right) {
        return Optional.of(dir == SortDir.ASCENDING ? left : right);
    }

    private static Optional<Comparator<AEKey>> byResourceLocation(SortDir dir) {
        if (ClientConfig.ByResourceLocation.MINECRAFT_FIRST.get()) {
            return ascOrDesc(dir, Comparators.RESOURCELOC_ASC_MC_FIRST, Comparators.RESOURCELOC_DESC_MC_LAST);
        } else {
            return ascOrDesc(dir, Comparators.RESOURCELOC_ASC, Comparators.RESOURCELOC_DESC);
        }
    }
}
