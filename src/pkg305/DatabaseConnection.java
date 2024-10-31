package pkg305;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "SportTournament";
    private static final String USER = "root";
    private static final String PASSWORD = "olaali123";

    // Initialize the database and create the table if it doesn't exist
    static {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()) {

            // Create the database if it doesn't exist
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
            statement.executeUpdate(createDatabaseQuery);

            // Connect to the newly created or existing database
            try (Connection dbConnection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
                    Statement dbStatement = dbConnection.createStatement()) {

                // Create the Teams table if it doesn't exist
                String createTableQuery = "CREATE TABLE IF NOT EXISTS Teams ("
                        + "team_id INT AUTO_INCREMENT PRIMARY KEY, "
                        + "team_name VARCHAR(255) NOT NULL, "
                        + "coach_name VARCHAR(255) NOT NULL, "
                        + "number_of_players INT NOT NULL, "
                        + "sport_type VARCHAR(255) NOT NULL)";
                dbStatement.executeUpdate(createTableQuery);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a connection to the SportTournament database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
    }
}
