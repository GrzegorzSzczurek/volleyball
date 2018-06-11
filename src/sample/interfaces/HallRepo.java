package sample.interfaces;

import sample.model.Hall;

import java.util.List;

public interface HallRepo {

    List<Hall> findAll();

    Hall findById(int hallId);

    Hall insert(Hall hall);

    void removeById(int hallId);

    void update(Hall hall);
}
