package edu.hw7.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

class SynchronizedDatabaseTest {

    private static Stream<Person> personProvider() {
        return Stream.of(
            new Person(1, "Alice", "123 Maple Street", "555-1234"),
            new Person(2, "Bob", "456 Oak Street", "555-5678"),
            new Person(3, "Charlie", "789 Pine Street", "555-9012")
        );
    }

    @ParameterizedTest
    @MethodSource("personProvider")
    void testAddAndFind(Person person) {
        SynchronizedDatabase db = new SynchronizedDatabase();

        // Given
        db.add(person);

        // When/Then
        assertTrue(db.findByName(person.name()).contains(person));
        assertTrue(db.findByAddress(person.address()).contains(person));
        assertTrue(db.findByPhone(person.phoneNumber()).contains(person));
    }

    @ParameterizedTest
    @MethodSource("personProvider")
    void testDelete(Person person) {
        SynchronizedDatabase db = new SynchronizedDatabase();

        // Given
        db.add(person);
        db.delete(person.id());

        // When/Then
        assertFalse(db.findByName(person.name()).contains(person));
        assertFalse(db.findByAddress(person.address()).contains(person));
        assertFalse(db.findByPhone(person.phoneNumber()).contains(person));
    }

    @Test
    void testMultithreading() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        SynchronizedDatabase db = new SynchronizedDatabase();

        Runnable addTask = () -> {
            for (int i = 0; i < 100; i++) {
                db.add(new Person(i, "Name" + i, "Address" + i, "Phone" + i));
            }
        };

        Runnable deleteTask = () -> {
            for (int i = 0; i < 50; i++) {
                db.delete(i);
            }
        };

        for (int i = 0; i < 2; i++) {
            executorService.submit(addTask);
            executorService.submit(deleteTask);
        }

        executorService.close();

        // Проверяем, что количество записей в базе данных соответствует ожидаемому
        assertTrue(db.getSize() >= 50 && db.getSize() <= 200);
    }
}
