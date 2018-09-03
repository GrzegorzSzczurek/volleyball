package sample.interfaces;

import sample.model.CadrePlayer;

import java.util.List;

public interface CardePlayersRepo {

    List<CadrePlayer> findAll();

    CadrePlayer insert(CadrePlayer cadrePlayer);

    /*CadrePlayer findById(int cadrePlayerId);

    void removeById(int cadrePlayerIdId);

    void update(CadrePlayer cadrePlayer);*/
}
