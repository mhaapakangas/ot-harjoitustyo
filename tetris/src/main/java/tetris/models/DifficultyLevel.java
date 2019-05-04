package tetris.models;

import lombok.Getter;

/**
 * Difficulty level of the game
 */
public enum DifficultyLevel {
    EASY(0),
    MEDIUM(5),
    HARD(10);

    /**
     * Game level corresponding to the difficulty level
     */
    @Getter
    private final int level;

    DifficultyLevel(int level) {
        this.level = level;
    }
}
