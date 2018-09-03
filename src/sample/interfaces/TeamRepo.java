package sample.interfaces;

import sample.model.Team;

import java.util.List;

public interface TeamRepo {

    List<Team> findAll();

    Team insert(Team team);

    Team remove(Team team);
}
