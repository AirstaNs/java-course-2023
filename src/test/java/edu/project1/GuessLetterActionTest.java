package edu.project1;

import edu.project1.action.GuessLetterAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuessLetterActionTest {

    private GuessLetterAction guessLetterAction;

    @BeforeEach
    public void setup() {
        guessLetterAction = new GuessLetterAction();
    }

    @Test
    @DisplayName("canExecute должен возвращать true для одной буквы")
    public void canExecuteReturnsTrueForSingleLetter() {
        assertTrue(guessLetterAction.canExecute("a"));
    }

    @Test
    @DisplayName("canExecute должен возвращать false для строки длиннее одного символа")
    public void canExecuteReturnsFalseForMultipleCharacters() {
        assertFalse(guessLetterAction.canExecute("ab"));
    }

    @Test
    @DisplayName("canExecute должен возвращать false для не-буквенных символов")
    public void canExecuteReturnsFalseForNonLetter() {
        assertFalse(guessLetterAction.canExecute("1"));
    }

}
