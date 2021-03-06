package sample.model;

public class Cadre {

    private Integer cadreId;
    private Match matchId;
    private Club clubId;

    public Cadre() {
    }

    public Cadre(Integer cadreId, Match matchId, Club clubId) {
        this.cadreId = cadreId;
        this.matchId = matchId;
        this.clubId = clubId;
    }

    public Cadre(Integer cadreId, Club clubId) {
        this.cadreId = cadreId;
        this.clubId = clubId;
    }

    public Cadre(Club clubId) {
        this.clubId = clubId;
    }

    public Cadre(Match matchId, Club clubId) {
        this.matchId = matchId;
        this.clubId = clubId;
    }

    public void setMatchId(Match matchId) {
        this.matchId = matchId;
    }

    public void setClubId(Club clubId) {
        this.clubId = clubId;
    }

    public Integer getCadreId() {
        return cadreId;
    }

    public void setCadreId(Integer cadreId) {
        this.cadreId = cadreId;
    }

    public Match getMatchId() {
        return matchId;
    }

    public Club getClubId() {
        return clubId;
    }
}
