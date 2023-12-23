package edu.module2.hw1.task8;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Task8 {
    public static final int BOARD_SIZE = 8;
    public static final int KNIGHT = 1;

    public static final int EMPTY = 0;

    private Task8() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        if (!isValidBoard(board)) {
            throw new IllegalArgumentException("Неправильный формат ввода");
        }

        List<Knight> knights = getKnights(board);
        return areKnightsSafe(knights);

    }

    /*
     Проверяет, расположены ли кони на доске так, что ни один из них не может
     атаковать другого. Функция сравнивает каждого коня с другими на доске,
     и если хотя бы одна пара коней может атаковать друг друга, возвращается false.
     */
    private static boolean areKnightsSafe(List<Knight> knights) {
        int size = knights.size();
        for (int i = 0; i < size; i++) {
            Knight currentKnight = knights.get(i);
            for (int j = i + 1; j < size; j++) {
                Knight otherKnight = knights.get(j);
                if (currentKnight.canCapture(otherKnight)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Проходит по всем клеткам шахматной доски и добавляет коней в список.
    private static List<Knight> getKnights(int[][] board) {
        List<Knight> knights = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (KNIGHT == board[i][j]) {
                    knights.add(new Knight(i, j));
                }
            }
        }

        return knights;
    }

    /*
     Проверяет корректность размеров доски. Валидация проводится на основе
     размера доски и каждой ячейки. Доска должна быть размером 8x8.
     */
    private static boolean isValidBoard(int[][] board) {
        Objects.requireNonNull(board);

        if (isNotValidSize(board.length)) {
            return false;
        }
        for (var row : board) {
            Objects.requireNonNull(row);
            if (isNotValidSize(row.length)) {
                return false;
            }

            // проверка содержимого
            for (int cell : row) {
                if (EMPTY != cell && KNIGHT != cell) {
                    return false;
                }
            }
        }
        return true;
    }

    // Проверяет, соответствует ли заданный размер стандартному размеру доски (8x8).
    private static boolean isNotValidSize(int size) {
        return size != BOARD_SIZE;
    }
}
