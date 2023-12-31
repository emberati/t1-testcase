package com.emb.test.t1.service.counter;

import java.util.Comparator;
import java.util.Map;

public class FrequencyComparatorFactory {
    public enum SortingStrategy {
        ASCENDING,
        DESCENDING
    }

    public Comparator<Map.Entry<Character, Integer>> createComparator(String strategy) {
        try {
            if (strategy == null) {
                return createComparator(SortingStrategy.DESCENDING);
            }
            return createComparator(SortingStrategy.valueOf(strategy.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid sorting order: %s! Available \"ascending\" or \"descending\"!".formatted(strategy));
        }
    }

    public Comparator<Map.Entry<Character, Integer>> createComparator(SortingStrategy strategy) {
        return switch (strategy) {
            case ASCENDING -> Map.Entry.comparingByValue(Comparator.comparingInt(val -> val));
            case DESCENDING -> Map.Entry.comparingByValue((val1, val2) -> val2 - val1);
        };
    }
}
