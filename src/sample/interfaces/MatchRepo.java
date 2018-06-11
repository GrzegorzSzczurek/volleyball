package sample.interfaces;

import sample.model.Match;

import java.util.List;

public interface MatchRepo {

    List<Match> findAll();

    Match findById(int matchId);

    Match insert(Match match);

    void removeById(int matchId);

    void update(Match match);
}
