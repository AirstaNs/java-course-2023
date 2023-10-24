package edu.hw3.task6;

import java.util.PriorityQueue;
import org.jetbrains.annotations.NotNull;

public class StockMarketImpl implements StockMarket {
    private final PriorityQueue<Stock> stocks = new PriorityQueue<>();

    @Override
    public void add(@NotNull Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(@NotNull Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
