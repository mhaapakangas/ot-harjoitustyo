package tetris.models;

import lombok.Getter;

/**
 * Difficulty level of the game
 */
public enum DifficultyLevel {
    /**
     * Easy difficulty level. Game starts from level 0.
     */
    EASY(0),
    /**
     * Medium difficulty level. Game starts from level 5.
     */
    MEDIUM(5),
    /**
     * Hard difficulty level. Game starts from level 10.
     */
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
