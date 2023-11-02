package edu.hw3.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NullFriendlyComparatorTest {

    @Test
    @DisplayName("Проверка TreeMap с компаратором, поддерживающим null")
    void validateNullFriendlyTreeMap() {
        //Given
        TreeMap<String, String> treeMap = new TreeMap<>(new NullFriendlyComparator<>());
        treeMap.put(null, "test");
        treeMap.put("key", "value");

        // When
        String result = treeMap.get(null);
        String result2 = treeMap.get("key");

        // Then
        assertEquals("test", result);
        assertEquals("value", result2);
    }

    @Test
    @DisplayName("Тест на добавление null значения в TreeMap без нашего компаратора")
    void testTreeMapWithDefaultComparator() {
        //Given
        TreeMap<String, String> tree = new TreeMap<>();
        // When & Then
        assertThrows(NullPointerException.class, () -> tree.put(null, "test"));
    }

}

