package tetris.controllers;

import lombok.Getter;
import lombok.Setter;
import tetris.models.Level;

public class LevelServiceImpl implements LevelService {
    @Getter
    @Setter
    private Level level = Level.EASY;
}
