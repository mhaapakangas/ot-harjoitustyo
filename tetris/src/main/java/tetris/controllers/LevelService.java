package tetris.controllers;

import tetris.models.DifficultyLevel;

/**
 * Interface for handling the game difficulty level.
 */
public interface LevelService {

    DifficultyLevel getLevel();

    void setLevel(DifficultyLevel level);
}
