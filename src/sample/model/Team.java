package sample.model;

public class Team {

    private Cadre cadreId;
    private Player playerId;

    public Team(Cadre cadreId, Player playerId) {
        this.cadreId = cadreId;
        this.playerId = playerId;
    }

    public Team() {
    }

    public Cadre getCadreId() {
        return cadreId;
    }

    public void setCadreId(Cadre cadreId) {
        this.cadreId = cadreId;
    }

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }
}
