package edu.project1;

import edu.project1.action.GameAction;
import edu.project1.game.ConsoleHangman;
import edu.project1.game.GameConfiguration;
import edu.project1.game.GameMessagesPrinter;
import edu.project1.model.Player;
import edu.project1.model.Word;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleHangmanTest {
    private static final int MAX_ATTEMPTS = 6;
    private static final char HIDDEN_SYMBOL = '.';
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Random RANDOM = new Random();
    private ConsoleHangman game;
    private Player player;
    private Word word;

    @BeforeEach
    public void setUp() {
        GameConfiguration config = new GameConfiguration(HIDDEN_SYMBOL, RANDOM);
        List<GameAction> gameActions = config.configureActions();
        word = config.configureWord();

        GameMessagesPrinter printer = new GameMessagesPrinter(LOGGER);
        game = new ConsoleHangman(gameActions, printer);
        player = new Player(MAX_ATTEMPTS);
    }

    @Test
    public void testExceedingAttemptsResultsInLoss() {
        for (int i = 0; i < player.getMaxAttempts() + 1; i++) {
            player.makeMistake();
        }
        game.start(word, player);
        assertTrue(player.hasLost());
    }
}
