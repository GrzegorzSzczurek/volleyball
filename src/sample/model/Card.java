package sample.model;

public class Card {

    private Integer id;
    private String cardType;
    private Player player;

    public Card(Integer id, String cardType) {
        this.id = id;
        this.cardType = cardType;
    }

    public Card(String cardType, Player player) {
        this.cardType = cardType;
        this.player = player;
    }

    public Card(Integer id, String cardType, Player player) {
        this.id = id;
        this.cardType = cardType;
        this.player = player;
    }

    public Card(String cardType) {
        this.cardType = cardType;
    }

    public Card() {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
