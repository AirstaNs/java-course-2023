package edu.module2.project1.action;

import edu.module2.project1.game.ConsoleHangman;

public class ExitAction implements GameAction {
    private final String command;

    public ExitAction(String command) {
        this.command = command;
    }

    @Override
    public boolean canExecute(String input) {
        return command.equalsIgnoreCase(input);
    }

    @Override
    public void execute(ConsoleHangman game, String input) {
        game.exit();
    }
}
