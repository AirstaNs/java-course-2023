package edu.module3.hw3.task5;

import static org.junit.jupiter.api.Assertions.*;

import edu.module3.hw3.task5.Contact;
import edu.module3.hw3.task5.OrderType;
import edu.module3.hw3.task5.Task5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

class ContactTest {

    private static List<Arguments> provideContactsForSorting() {
        Contact Locke = Task5.parse("John Locke");
        Contact Aquinas = Task5.parse("Thomas Aquinas");
        Contact Hume = Task5.parse("David Hume");

        Contact Descartes = Task5.parse("Rene Descartes");
        Contact erdos = Task5.parse("Paul Erdos");
        Contact euler = Task5.parse("Leonhard Euler");
        Contact gauss = Task5.parse("Carl Gauss");

        return List.of(
            Arguments.of(
                OrderType.ASC,
                List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"),
                List.of(Aquinas, Descartes, Hume, Locke)
            ),

            Arguments.of(
                OrderType.DESC,
                List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss"),
                List.of(gauss, euler, erdos)
            ),
            Arguments.of(
                OrderType.DESC,
                new ArrayList<String>(),
                new ArrayList<Contact>()),
            Arguments.of(
                OrderType.DESC,
                null,
                new ArrayList<Contact>()
            )
        );
    }

    @ParameterizedTest
    @MethodSource("provideContactsForSorting")
    @DisplayName("Проверка сортировки Contact")
    void testContactSorting(OrderType type, List<String> input, List<Contact> expected) {
        // When
        List<Contact> result = Task5.processContacts(input, type);

        // Then
        assertEquals(expected, result);
    }

    // Пограничные значения
    private static List<Arguments> provideStringsForParsing() {
        return List.of(
            Arguments.of("", new Contact("", null)),
            Arguments.of("John", new Contact("John", null))
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForParsing")
    @DisplayName("Проверка разбора строк")
    void testParse(String input, Contact expected) {
        // When
        Contact result = Task5.parse(input);

        // Then
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("Тест на null порядок сортировки")
    void testNullOrderType() {
        assertThrows(
            NullPointerException.class,
            () -> Task5.processContacts(List.of("John Locke", "Thomas Aquinas"), null));
    }
}
