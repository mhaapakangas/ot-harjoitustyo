package tetris.controllers;

import com.google.inject.Inject;
import tetris.daos.ScoreDao;
import tetris.models.Score;

import java.util.List;

public class ScoreService {
    private ScoreDao scoreDao;

    @Inject
    public ScoreService(ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public void saveScore(int score, String username) {
        scoreDao.saveScore(new Score(score, username));
    }

    public List<Score> getHighScores() {
        return scoreDao.getHighScores();
    }
}