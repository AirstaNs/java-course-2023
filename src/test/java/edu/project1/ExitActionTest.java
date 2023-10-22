package edu.project1;

import edu.project1.action.ExitAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExitActionTest {

    private ExitAction exitAction;

    @BeforeEach
    public void setup() {
        exitAction = new ExitAction("exit");
    }

    @Test
    @DisplayName("canExecute должен возвращать true для корректной команды выхода")
    public void canExecuteReturnsTrueForCorrectCommand() {
        // Given
        String input = "exit";

        // When
        boolean result = exitAction.canExecute(input);

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("canExecute должен возвращать false для некорректной команды")
    public void canExecuteReturnsFalseForIncorrectCommand() {
        // Given
        String input = "notExit";

        // When
        boolean result = exitAction.canExecute(input);

        // Then
        assertFalse(result);
    }
}
