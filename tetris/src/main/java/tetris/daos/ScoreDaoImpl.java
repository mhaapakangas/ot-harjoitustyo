package tetris.daos;

import tetris.models.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDaoImpl implements ScoreDao {
    private DatabaseService service;

    public ScoreDaoImpl() {
        this.service = new DatabaseService();
    }

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
            e.printStackTrace();
        }
    }

    @Override
    public List<Score> getHighScores() {
        List<Score> scores = new ArrayList<>();

        try {
            Connection connection = service.createConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM SCORE ORDER BY SCORE DESC LIMIT 10");
            while ( rs.next() ) {
                String username = rs.getString("USERNAME");
                int score = rs.getInt("SCORE");
                scores.add(new Score(score, username));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scores;
    }
}
