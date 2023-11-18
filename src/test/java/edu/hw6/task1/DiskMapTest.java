package edu.hw6.task1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskMapTest {

    private static final List<String> KEYS = List.of("key1", "key2", "key3", "key4");
    private static final List<String> VALUES = List.of("val1", "val2", "val3", "val4");
    private static final Map<String, String> PROTOTYPE = new HashMap<>();
    private static final Path TEST_FILE = Path.of("./src/test/resources/hw6/task1test.txt");


    @BeforeAll
    static void initAll(){
        for (int i = 0; i < KEYS.size(); i++) {
            PROTOTYPE.put(KEYS.get(i), VALUES.get(i));
        }
    }

    @Test
    @DisplayName("Тестирование функциональности DiskMap")
    void testDiskMap() {
        // Given
        DiskMap diskMap = new DiskMap(TEST_FILE, StandardCharsets.UTF_8);

        // When
        assertDoesNotThrow(() -> {
            for (int i = 0; i < KEYS.size(); i++) {
                diskMap.put(KEYS.get(i), VALUES.get(i));
            }
        });

        // Then
        assertTrue(!diskMap.isEmpty());
        diskMap.clear();
        assertTrue(diskMap.isEmpty());
        diskMap.read();

        assertEquals("val3", diskMap.get("key3"));
        assertEquals(new HashSet<>(KEYS), diskMap.keySet());
        assertTrue(VALUES.containsAll(diskMap.values()));
        assertEquals(diskMap.size(), KEYS.size());
        assertEquals(PROTOTYPE.entrySet(), diskMap.entrySet());

        Map<String, String> map = Map.of("testKey", "testVal");

        assertDoesNotThrow(() -> diskMap.putAll(map));
        assertTrue(diskMap.containsValue("testVal"));
        assertTrue(diskMap.containsKey("testKey"));

        assertDoesNotThrow(() -> diskMap.remove("testKey"));
        assertFalse(diskMap.containsKey("testKey"));
    }
}
