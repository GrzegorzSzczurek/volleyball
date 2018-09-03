package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.CardePlayersRepo;
import sample.model.Cadre;
import sample.model.CadrePlayer;
import sample.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadrePlayerRepository implements CardePlayersRepo {
    @Override
    public List<CadrePlayer> findAll() {
        String findAllSQL = "SELECT * FROM ZAWODNICY_KADRY";
        List<CadrePlayer> cadrePlayersList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cadreId = rs.getInt("KADRA_ID");
                int playerId = rs.getInt("ZAWODNIK_ID");

                CadreRepository cadreRepository = new CadreRepository();
                Cadre cadreById = cadreRepository.findById(cadreId);

                PlayerRepository playerRepository = new PlayerRepository();
                Player playerById = playerRepository.findById(playerId);


                CadrePlayer cadrePlayer = new CadrePlayer(cadreById, playerById);

                cadrePlayersList.add(cadrePlayer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cadrePlayersList;
    }


    @Override
    public CadrePlayer insert(CadrePlayer cadrePlayer) {
        String insertTableSQL = "INSERT INTO ZAWODNICY_KADRY"
                + "(KADRA_ID, ZAWODNIK_ID) VALUES"
                + "(?,?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, cadrePlayer.getCadreId().getCadreId());
            preparedStatement.setInt(2, cadrePlayer.getPlayerId().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cadrePlayer;
    }

    /*@Override
    public void removeById(int cadrePlayerIdId) {

    }

    @Override
    public void update(CadrePlayer cadrePlayer) {

    }*/
}
