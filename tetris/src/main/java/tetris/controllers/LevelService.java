package tetris.controllers;

import tetris.models.Level;

public interface LevelService {

    Level getLevel();

    void setLevel(Level level);
}
