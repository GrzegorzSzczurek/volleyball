package sample.interfaces;

import sample.model.League;

import java.util.List;

public interface LeagueRepo {

    List<League> findAll();

    League findById(int leagueId);

    League insert(League league);

    void removeById(int leagueId);

    void update(League league);
}
