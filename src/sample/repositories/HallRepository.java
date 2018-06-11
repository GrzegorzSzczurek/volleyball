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

public class HallRepository implements HallRepo{
    @Override
    public List<Hall> findAll() {
        String findAllSQL = "SELECT * FROM HALA";
        List<Hall> hallList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                String hallName = rs.getString("nazwa_hali");
                int capacity= rs.getInt("pojemnosc");
                String city= rs.getString("miasto");
                Integer postCode= rs.getInt("kod_pocztowy");
                String street= rs.getString("ulica");
                Hall hall = new Hall(hallName, capacity, city, postCode, street);
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
                int id = rs.getInt("id");
                String hallName = rs.getString("hallName");
                Integer capacity= rs.getInt("capacity");
                String city= rs.getString("city");
                Integer postCode= rs.getInt("postCode");
                String street= rs.getString("street");
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

        String autor = "DELETE FROM HALA WHERE HALA_ID= " + hallId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(autor)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Hall hall) {

        String updatePlayerSql = "UPDATE HALA SET NAZWA_HALI= ?, POJEMNOSC= ?, MIASTO= ?, KOD_POCZTOWY= ?, ULICA= ? WHERE HALA_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(updatePlayerSql)){
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
