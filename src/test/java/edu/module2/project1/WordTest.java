package edu.module2.project1;

import edu.module2.project1.model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordTest {

    private static final char HIDDEN_SYMBOL = '.';
    private Word word;

    @BeforeEach
    public void setUp() {
        word = new Word("test", HIDDEN_SYMBOL);
    }

    @Test
    @DisplayName("После создания слово полностью скрыто")
    public void wordIsFullyHiddenAfterCreation() {
        assertEquals("....", word.getCurrentState());
    }

    @Test
    @DisplayName("Угадывание верной буквы открывает ее в слове")
    public void guessingCorrectLetterRevealsIt() {
        assertTrue(word.guess('t'));
        assertEquals("t..t", word.getCurrentState());
    }

    @Test
    @DisplayName("Угадывание неверной буквы не меняет слово")
    public void guessingIncorrectLetterDoesNotChangeWord() {
        assertFalse(word.guess('z'));
        assertEquals("....", word.getCurrentState());
    }

    @Test
    @DisplayName("Если все буквы угаданы, isGuessed возвращает true")
    public void isGuessedReturnsTrueIfAllLettersAreGuessed() {
        word.guess('t');
        word.guess('e');
        word.guess('s');
        assertTrue(word.isGuessed());
    }

    @Test
    @DisplayName("getActualWord возвращает правильное слово")
    public void getActualWordReturnsCorrectWord() {
        assertEquals("test", word.getActualWord());
    }

    @Test
    @DisplayName("Если не все буквы угаданы, isGuessed возвращает false")
    public void isGuessedReturnsFalseIfNotAllLettersAreGuessed() {
        word.guess('t');
        assertFalse(word.isGuessed());
    }

    @Test
    @DisplayName("Проверка невалидных слов")
    public void testInvalidWordLength() {
        assertThrows(IllegalArgumentException.class, () -> new Word("", HIDDEN_SYMBOL));
        assertThrows(IllegalArgumentException.class, () -> new Word(null, HIDDEN_SYMBOL));
    }

    @Test
    @DisplayName("Текущее состояние слова обновляется после угадывания верной буквы")
    public void testGetCurrentState() {
        assertEquals("....", word.getCurrentState());

        word.guess('t');
        assertEquals("t..t", word.getCurrentState());
    }

    @Test
    @DisplayName("Тестирование различных сценариев угадывания букв")
    public void testGuess() {
        assertTrue(word.guess('t'));
        assertEquals("t..t", word.getCurrentState());

        assertFalse(word.guess('x'));
        assertEquals("t..t", word.getCurrentState());

        assertTrue(word.guess('E'));
        assertEquals("te.t", word.getCurrentState());

        assertFalse(word.guess('t'));
        assertEquals("te.t", word.getCurrentState());
    }
}
