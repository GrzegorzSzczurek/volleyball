package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.CoachRepo;
import sample.model.Club;
import sample.model.Coach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoachRepository implements CoachRepo{

    @Override
    public List<Coach> findAll() {
        String findAllSQL = "SELECT * FROM TRENER";
        List<Coach> coachList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int coachId = rs.getInt("TRENER_ID");
                String name = rs.getString("IMIE");
                String surname = rs.getString("NAZWISKO");
                Date birthday= rs.getDate("DATA_URODZENIA");
                String nationality = rs.getString("NARODOWOSC");

                Coach coach= new Coach(coachId, name, surname, birthday, nationality);

                coachList.add(coach);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coachList;
    }

    @Override
    public Coach findById(int coachId) {
        String findCoachById = "SELECT * FROM KLUB WHERE KLUB_ID=" + coachId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findCoachById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("TRENER_ID");
                String name = rs.getString("IMIE");
                String surname = rs.getString("NAZWISKO");
                Date birthday= rs.getDate("DATA_URODZENIA");
                String nationality = rs.getString("NARODOWOSC");

                return new Coach(id, name, surname, birthday, nationality);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Coach insert(Coach coach) {
        String insertTableSQL = "INSERT INTO TRENER"
                + "(IMIE, NAZWISKO, DATA_URODZENIA, NARODOWOSC) VALUES"
                + "(?,?,?,?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setString(1, coach.getName());
            preparedStatement.setString(2, coach.getSurname());
            preparedStatement.setDate(3, (java.sql.Date) coach.getBirthDay());
            preparedStatement.setString(4, coach.getNationality());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coach;
    }

    @Override
    public void removeById(int couchId) {
        String couch = "DELETE FROM TRENER WHERE TRENER_ID= " + couchId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(couch)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Coach coach) {
        String coachUpdate = "UPDATE TRENER SET IMIE= ? , NAZWISKO= ? , DATA_URODZENIA= ? , NARODOWOSC= ?  WHERE TRENER_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(coachUpdate)) {
            preparedStatement.setString(1, coach.getName());
            preparedStatement.setString(2, coach.getSurname());
            preparedStatement.setDate(3, (java.sql.Date) coach.getBirthDay());
            preparedStatement.setString(4, coach.getNationality());
            preparedStatement.setInt(4, coach.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
