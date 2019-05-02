package tetris.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tetris.daos.ScoreDao;
import tetris.models.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScoreServiceTest {
    private ScoreService scoreService;
    private ScoreDao mockedScoreDao;

    @Before
    public void setUp() {
        mockedScoreDao = mock(ScoreDao.class);
        scoreService = new ScoreService(mockedScoreDao);
    }

    @Test
    public void saveScoreToDatabase() {
        int score = 9999;
        String username = "Bob";

        scoreService.saveScore(score, username);

        Score expected = new Score(score, username);
        verify(mockedScoreDao).saveScore(expected);
    }

    @Test
    public void getHighScoresFromDatabase() {
        List<Score> scores = Arrays.asList(new Score(9999, "Bob"), new Score(9998, "Bab"));

        when(mockedScoreDao.getHighScores()).thenReturn(scores);

        Assert.assertEquals(scores, scoreService.getHighScores());
    }
}
