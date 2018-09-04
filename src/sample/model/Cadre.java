package sample.model;

import java.util.List;

public class Cadre {

    private Integer cadreId;
    private Match matchId;
    private Club clubId;
    private List<Player> playerList;

    public Cadre() {
    }

    public Cadre(Integer cadreId, Match matchId, Club clubId) {
        this.cadreId = cadreId;
        this.matchId = matchId;
        this.clubId = clubId;
    }

    public Cadre(Club clubId) {
        this.clubId = clubId;
    }

    public Cadre(Match matchId, Club clubId) {
        this.matchId = matchId;
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

    public void setMatchId(Match matchId) {
        this.matchId = matchId;
    }

    public Club getClubId() {
        return clubId;
    }

    public void setClubId(Club clubId) {
        this.clubId = clubId;
    }
}
