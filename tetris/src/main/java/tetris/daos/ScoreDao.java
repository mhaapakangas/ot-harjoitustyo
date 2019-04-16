package tetris.daos;

import tetris.models.Score;

import java.util.List;

public interface ScoreDao {

    void saveScore(Score score);

    List<Score> getHighScores();
}
