package sample.model;

public class Cadre {

    private Integer cadreId;
    private Match matchId;
    private Player playerId;
    private Club clubId;

    public Cadre(Integer cadreId, Match matchId, Player playerId, Club clubId) {
        this.cadreId = cadreId;
        this.matchId = matchId;
        this.playerId = playerId;
        this.clubId = clubId;
    }

    public Cadre() {
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

    public void setMatchId(Match matchId) {
        this.matchId = matchId;
    }

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }

    public Club getClubId() {
        return clubId;
    }

    public void setClubId(Club clubId) {
        this.clubId = clubId;
    }
}
