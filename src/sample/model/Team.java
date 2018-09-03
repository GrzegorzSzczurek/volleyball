package sample.model;

public class Team {

    private Club clubId;
    private Player playerId;

    public Team(Club clubId, Player playerId) {
        this.clubId = clubId;
        this.playerId = playerId;
    }

    public Team() {
    }

    public Club getClubId() {
        return clubId;
    }

    public void setClubId(Club clubId) {
        this.clubId = clubId;
    }

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }
}
