package edu.project1.game;

import org.apache.logging.log4j.Logger;

public class GameMessagesPrinter {
    public static final String CORRECT_GUESS_MESSAGE = "Correct! %s";
    public static final String INCORRECT_GUESS_MESSAGE = "Incorrect! You have %d attempts left out of %d";
    public static final String WELCOME_MESSAGE = "Welcome to Hangman!";
    public static final String WORD_LENGTH_MESSAGE = "The word contains %d letters.";
    public static final String CURRENT_WORD_STATE = "Current word: %s";
    public static final String REQUEST_INPUT = "Enter your guess (or 'exit' to quit):";
    public static final String WIN_MESSAGE = "Congratulations! You guessed the word: %s";
    public static final String LOSS_MESSAGE = "Sorry, you lost! The word was: %s";
    public static final String INVALID_INPUT = "Invalid input. Enter a single letter. Try again.";
    private final Logger logger;

    public GameMessagesPrinter(Logger logger) {
        this.logger = logger;
    }

    public void printWelcomeMessage(String initialWordState) {
        int wordLength = initialWordState.length();
        logger.info(WELCOME_MESSAGE);
        logger.info(CURRENT_WORD_STATE.formatted(initialWordState));
        logger.info(WORD_LENGTH_MESSAGE.formatted(wordLength));
    }

    public void printCorrectGuess(String currentState) {
        String current = CURRENT_WORD_STATE.formatted(currentState);
        logger.info(CORRECT_GUESS_MESSAGE.formatted(current));
    }

    public void printIncorrectGuess(int remainingAttempts, int maxMistakes) {
        logger.error(INCORRECT_GUESS_MESSAGE.formatted(remainingAttempts, maxMistakes));
    }

    public void printWinMessage(String word) {
        logger.info(WIN_MESSAGE.formatted(word));
    }

    public void printLossMessage(String word) {
        logger.warn(LOSS_MESSAGE.formatted(word));
    }

    public void printRequestInput() {
        logger.info(REQUEST_INPUT);
    }

    public void printInvalidInput() {
        logger.error(INVALID_INPUT);
    }
}
