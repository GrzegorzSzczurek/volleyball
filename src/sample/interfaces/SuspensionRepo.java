package sample.interfaces;

import sample.model.Suspension;

import java.util.List;

public interface SuspensionRepo {

    List<Suspension> findAll();

    Suspension findById(int suspensionId);

    Suspension insert(Suspension suspension);

    void removeById(int suspensionId);

    void update(Suspension suspension);
}
