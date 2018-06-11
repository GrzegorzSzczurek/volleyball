package sample.interfaces;

import sample.model.Coach;

import java.util.List;

public interface CoachRepo {

    List<Coach> findAll();

    Coach findById(int coachId);

    Coach insert(Coach coach);

    void removeById(int coachId);

    void update(Coach coach);
}
