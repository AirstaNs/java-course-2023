package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Task3 {

    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Objects.requireNonNull(list);

        Map<T, Integer> freqMap = new HashMap<>();
        for (T s : list) {
            freqMap.merge(s, 1, Integer::sum);
        }
        return freqMap;
    }
}
