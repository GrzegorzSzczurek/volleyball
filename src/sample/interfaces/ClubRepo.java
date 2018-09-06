package sample.interfaces;

import sample.model.Club;

import java.util.List;

public interface ClubRepo {
    List<Club> findAll();

    Club findById(int clubId);

    Club insert(Club club);

    void removeById(int clubId);

    void update(Club club);
}
