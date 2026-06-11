package net.meatwo310.appliedsorting.util;

import java.util.List;

public final class CollectionUtils {
    private CollectionUtils() {}

    public static <E> int indexOfOr(List<E> list, E element, int fallback) {
        int index = list.indexOf(element);
        return index == -1 ? fallback : index;
    }
}
