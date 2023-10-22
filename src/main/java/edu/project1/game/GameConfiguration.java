package edu.project1.game;

import edu.project1.action.ExitAction;
import edu.project1.action.GameAction;
import edu.project1.action.GuessLetterAction;
import edu.project1.model.Word;
import edu.project1.store.StaticWordStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Класс для упрощения конфигурации игры,
// включая создание списка действий и выбор случайного слова для угадывания
public class GameConfiguration {
    private final char hiddenSymbol;

    private final Random random;

    public GameConfiguration(char hiddenSymbol, Random random) {
        this.hiddenSymbol = hiddenSymbol;
        this.random = random;
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
}
