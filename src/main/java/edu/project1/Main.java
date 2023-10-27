package edu.project1;

import edu.project1.game.ConsoleHangman;
import edu.project1.game.GameConfiguration;
import edu.project1.game.GameMessagesPrinter;
import edu.project1.model.Player;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final int MAX_ATTEMPTS = 6;
    private static final char HIDDEN_SYMBOL = '.';
    private static final Logger LOGGER = LogManager.getLogger();

    private static final Random RANDOM = new Random();

    private static final Scanner SCANNER = new Scanner(System.in);

    private Main() {}

    public static void main(String[] args) {
        var config = new GameConfiguration(HIDDEN_SYMBOL, RANDOM, SCANNER);
        var gameActions = config.configureActions();
        var word = config.configureWord();

        var printer = new GameMessagesPrinter(LOGGER);
        var player = new Player(MAX_ATTEMPTS);
        try (var consoleHangman = new ConsoleHangman(gameActions, printer, config.getScanner())) {
            consoleHangman.start(word, player);
        }
    }
}
