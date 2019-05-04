package tetris.controllers;

import lombok.Getter;
import lombok.Setter;
import tetris.models.DifficultyLevel;

/**
 * Class for handling the game difficulty level.
 */
public class LevelServiceImpl implements LevelService {

    @Getter
    @Setter
    private DifficultyLevel level = DifficultyLevel.EASY;
}
