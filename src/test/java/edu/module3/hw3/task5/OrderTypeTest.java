package edu.module3.hw3.task5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.module3.hw3.task5.Contact;
import edu.module3.hw3.task5.OrderType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;

class OrderTypeTest {

    @ParameterizedTest
    @MethodSource("orderTypesProvider")
    @DisplayName("Проверка корректности возвращаемого компаратора из OrderType")
    void testOrderType(OrderType type, Comparator<Contact> expected) {
        assertEquals(expected, type.getComparator());
    }

    private static List<Arguments> orderTypesProvider() {
        return List.of(
            Arguments.of(OrderType.ASC, Comparator.naturalOrder()),
            Arguments.of(OrderType.DESC, Comparator.reverseOrder())
        );
    }
}
