package edu.module2.project1.action;

import edu.module2.project1.game.ConsoleHangman;

public class GuessLetterAction implements GameAction {
    @Override
    public boolean canExecute(String input) {
        // строка должна состоять из 1-го символа и является буквой
        return input.length() == 1 && Character.isLetter(input.charAt(0));
    }

    @Override
    public void execute(ConsoleHangman game, String input) {
        game.guessLetter(input.charAt(0));
    }
}
