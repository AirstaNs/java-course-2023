package edu.module2.project1.game;

import edu.module2.project1.action.GameAction;
import edu.module2.project1.action.GuessLetterAction;
import edu.module2.project1.action.ExitAction;
import edu.module2.project1.model.Word;
import edu.module2.project1.store.StaticWordStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Класс для упрощения конфигурации игры,
// включая создание списка действий и выбор случайного слова для угадывания
public class GameConfiguration {
    private final char hiddenSymbol;

    private final Random random;

    private final Scanner scanner;

    public GameConfiguration(char hiddenSymbol, Random random, Scanner scanner) {
        this.hiddenSymbol = hiddenSymbol;
        this.random = random;
        this.scanner = scanner;
    }

    public List<GameAction> configureActions() {
        final String exitCommand = "exit";
        List<GameAction> actions = new ArrayList<>();
        actions.add(new GuessLetterAction());
        actions.add(new ExitAction(exitCommand));
        return actions;
    }

    public Word configureWord() {
        var repository = new StaticWordStore(random, hiddenSymbol);
        return repository.getRandomWord();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
