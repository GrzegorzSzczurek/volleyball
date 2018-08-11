package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.ClubRepo;
import sample.model.Club;
import sample.model.Coach;
import sample.model.Hall;
import sample.model.League;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubRepository implements ClubRepo {

    @Override
    public List<Club> findAll() {
        String findAllSQL = "SELECT * FROM KLUB";
        List<Club> clubList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("KLUB_ID");
                int hallId = rs.getInt("HALA_ID");
                String country = rs.getString("KRAJ");
                int leagueId = rs.getInt("LIGA_ID");
                int coachId = rs.getInt("TRENER_ID");
                String clubName = rs.getString("NAZWA_KLUBU");

                HallRepository hallRepository = new HallRepository();
                Hall hallById = hallRepository.findById(hallId);

                LeagueRepository leagueRepository = new LeagueRepository();
                League leagueById = leagueRepository.findById(leagueId);

                CoachRepository coachRepository = new CoachRepository();
                Coach coachById = coachRepository.findById(coachId);

                Club club = new Club(id, hallById, country, leagueById, coachById, clubName);

                clubList.add(club);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clubList;
    }

    @Override
    public Club findById(int clubId) {
        String findBookById = "SELECT * FROM KLUB WHERE KLUB_ID=" + clubId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findBookById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("KLUB_ID");
                int hallId = rs.getInt("HALA_ID");
                String country = rs.getString("KRAJ");
                int leagueId = rs.getInt("LIGA_ID");
                int coachId = rs.getInt("TRENER_ID");
                String clubName = rs.getString("NAZWA_KLUBU");

                HallRepository hallRepository = new HallRepository();
                Hall hallById = hallRepository.findById(hallId);

                LeagueRepository leagueRepository = new LeagueRepository();
                League leagueById = leagueRepository.findById(leagueId);

                CoachRepository coachRepository = new CoachRepository();
                Coach coachById = coachRepository.findById(coachId);

                return new Club(id, hallById, country, leagueById, coachById, clubName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Club insert(Club club) {
        String insertTableSQL = "INSERT INTO KLUB"
                + "(KLUB_ID, HALA_ID, KRAJ, LIGA_ID, TRENER_ID, NAZWA_KLUBU) VALUES"
                + "(?,?,?,?,?,?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, club.getId());
            preparedStatement.setInt(2, club.getHallId().getId());
            preparedStatement.setString(3, club.getCountry());
            preparedStatement.setInt(4, club.getLeagueId().getId());
            preparedStatement.setInt(5, club.getCoachId().getId());
            preparedStatement.setString(6, club.getClubname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return club;
    }

    @Override
    public void removeById(int clubId) {
        String club = "DELETE FROM KLUB WHERE KLUB_ID= " + clubId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(club)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Club club) {
        String ksiazkaUpdate = "UPDATE KLUB SET HALA_ID= ? , KRAJ= ? , LIGA_ID= ? , TRENER_ID= ? , NAZWA_KLUBU= ? WHERE KLUB_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(ksiazkaUpdate)) {
            preparedStatement.setInt(1, club.getId());
            preparedStatement.setInt(2, club.getHallId().getId());
            preparedStatement.setString(3, club.getCountry());
            preparedStatement.setInt(4, club.getLeagueId().getId());
            preparedStatement.setInt(5, club.getCoachId().getId());
            preparedStatement.setString(6, club.getClubname());
            preparedStatement.setInt(7, club.getId());

            int i = preparedStatement.executeUpdate();
            System.out.println("as");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
