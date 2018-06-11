package sample.model;

public class Club {

    private Integer id;
    private Hall hallId;
    private String country;
    private League leagueId;
    private Coach coachId;
    private String clubname;

    public Club(Integer id, Hall hallId, String country, League leagueId, Coach coachId, String clubname) {
        this.id = id;
        this.hallId = hallId;
        this.country = country;
        this.leagueId = leagueId;
        this.coachId = coachId;
        this.clubname = clubname;
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

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }
}
