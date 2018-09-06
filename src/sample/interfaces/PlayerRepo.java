package sample.interfaces;

import sample.model.Player;

import java.util.List;

public interface PlayerRepo {

    List<Player> findAll();

    Player findById(int playerId);

    Player insertAll(Player player);

    Player insertBasic(Player player);

    //Player insertCard(Player player);

    void removeById(int playerId);

    void updateBasic(Player player);

    //void updateWithSuspensionAndCards(Player player);
}
