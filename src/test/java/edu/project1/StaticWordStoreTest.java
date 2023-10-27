package edu.project1;

import edu.project1.model.Word;
import edu.project1.store.StaticWordStore;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StaticWordStoreTest {
    private final char hiddenSymbol = '.';
    private StaticWordStore store;

    @BeforeEach
    public void setUp() {
        Random seededRandom = new Random(1234567890L);
        store = new StaticWordStore(seededRandom, hiddenSymbol);
    }

    @Test
    @DisplayName("getRandomWord использует символ hiddenSymbol")
    public void getRandomWordUsesHiddenSymbol() {
        Word word = store.getRandomWord();
        assertTrue(word.getCurrentState().contains(String.valueOf(hiddenSymbol)));
    }
}
