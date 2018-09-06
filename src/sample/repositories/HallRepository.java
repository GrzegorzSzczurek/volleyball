package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.HallRepo;
import sample.model.Hall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallRepository implements HallRepo {
    @Override
    public List<Hall> findAll() {
        String findAllSQL = "SELECT * FROM HALA";
        List<Hall> hallList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("HALA_ID");
                String hallName = rs.getString("NAZWA_HALI");
                int capacity = rs.getInt("POJEMNOSC");
                String city = rs.getString("MIASTO");
                Integer postCode = rs.getInt("KOD_POCZTOWY");
                String street = rs.getString("ULICA");
                Hall hall = new Hall(id, hallName, capacity, city, postCode, street);
                hallList.add(hall);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hallList;
    }

    @Override
    public Hall findById(int hallId) {
        String findHallById = "SELECT * FROM HALA WHERE HALA_ID=" + hallId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findHallById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("HALA_ID");
                String hallName = rs.getString("NAZWA_HALI");
                Integer capacity = rs.getInt("POJEMNOSC");
                String city = rs.getString("MIASTO");
                Integer postCode = rs.getInt("KOD_POCZTOWY");
                String street = rs.getString("ULICA");
                Hall hall = new Hall(id, hallName, capacity, city, postCode, street);

                return hall;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Hall insert(Hall hall) {
        String insertTableSQL = "INSERT INTO HALA"
                + "(NAZWA_HALI, POJEMNOSC, MIASTO, KOD_POCZTOWY, ULICA) VALUES"
                + "(?,?,?,?,?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setString(1, hall.getHallName());
            preparedStatement.setInt(2, hall.getCapacity());
            preparedStatement.setString(3, hall.getCity());
            preparedStatement.setInt(4, hall.getPostCode());
            preparedStatement.setString(5, hall.getStreet());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hall;
    }

    @Override
    public void removeById(int hallId) {

        String hall = "DELETE FROM HALA WHERE HALA_ID=" + hallId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(hall)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Hall hall) {

        String updateHallSql = "UPDATE hala SET NAZWA_HALI= ?, POJEMNOSC= ?, MIASTO= ?, KOD_POCZTOWY= ?, ULICA= ? WHERE HALA_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(updateHallSql)) {
            preparedStatement.setString(1, hall.getHallName());
            preparedStatement.setInt(2, hall.getCapacity());
            preparedStatement.setString(3, hall.getCity());
            preparedStatement.setInt(4, hall.getPostCode());
            preparedStatement.setString(5, hall.getStreet());
            preparedStatement.setInt(6, hall.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
