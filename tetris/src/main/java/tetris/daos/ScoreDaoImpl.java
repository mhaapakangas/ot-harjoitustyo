package tetris.daos;

import com.google.inject.Inject;
import tetris.models.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class is responsible for reading and writing scores to database.
 */
public class ScoreDaoImpl implements ScoreDao {
    private static final Logger LOGGER = Logger.getLogger(ScoreDaoImpl.class.getName());
    private DatabaseService service;

    @Inject
    public ScoreDaoImpl(DatabaseService service) {
        this.service = service;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void saveScore(Score score) {
        try {
            Connection connection = service.createConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO SCORE (SCORE, USERNAME) VALUES (?, ?);");
            statement.setInt(1, score.getScore());
            statement.setString(2, score.getUsername());
            statement.execute();

            connection.close();
        } catch (SQLException e) {
            LOGGER.warning("Error saving score to database.");
        }
    }

    /**
     * Retrieves 10 highest scores in descending order from database.
     * @return the high score list.
     */
    @Override
    public List<Score> getHighScores() {
        List<Score> scores = new ArrayList<>();

        try {
            Connection connection = service.createConnection();
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE ORDER BY SCORE DESC LIMIT 10");
            while (rs.next()) {
                String username = rs.getString("USERNAME");
                int score = rs.getInt("SCORE");
                scores.add(new Score(score, username));
            }

            connection.close();
        } catch (SQLException e) {
            LOGGER.warning("Error reading scores from database.");
        }

        return scores;
    }
}
