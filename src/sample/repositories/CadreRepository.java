package sample.repositories;

import sample.interfaces.CadreRepo;
import sample.model.Cadre;

import java.util.List;

public class CadreRepository implements CadreRepo{
    @Override
    public List<Cadre> findAll() {
        return null;
    }

    @Override
    public Cadre findById(int cadreId) {
        return null;
    }

    @Override
    public Cadre insert(Cadre cadre) {
        return null;
    }

    @Override
    public void removeById(int cadreId) {

    }

    @Override
    public void update(Cadre cadre) {

    }
}
