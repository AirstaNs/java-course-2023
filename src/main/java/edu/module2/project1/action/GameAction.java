package edu.module2.project1.action;

import edu.module2.project1.game.ConsoleHangman;

public interface GameAction {
    boolean canExecute(String input);

    void execute(ConsoleHangman game, String input);
}
