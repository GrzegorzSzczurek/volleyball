package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.LeagueRepo;
import sample.model.League;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeagueRepository implements LeagueRepo {

    @Override
    public List<League> findAll() {
        String findAllSQL = "SELECT * FROM LIGA";
        List<League> leagueList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("LIGA_ID");
                String leagueName = rs.getString("NAZWA_LIGI");
                String leagueLevel = rs.getString("POZIOM_LIGI");
                int numberOfTeams = rs.getInt("ILOSC_ZESPOLOW");
                int numberOfMatches = rs.getInt("ILOSC_MECZY");
                int year = rs.getInt("ROK");

                League league = new League(id, leagueName, leagueLevel, numberOfTeams, numberOfMatches,  year);
                leagueList.add(league);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return leagueList;
    }

    @Override
    public League findById(int leagueId) {
        String findHallById = "SELECT * FROM LIGA WHERE LIGA_ID=" + leagueId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findHallById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("LIGA_ID");
                String leagueName = rs.getString("NAZWA_LIGI");
                String leagueLevel = rs.getString("POZIOM_LIGI");
                int numberOfTeams = rs.getInt("ILOSC_ZESPOLOW");
                int numberOfMatches = rs.getInt("ILOSC_MECZY");
                int year = rs.getInt("ROK");

                League league = new League(id, leagueName, leagueLevel, numberOfTeams, numberOfMatches, year);

                return league;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public League insert(League league) {
        String insertTableSQL = "INSERT INTO LIGA"
                + "(NAZWA_LIGI, POZIOM_LIGI, ILOSC_ZESPOLOW, ILOSC_MECZY, ROK) VALUES"
                + "(?,?,?,?,?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setString(1, league.getLeagueName());
            preparedStatement.setString(2, league.getLeagueLevel());
            preparedStatement.setInt(3, league.getNumberOfClubs());
            preparedStatement.setInt(4, league.getNumberOfMatches());
            preparedStatement.setInt(5, league.getYear());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return league;
    }

    @Override
    public void removeById(int leagueId) {

        String autor = "DELETE FROM LIGA WHERE LIGA_ID= " + leagueId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(autor)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(League league) {

        String updatePlayerSql = "UPDATE LIGA SET NAZWA_LIGI= ?, POZIOM_LIGI= ?, ILOSC_ZESPOLOW= ?, ILOSC_MECZY= ?, ROK= ? WHERE LIGA_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(updatePlayerSql)) {
            preparedStatement.setString(1, league.getLeagueName());
            preparedStatement.setString(2, league.getLeagueLevel());
            preparedStatement.setInt(3, league.getNumberOfClubs());
            preparedStatement.setInt(4, league.getNumberOfMatches());
            preparedStatement.setInt(5, league.getYear());
            preparedStatement.setInt(6, league.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
