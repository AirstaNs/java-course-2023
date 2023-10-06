package edu.hw1;

import edu.hw1.task8.Task8;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task8Test {

    @Test
    @DisplayName("Проверка безопасного расположения коней #1")
    public void shouldReturnTrueForSafeKnightPlacement() {
        int[][] testBoard = new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };

        boolean actual = Task8.knightBoardCapture(testBoard);
        assertTrue(actual);
    }

    @Test
    @DisplayName("Проверка опасного расположения коней #2")
    public void shouldReturnFalseForUnsafePlacementOne() {
        int[][] testBoard = new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        };

        boolean actual = Task8.knightBoardCapture(testBoard);
        assertFalse(actual);
    }

    @Test
    @DisplayName("Проверка опасного расположения коней #3")
    public void shouldReturnFalseForUnsafePlacementTwo() {
        int[][] testBoard = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };

        boolean result = Task8.knightBoardCapture(testBoard);
        assertFalse(result);
    }
    @Test
    @DisplayName("Проверка доски 8x8, полностью заполненной конями")
    public void shouldReturnFalseForFullOneEightByEightBoard() {
        int[][] testBoard = new int[Task8.BOARD_SIZE][Task8.BOARD_SIZE];
        for (int i = 0; i < Task8.BOARD_SIZE; i++) {
            Arrays.fill(testBoard[i], Task8.KNIGHT);
        }

        boolean result = Task8.knightBoardCapture(testBoard);
        assertFalse(result);
    }
    @Test
    @DisplayName("Проверка неверного формата доски, размером 7x7")
    public void shouldReturnFalseForSevenBySevenBoard() {
        int[][] testBoard = new int[7][7];
        for (int i = 0; i < 7; i++) {
            Arrays.fill(testBoard[i], Task8.EMPTY);
        }

        assertThrows(IllegalArgumentException.class, () -> Task8.knightBoardCapture(testBoard));
    }
    @Test
    @DisplayName("Проверка неверного формата доски, размером 1x1")
    public void shouldReturnFalseForOneByOneBoard() {
        int[][] testBoard = {{Task8.EMPTY}};

        assertThrows(IllegalArgumentException.class, () -> Task8.knightBoardCapture(testBoard));
    }


    @Test
    @DisplayName("Проверка неверного формата доски, размером 20x20")
    public void shouldReturnFalseForTwentyByTwentyBoard() {
        int[][] testBoard = new int[20][20];
        for (int i = 0; i < 20; i++) {
            Arrays.fill(testBoard[i], Task8.EMPTY);
        }

        assertThrows(IllegalArgumentException.class, () -> Task8.knightBoardCapture(testBoard));
    }

    @Test
    @DisplayName("Проверка неверного формата доски, размером 7x8")
    public void shouldReturnFalseForZeroSevenByEightBoard() {
        int[][] testBoard = new int[7][Task8.BOARD_SIZE];
        for (int i = 0; i < 7; i++) {
            Arrays.fill(testBoard[i], Task8.EMPTY);
        }
        assertThrows(IllegalArgumentException.class, () -> Task8.knightBoardCapture(testBoard));
    }
    @Test
    @DisplayName("Проверка неверного формата доски, размером 7x8, полностью заполненной конями")
    public void shouldReturnFalseForIncorrectSevenByEightBoard() {
        int[][] testBoard = new int[7][Task8.BOARD_SIZE];
        for (int i = 0; i < 7; i++) {
            Arrays.fill(testBoard[i], Task8.KNIGHT);
        }
        assertThrows(IllegalArgumentException.class, () -> Task8.knightBoardCapture(testBoard));
    }
    @Test
    @DisplayName("Проверка доски с некорректным значением")
    public void shouldThrowExceptionForBoardWithInvalidValues() {
        int[][] testBoard = {
            {1, 0, 1, 0, 1, 0, 1, 7}, // Некорректное значение
            {0, 1, 1, 1, 1, 1, 0, 1},
            {0, 1, 1, 1, 1, 1, 0, 1},
            {0, 1, 1, 1, 1, 1, 0, 1},
            {0, 1, 1, 1, 1, 1, 0, 1},
            {0, 1, 1, 1, 1, 1, 0, 1},
            {0, 1, 1, 1, 1, 1, 0, 1},
            {0, 1, 1, 1, 1, 1, 0, 1}
        };
        assertThrows(IllegalArgumentException.class, () -> Task8.knightBoardCapture(testBoard));
    }
    @Test
    @DisplayName("Проверка доски с null значением")
    public void shouldThrowExceptionForBoardWithNullValues() {
        assertThrows(NullPointerException.class, () -> Task8.knightBoardCapture(null));
    }

    @Test
    @DisplayName("Проверка доски с null строками")
    public void shouldThrowExceptionForBoardWithNullRows() {
        int[][] board = new int[Task8.BOARD_SIZE][];
        board[0] = new int[] {1, 0, 1, 0, 1, 0, 1, 0};
        assertThrows(NullPointerException.class, () -> Task8.knightBoardCapture(board));
    }
}
