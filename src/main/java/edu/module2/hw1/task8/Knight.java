package edu.module2.hw1.task8;

class Knight {
    private final int x;
    private final int y;

    // Константы для представления хода коня
    private static final int LONG_MOVE = 2;
    private static final int SHORT_MOVE = 1;

    Knight(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Проверяет, может ли текущий конь захватить другого коня на основе их позиций на доске.
    boolean canCapture(Knight other) {
        // Вычисляем горизонтальное и вертикальное расстояния между конями
        int horizontalDistance = Math.abs(x - other.x);
        int verticalDistance = Math.abs(y - other.y);
        // Проверяем, соответствует ли расстояние между конями
        return (horizontalDistance == LONG_MOVE && verticalDistance == SHORT_MOVE)
               || (horizontalDistance == SHORT_MOVE && verticalDistance == LONG_MOVE);
    }
}

