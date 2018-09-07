package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.SuspensionRepo;
import sample.model.Player;
import sample.model.Suspension;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuspensionRepository implements SuspensionRepo {

    @Override
    public List<Suspension> findAll() {
        String findAllSQL = "SELECT * FROM ZAWIESZENIA";
        List<Suspension> suspensionList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ZAWIESZENIE_ID");
                int playerId = rs.getInt("ZAWODNIK_ID");
                Date startDate = rs.getDate("DATA_ROZPOCZECIA");
                Date endDate = rs.getDate("DATA_ZAKONCZENIA");

                PlayerRepository playerRepository = new PlayerRepository();
                Player player = playerRepository.findById(playerId);
                Suspension suspension = new Suspension(id, startDate, endDate, player);

                suspensionList.add(suspension);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return suspensionList;
    }

    @Override
    public Suspension findById(int suspensionId) {
        String findBookById = "SELECT * FROM ZAWIESZENIA WHERE ZAWIESZENIE_ID=" + suspensionId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findBookById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ZAWIESZENIE_ID");
                Date startDate = rs.getDate("DATA_ROZPOCZECIA");
                Date endDate = rs.getDate("DATA_ZAKONCZENIA");
                Suspension suspension = new Suspension(id, startDate, endDate);
                return suspension;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Suspension insert(Suspension suspension) {
        String insertTableSQL = "INSERT INTO ZAWIESZENIE"
                + "(ZAWIESZENIE_ID, DATA_ROZPOCZECIA, DATA_ZAKONCZENIA) VALUES"
                + "(?,?,?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, suspension.getId());
            preparedStatement.setDate(2, (Date) suspension.getStart());
            preparedStatement.setDate(3, (Date) suspension.getEnd());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return suspension;
    }

    @Override
    public void removeById(int suspensionId) {
        String wypozyczenie = "DELETE FROM ZAWIESZENIA WHERE ZAWIESZENIE_ID= " + suspensionId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(wypozyczenie)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Suspension suspension) {
        String suspensionUpdate = "UPDATE ZAWIESZENIE SET DATA_ROZPOCZECIA= ? , DATA_ZAKONCZENIA= ?  WHERE ZAWIESZENIE_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(suspensionUpdate)) {
            preparedStatement.setDate(1, (Date) suspension.getStart());
            preparedStatement.setDate(2, (Date) suspension.getEnd());
            preparedStatement.setInt(3, suspension.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
