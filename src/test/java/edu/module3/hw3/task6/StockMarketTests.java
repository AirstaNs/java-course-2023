package edu.module3.hw3.task6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import edu.module3.hw3.task6.Stock;
import edu.module3.hw3.task6.StockMarket;
import edu.module3.hw3.task6.StockMarketImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;

public class StockMarketTests {



    private static List<Arguments> stockProvider() {
        var apple = new Stock("APPLE", 100.5);
        var google =  new Stock("GOOGLE", 210.2);
        var amazon = new Stock("AMAZON", 310.8);
        return List.of(
            Arguments.of(
                List.of(apple, google, amazon),
                amazon
            ),
            Arguments.of(
                List.of(apple, google),
                google
            )
        );
    }

    @ParameterizedTest
    @MethodSource("stockProvider")
    @DisplayName("Проверка нахождения самой дорогой акции")
    void testMostValuableStock(List<Stock> inputList, Stock expected) {
        StockMarket market = new StockMarketImpl();
        for (Stock stock : inputList) {
            market.add(stock);
        }
        Stock stock = market.mostValuableStock();
        assertEquals(expected, stock);
    }

    @Test
    @DisplayName("Тест удаления акции и нахождения новой самой дорогой акции")
    void testRemovingStock() {
        StockMarket market = new StockMarketImpl();
        var apple = new Stock("APPLE", 100.5);
        var google =  new Stock("GOOGLE", 210.2);
        var amazon = new Stock("AMAZON", 310.8);
        market.add(apple);
        market.add(google);
        market.add(amazon);

        assertEquals(amazon, market.mostValuableStock());

        market.remove(amazon);
        assertEquals(google, market.mostValuableStock());
    }

    @Test
    @DisplayName("Тест на добавление null акции")
    void testAddingNullStock() {
        StockMarket market = new StockMarketImpl();
        assertThrows(NullPointerException.class, () -> market.add(null));
    }

    @Test
    @DisplayName("Тест на удаление null акции")
    void testRemovingNullStock() {
        StockMarket market = new StockMarketImpl();
        assertThrows(NullPointerException.class, () -> market.remove(null));
    }

    @Test
    @DisplayName("Тест на получение самой дорогой акции из пустого рынка")
    void testEmptyMarket() {
        StockMarket market = new StockMarketImpl();
        assertNull(market.mostValuableStock());
    }
}
