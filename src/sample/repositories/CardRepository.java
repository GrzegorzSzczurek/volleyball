package sample.repositories;

import sample.interfaces.CardRepo;
import sample.model.Card;

import java.util.List;

public class CardRepository implements CardRepo{
    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public Card findById(int cardId) {
        return null;
    }

    @Override
    public Card insert(Card card) {
        return null;
    }

    @Override
    public void removeById(int cardId) {

    }

    @Override
    public void update(Card card) {

    }
}
