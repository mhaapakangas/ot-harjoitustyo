package tetris.controllers;

import tetris.daos.ScoreDao;
import tetris.daos.ScoreDaoImpl;
import tetris.models.Score;

import java.util.List;

public class ScoreService {
    private ScoreDao scoreDao;

    public ScoreService() {
        this.scoreDao = new ScoreDaoImpl();
    }

    public void saveScore(int score, String username) {
        scoreDao.saveScore(new Score(score, username));
    }

    public List<Score> getHighScores() {
        return scoreDao.getHighScores();
    }
}
