package edu.module3.hw3.task8;
import static org.junit.jupiter.api.Assertions.*;

import edu.module3.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIteratorTest {

    @Test
    @DisplayName("Итератор должен правильно возвращать элементы в обратном порядке")
    void shouldIterateInReverseOrder() {
        Iterator<Integer> it = new BackwardIterator<>(Arrays.asList(1, 2, 3));

        assertTrue(it.hasNext());
        assertEquals(3, it.next());

        assertTrue(it.hasNext());
        assertEquals(2, it.next());

        assertTrue(it.hasNext());
        assertEquals(1, it.next());

        assertFalse(it.hasNext());
    }

    @Test
    @DisplayName("Итератор должен бросить исключение, если нет следующего элемента")
    void shouldThrowNoSuchElementException() {
        Iterator<Integer> it = new BackwardIterator<>(List.of(1));

        assertEquals(1, it.next());

        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    @DisplayName("Итератор с пустым списком не должен иметь следующего элемента")
    void shouldHandleEmptyList() {
        Iterator<Integer> it = new BackwardIterator<>(List.of());

        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }
}
