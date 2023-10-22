package edu.project1.game;

import org.apache.logging.log4j.Logger;

public class GameMessagesPrinter {
    public static final String CORRECT_GUESS_MESSAGE = "Correct! Current word: %s";
    public static final String INCORRECT_GUESS_MESSAGE = "Incorrect! You have %d attempts left out of %d";
    public static final String WELCOME_MESSAGE = "Welcome to Hangman!";
    public static final String REQUEST_INPUT = "Enter your guess (or 'exit' to quit):";
    public static final String WIN_MESSAGE = "Congratulations! You guessed the word: %s";
    public static final String LOSS_MESSAGE = "Sorry, you lost! The word was: %s";
    public static final String INVALID_INPUT = "Invalid input. Try again.";
    private final Logger logger;

    public GameMessagesPrinter(Logger logger) {
        this.logger = logger;
    }

    public void printWelcomeMessage() {
        logger.info(WELCOME_MESSAGE);
    }

    public void printCorrectGuess(String currentState) {
        logger.info(CORRECT_GUESS_MESSAGE.formatted(currentState));
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
