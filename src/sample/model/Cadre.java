package sample.model;

public class Cadre {

    private Integer cadreId;
    private Player playerId;

    public Cadre() {
    }

    public Cadre(Integer cadreId, Player playerId) {
        this.cadreId = cadreId;
        this.playerId = playerId;
    }

    public Integer getCadreId() {
        return cadreId;
    }

    public void setCadreId(Integer cadreId) {
        this.cadreId = cadreId;
    }

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }
}
