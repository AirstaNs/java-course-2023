package edu.project1.model;

import java.util.Arrays;

public class Word {
    private final char[] actualWord;
    private final char[] representation;
    private final char hiddenSymbol;

    public Word(String actualWord, char hiddenSymbol) {
        this.checkLengthWord(actualWord);
        this.actualWord = actualWord.toLowerCase().toCharArray();
        this.hiddenSymbol = hiddenSymbol;
        this.representation = new char[actualWord.length()];
        Arrays.fill(representation, hiddenSymbol);
    }

    private void checkLengthWord(String actualWord) {
        if (actualWord == null || actualWord.isEmpty()) {
            throw new IllegalArgumentException("word must not be empty");
        }
    }

    public boolean guess(Character letter) {
        char lowerLetter = Character.toLowerCase(letter);

        boolean isLetterPresent = false;
        for (int i = 0; i < actualWord.length; i++) {
            if (actualWord[i] == lowerLetter && representation[i] == hiddenSymbol) {
                representation[i] = lowerLetter;
                isLetterPresent = true;
            }
        }
        return isLetterPresent;
    }

    public boolean isGuessed() {
        for (char c : representation) {
            if (c == hiddenSymbol) {
                return false; // Есть ещё не угаданные символы
            }
        }
        return true; // Все символы угаданы
    }

    public String getActualWord() {
        return String.valueOf(actualWord);
    }

    public String getCurrentState() {
        return String.valueOf(representation);
    }
}
