package tetris.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class creates an h2 database for storing game scores,
 * and handles connections to it.
 */
public class DatabaseService {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./tetris";
    private static final String USER = "sa";
    private static final String PASS = "sa";

    /**
     * Initializes the database. Creates a database table for scores
     * if it doesn't exist yet.
     *
     * @throws SQLException if creating a connection or table fails
     */
    public void init() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);

        Connection conn = createConnection();
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS SCORE" +
            " (ID INT PRIMARY KEY AUTO_INCREMENT," +
            " SCORE INT," +
            " USERNAME VARCHAR(50) NOT NULL);");

        statement.close();
        conn.close();
    }

    /**
     * Creates a connection to the database.
     *
     * @return the new connection.
     * @throws SQLException if creating a connection fails
     */
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
