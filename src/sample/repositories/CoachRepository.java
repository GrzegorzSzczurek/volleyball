package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.CoachRepo;
import sample.model.Coach;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoachRepository implements CoachRepo {
    @Override
    public List<Coach> findAll() {
        String findAllSQL = "SELECT * FROM TRENER";
        List<Coach> coachList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("TRENER_ID");
                String name = rs.getString("IMIE");
                String surname = rs.getString("NAZWISKO");
                String nationality = rs.getString("NARODOWOSC");
                Date date = rs.getDate("DATA_URODZENIA");

                Coach coach = new Coach(id, name, surname, date, nationality);
                coachList.add(coach);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coachList;
    }

    @Override
    public Coach findById(int coachId) {
        String findCoachById = "SELECT * FROM TRENER WHERE TRENER_ID=" + coachId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findCoachById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("TRENER_ID");
                String name = rs.getString("IMIE");
                String surname = rs.getString("NAZWISKO");
                String nationality = rs.getString("NARODOWOSC");
                Date date = rs.getDate("DATA_URODZENIA");
                return new Coach(id, name, surname, date, nationality);
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
            preparedStatement.setString(3, String.valueOf(coach.getBirthDay()));
            preparedStatement.setString(4, coach.getNationality());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coach;
    }

    @Override
    public void removeById(int coachId) {
        String coach = "DELETE FROM TRENER WHERE TRENER_ID= " + coachId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(coach)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Coach coach) {
        String coachUpdate = "UPDATE TRENER SET IMIE= ? , NAZWISKO= ? , DATA_URODZENIA= ? , NARODOWOSC= ? WHERE TRENER_ID=?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(coachUpdate)) {
            preparedStatement.setString(1, coach.getName());
            preparedStatement.setString(2, coach.getSurname());
            preparedStatement.setString(3, String.valueOf(coach.getBirthDay()));
            preparedStatement.setString(4, coach.getNationality());
            preparedStatement.setInt(5, coach.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
