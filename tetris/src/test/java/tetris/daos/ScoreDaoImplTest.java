package tetris.daos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tetris.models.Score;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ScoreDaoImplTest {
    private DatabaseService mockedDatabaseService;
    private Connection mockedConnection;
    private PreparedStatement mockedPreparedStatement;
    private Statement mockedStatement;
    private ResultSet mockedResultSet;
    private ScoreDao scoreDao;

    @Before
    public void setUp() {
        mockedDatabaseService = mock(DatabaseService.class);
        mockedConnection = mock(Connection.class);
        mockedPreparedStatement = mock(PreparedStatement.class);
        mockedStatement = mock(Statement.class);
        mockedResultSet = mock(ResultSet.class);
        scoreDao = new ScoreDaoImpl(mockedDatabaseService);
    }

    @Test
    public void saveScoreToDatabase() throws SQLException {
        int score = 9999;
        String username = "Bob";

        when(mockedDatabaseService.createConnection()).thenReturn(mockedConnection);
        when(mockedConnection.prepareStatement("INSERT INTO SCORE (SCORE, USERNAME) VALUES (?, ?);"))
            .thenReturn(mockedPreparedStatement);

        scoreDao.saveScore(new Score(score, username));

        verify(mockedPreparedStatement).setInt(1, score);
        verify(mockedPreparedStatement).setString(2, username);
        verify(mockedPreparedStatement).execute();
        verify(mockedConnection).close();
    }

    @Test
    public void getHighScoresFromDatabase() throws SQLException {
        int score = 9999;
        String username = "Bob";
        int score2 = 980;
        String username2 = "Barbara";
        List<Score> expectedScores = Arrays.asList(new Score(score, username),
            new Score(score2, username2));

        when(mockedDatabaseService.createConnection()).thenReturn(mockedConnection);
        when(mockedConnection.createStatement()).thenReturn(mockedStatement);
        when(mockedStatement.executeQuery("SELECT * FROM SCORE ORDER BY SCORE DESC LIMIT 10"))
            .thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true, true, false);
        when(mockedResultSet.getString("USERNAME")).thenReturn(username, username2);
        when(mockedResultSet.getInt("SCORE")).thenReturn(score, score2);

        List<Score> scores = scoreDao.getHighScores();

        verify(mockedResultSet, times(3)).next();
        verify(mockedConnection).close();

        Assert.assertEquals(expectedScores, scores);

    }
}
