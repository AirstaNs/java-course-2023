package edu.module4.hw6.task1;

import edu.module4.hw6.task1.DiskMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
    private DiskMap diskMap;

    @BeforeAll
    static void initAll(){
        for (int i = 0; i < KEYS.size(); i++) {
            PROTOTYPE.put(KEYS.get(i), VALUES.get(i));
        }
    }

    @BeforeEach
    void setUp() {
        diskMap = new DiskMap(TEST_FILE, StandardCharsets.UTF_8);
    }


    @Test
    @DisplayName("Проверка добавления элементов")
    void testPut() {
        assertDoesNotThrow(() -> {
            for (int i = 0; i < KEYS.size(); i++) {
                diskMap.put(KEYS.get(i), VALUES.get(i));
            }
        });
    }

    @Test
    @DisplayName("Проверка наличия элементов после добавления")
    void testIsNotEmptyAfterPut() {
        for (int i = 0; i < KEYS.size(); i++) {
            diskMap.put(KEYS.get(i), VALUES.get(i));
        }
        assertFalse(diskMap.isEmpty());
    }

    @Test
    @DisplayName("Проверка очистки DiskMap")
    void testClear() {
        diskMap.clear();
        assertTrue(diskMap.isEmpty());
    }

    @Test
    @DisplayName("Проверка получения значения по ключу")
    void testGet() {
        diskMap.put("key3", "val3");
        assertEquals("val3", diskMap.get("key3"));
    }

    @Test
    @DisplayName("Проверка метода keySet")
    void testKeySet() {
        // заполните diskMap перед проверкой
        assertEquals(new HashSet<>(KEYS), diskMap.keySet());
    }

    @Test
    @DisplayName("Проверка метода values")
    void testValues() {
        VALUES.forEach(System.out::println);
        diskMap.values().forEach(System.out::println);
        assertTrue(VALUES.containsAll(diskMap.values()));
    }

    @Test
    @DisplayName("Проверка размера DiskMap")
    void testSize() {
        assertEquals(diskMap.size(), KEYS.size());
    }

    @Test
    @DisplayName("Проверка метода entrySet")
    void testEntrySet() {
        assertEquals(PROTOTYPE.entrySet(), diskMap.entrySet());
    }

    @Test
    @DisplayName("Проверка добавления всех элементов из другой карты")
    void testPutAll() {
        Map<String, String> map = Map.of("testKey", "testVal");
        assertDoesNotThrow(() -> diskMap.putAll(map));
        assertTrue(diskMap.containsValue("testVal"));
        assertTrue(diskMap.containsKey("testKey"));
    }

    @Test
    @DisplayName("Проверка удаления элемента")
    void testRemove() {
        diskMap.put("testKey", "testVal");
        assertDoesNotThrow(() -> diskMap.remove("testKey"));
        assertFalse(diskMap.containsKey("testKey"));
    }

    @TempDir
    Path tempDir;

    @Test
    void save_ShouldCorrectlyWriteDataToFile() throws Exception {
        // Given
        Path testfile = Files.createTempFile(tempDir, "testfile", ".txt");
        DiskMap diskMap = new DiskMap(testfile, StandardCharsets.UTF_8);
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        // When
        diskMap.save();

        // Then
        List<String> lines = Files.readAllLines(testfile, StandardCharsets.UTF_8);
        assertTrue(lines.contains("key1:value1"));
        assertTrue(lines.contains("key2:value2"));
    }
}
