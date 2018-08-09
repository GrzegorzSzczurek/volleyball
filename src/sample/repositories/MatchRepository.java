package sample.repositories;

import sample.interfaces.MatchRepo;
import sample.model.Match;

import java.util.List;

public class MatchRepository implements MatchRepo{
    @Override
    public List<Match> findAll() {
        return null;
    }

    @Override
    public Match findById(int matchId) {
        return null;
    }

    @Override
    public Match insert(Match match) {
        return null;
    }

    @Override
    public void removeById(int matchId) {

    }

    @Override
    public void update(Match match) {

    }
}
