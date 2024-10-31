package pkg305;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Team {

    private String name;
    private String coach;
    private int numberOfPlayers;
    private String matchType;

    public Team(String name, String coach, int numberOfPlayers, String matchType) {
        this.name = name;
        this.coach = coach;
        this.numberOfPlayers = numberOfPlayers;
        this.matchType = matchType;
    }

    public void addTeamToDatabase() {
        String query = "INSERT INTO Teams (team_name, coach_name, number_of_players, sport_type) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, coach);
            statement.setInt(3, numberOfPlayers);
            statement.setString(4, matchType);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Team added successfully!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Coach: %s, Players: %d, Match Type: %s", name, coach, numberOfPlayers, matchType);
    }

}
