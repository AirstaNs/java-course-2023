package edu.project1.store;

import edu.project1.model.Word;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StaticWordStore implements WordStore {
    private static final List<String> DICTIONARY = Arrays.asList("java",
        "golang",
        "python",
        "ruby",
        "javaScript",
        "php"
    );
    private final Random randomizer;
    private final char hiddenSymbol;

    public StaticWordStore(Random randomizer, char hiddenSymbol) {
        this.randomizer = randomizer;
        this.hiddenSymbol = hiddenSymbol;
    }

    @Override
    public Word getRandomWord() {
        int index = randomizer.nextInt(DICTIONARY.size());
        return new Word(DICTIONARY.get(index), hiddenSymbol);
    }
}
