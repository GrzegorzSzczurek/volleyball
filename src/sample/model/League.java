package sample.model;

public class League {

    private Integer id;
    private String leagueName;
    private String leagueLevel;
    private int numberOfClubs;
    private int numberOfMatches;
    private int year;

    public League(Integer id, String leagueName, String leagueLevel, int numberOfClubs, int numberOfMatches, int year) {
        this.id = id;
        this.leagueName = leagueName;
        this.leagueLevel = leagueLevel;
        this.numberOfClubs = numberOfClubs;
        this.numberOfMatches = numberOfMatches;
        this.year = year;
    }

    public League(String leagueName, String leagueLevel, int numberOfClubs, int numberOfMatches, int year) {
        this.leagueName = leagueName;
        this.leagueLevel = leagueLevel;
        this.numberOfClubs = numberOfClubs;
        this.numberOfMatches = numberOfMatches;
        this.year = year;
    }

    public League() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueLevel() {
        return leagueLevel;
    }

    public void setLeagueLevel(String leagueLevel) {
        this.leagueLevel = leagueLevel;
    }

    public int getNumberOfClubs() {
        return numberOfClubs;
    }

    public void setNumberOfClubs(int numberOfClubs) {
        this.numberOfClubs = numberOfClubs;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public void setNumberOfMatches(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
