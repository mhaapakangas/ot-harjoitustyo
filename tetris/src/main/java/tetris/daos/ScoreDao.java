package tetris.daos;

import tetris.models.Score;

import java.util.List;

/**
 * Interface for reading and writing scores to database.
 */
public interface ScoreDao {

    /**
     * Saves a new score to database.
     * @param score new {@link Score}
     */
    void saveScore(Score score);

    /**
     * Retrieves high scores from database.
     * @return the high score list.
     */
    List<Score> getHighScores();
}
