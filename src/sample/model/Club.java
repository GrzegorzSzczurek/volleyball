package sample.model;

public class Club {

    private Integer id;
    private Hall hallId;
    private String country;
    private League leagueId;
    private Coach coachId;
    private String clubName;

    public Club(Integer id, Hall hallId, String country, League leagueId, Coach coachId, String clubName) {
        this.id = id;
        this.hallId = hallId;
        this.country = country;
        this.leagueId = leagueId;
        this.coachId = coachId;
        this.clubName = clubName;
    }

    public Club() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hall getHallId() {
        return hallId;
    }

    public void setHallId(Hall hallId) {
        this.hallId = hallId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public League getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(League leagueId) {
        this.leagueId = leagueId;
    }

    public Coach getCoachId() {
        return coachId;
    }

    public void setCoachId(Coach coachId) {
        this.coachId = coachId;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
