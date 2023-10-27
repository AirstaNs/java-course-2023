package edu.project1.model;

public class Player {
    private final int maxAttempts;
    private int attempt = 0;

    public Player(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public void makeMistake() {
        attempt++;
    }

    public boolean hasLost() {
        return attempt >= maxAttempts;
    }

    public int getRemainingAttempts() {
        return maxAttempts - attempt;
    }

    public void loseGame() {
        attempt = maxAttempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}
