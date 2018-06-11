package sample.interfaces;

import sample.model.Card;

import java.util.List;

public interface CardRepo {

    List<Card> findAll();

    Card findById(int cardId);

    Card insert(Card card);

    void removeById(int cardId);

    void update(Card card);
}
