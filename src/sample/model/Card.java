package sample.model;

public class Card {

    private Integer id;
    private String cardType;

    public Card(Integer id, String cardType) {
        this.id = id;
        this.cardType = cardType;
    }

    public Card() {
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
