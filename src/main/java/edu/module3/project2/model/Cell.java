package edu.module3.project2.model;

import java.awt.Point;

public record Cell(Type type, Point position) {

    public boolean isPassage() {
        return this.type == Type.PASSAGE;
    }

    public boolean isWall() {
        return this.type == Type.WALL;
    }

    public enum Type {
        WALL, PASSAGE
    }
}

