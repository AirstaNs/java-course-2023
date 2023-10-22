package edu.project1;

import edu.project1.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    private static final int MAX_ATTEMPTS = 5;
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player(MAX_ATTEMPTS);
    }

    @Test
    @DisplayName("После создания, у игрока все попытки на месте")
    public void hasAllAttemptsAfterCreation() {
        assertEquals(MAX_ATTEMPTS, player.getRemainingAttempts());
    }

    @Test
    @DisplayName("После ошибки количество попыток уменьшается")
    public void attemptsDecreaseAfterMistake() {
        player.makeMistake();
        assertEquals(MAX_ATTEMPTS - 1, player.getRemainingAttempts());
    }

    @Test
    @DisplayName("При достижении максимального числа ошибок игрок проигрывает")
    public void playerLosesAfterMaxMistakes() {
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            player.makeMistake();
        }
        assertTrue(player.hasLost());
    }

    @Test
    @DisplayName("После loseGame игрок проигрывает")
    public void playerLosesAfterLoseGame() {
        player.loseGame();
        assertTrue(player.hasLost());
    }

    @Test
    @DisplayName("getRemainingAttempts возвращает правильное количество попыток")
    public void getRemainingAttemptsReturnsCorrectValue() {
        int mistakes = 3;
        for (int i = 0; i < mistakes; i++) {
            player.makeMistake();
        }
        assertEquals(MAX_ATTEMPTS - mistakes, player.getRemainingAttempts());
    }

    @Test
    @DisplayName("getMaxAttempts возвращает правильное максимальное количество попыток")
    public void getMaxAttemptsReturnsCorrectValue() {
        assertEquals(MAX_ATTEMPTS, player.getMaxAttempts());
    }
}
