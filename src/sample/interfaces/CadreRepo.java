package sample.interfaces;

import sample.model.Cadre;

import java.util.List;

public interface CadreRepo {
    List<Cadre> findAll();

    Cadre findById(int cadreId);

    Cadre insert(Cadre cadre);

    void removeById(int cadreId);

    void update(Cadre cadre);
}
