package edu.hw3.task6;

import java.util.Objects;
import java.util.PriorityQueue;

public class StockMarketImpl implements StockMarket {
    private final PriorityQueue<Stock> stocks = new PriorityQueue<>();

    @Override
    public void add(Stock stock) {
        Objects.requireNonNull(stock);
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        Objects.requireNonNull(stock);
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
