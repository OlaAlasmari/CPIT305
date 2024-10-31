package pkg305;

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

    @Override
    public String toString() {
        return String.format("Name: %s, Coach: %s, Players: %d, Match Type: %s", name, coach, numberOfPlayers, matchType);
    }

}
