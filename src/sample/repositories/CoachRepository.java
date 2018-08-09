package sample.repositories;

import sample.interfaces.CoachRepo;
import sample.model.Coach;

import java.util.List;

public class CoachRepository implements CoachRepo{
    @Override
    public List<Coach> findAll() {
        return null;
    }

    @Override
    public Coach findById(int coachId) {
        return null;
    }

    @Override
    public Coach insert(Coach coach) {
        return null;
    }

    @Override
    public void removeById(int coachId) {

    }

    @Override
    public void update(Coach coach) {

    }
}
