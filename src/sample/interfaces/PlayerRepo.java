package sample.interfaces;

import sample.model.Player;

import java.util.List;

public interface PlayerRepo {

    List<Player> findAll();

    Player findById(int playerId);

    Player insertAll(Player player);

    Player insertBasic(Player player);

    void removeById(int playerId);

    void update(Player player);
}
