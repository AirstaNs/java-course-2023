package edu.project1;

import edu.project1.action.ExitAction;
import edu.project1.action.GuessLetterAction;
import edu.project1.game.GameConfiguration;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameConfigurationTest {
    private GameConfiguration configuration;

    @BeforeEach
    public void setUp() {
        configuration = new GameConfiguration('_', new Random());
    }

    @Test
    public void testConfigureActions() {
        var actions = configuration.configureActions();

        assertEquals(2, actions.size());
        assertTrue(actions.get(0) instanceof GuessLetterAction);
        assertTrue(actions.get(1) instanceof ExitAction);
    }

    @Test
    public void testConfigureWord() {
        var word = configuration.configureWord();

        assertNotNull(word);
    }
}
