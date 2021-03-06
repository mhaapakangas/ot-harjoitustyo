package tetris.controllers;

import com.google.inject.Inject;
import tetris.daos.ScoreDao;
import tetris.models.Score;

import java.util.List;

/**
 * This class saves and fetches scores.
 */
public class ScoreService {
    private ScoreDao scoreDao;
    private static final int USERNAME_LIMIT = 15;

    @Inject
    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    /**
     * Saves a new score.
     * @param score scored points
     * @param username username of the player
     */
    public void saveScore(int score, String username) {
        username = username.substring(0, Math.min(USERNAME_LIMIT, username.length()));
        scoreDao.saveScore(new Score(score, username));
    }

    /**
     * Retrieves high scores.
     * @return a list of high scores.
     */
    public List<Score> getHighScores() {
        return scoreDao.getHighScores();
    }
}