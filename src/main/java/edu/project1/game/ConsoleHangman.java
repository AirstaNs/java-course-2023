package edu.project1.game;

import edu.project1.action.GameAction;
import edu.project1.model.Player;
import edu.project1.model.Word;
import java.util.List;
import java.util.Scanner;

public class ConsoleHangman implements AutoCloseable {
    private final List<GameAction> actions;
    private final GameMessagesPrinter printer;
    private final Scanner scanner;
    private Word word;
    private Player player;

    public ConsoleHangman(List<GameAction> actions, GameMessagesPrinter printer, Scanner scanner) {
        this.actions = actions;
        this.printer = printer;
        this.scanner = scanner;
    }

    public void start(Word word, Player player) {
        this.word = word;
        this.player = player;

        printer.printWelcomeMessage();

        while (!word.isGuessed() && !player.hasLost()) {
            String input = getUserInput();
            handleInput(input);
        }

        String actualWord = word.getActualWord();
        if (word.isGuessed()) {
            printer.printWinMessage(actualWord);
        } else {
            printer.printLossMessage(actualWord);
        }
    }

    private String getUserInput() {
        printer.printRequestInput();
        return scanner.nextLine().trim();
    }

    // Метод для обработки ввода от пользователя
    private void handleInput(String input) {
        // Проверка, может ли действие быть выполнено с данным вводом
        for (GameAction action : actions) {
            if (action.canExecute(input)) {
                action.execute(this, input);
                return;
            }
        }
        printer.printInvalidInput();
    }

    public void guessLetter(char letter) {
        if (word.guess(letter)) {
            printer.printCorrectGuess(word.getCurrentState());
        } else {
            player.makeMistake();
            printer.printIncorrectGuess(player.getRemainingAttempts(), player.getMaxAttempts());
        }
    }

    public void exit() {
        player.loseGame(); // назначение 0 действующих попыток
    }

    @Override
    public void close() {
        scanner.close();
    }
}
