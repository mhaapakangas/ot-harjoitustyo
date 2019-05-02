package tetris.models;

import lombok.Getter;

public enum Level {
    EASY(0),
    MEDIUM(5),
    HARD(10);

    @Getter
    private final int level;

    Level(int level) {
        this.level = level;
    }
}
