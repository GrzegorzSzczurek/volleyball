package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.CadreRepo;
import sample.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CadreRepository implements CadreRepo{
    @Override
    public List<Cadre> findAll() {
        String findAllSQL = "SELECT * FROM KADRA";
        List<Cadre> cadreList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                int cadreId = rs.getInt("KADRA_ID");
                int matchId = rs.getInt("MECZ_ID");
                int playerId = rs.getInt("ZAWODNIK_ID");
                int clubId = rs.getInt("KLUB_ID");

                MatchRepository matchRepository= new MatchRepository();
                Match matchById= matchRepository.findById(matchId);

                PlayerRepository playerRepository= new PlayerRepository();
                Player playerById = playerRepository.findById(playerId);

                ClubRepository clubRepository= new ClubRepository();
                Club clubById = clubRepository.findById(clubId);

                Cadre cadre = new Cadre(cadreId, matchById, playerById, clubById);

                cadreList.add(cadre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cadreList;
    }

    @Override
    public Cadre findById(int cadreId) {
        String cadreById = "SELECT * FROM KADRA WHERE KADRA_ID=" + cadreId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(cadreById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("KADRA_ID");
                int matchId = rs.getInt("MECZ_ID");
                int playerId = rs.getInt("ZAWODNIK_ID");
                int clubId = rs.getInt("KLUB_ID");

                MatchRepository matchRepository= new MatchRepository();
                Match matchById= matchRepository.findById(matchId);

                PlayerRepository playerRepository= new PlayerRepository();
                Player playerById = playerRepository.findById(playerId);

                ClubRepository clubRepository= new ClubRepository();
                Club clubById = clubRepository.findById(clubId);

                Cadre cadre = new Cadre(id, matchById, playerById, clubById);

                return cadre;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Cadre insert(Cadre cadre) {
        String insertTableSQL = "INSERT INTO KADRA"
                + "(MECZ_ID, ZAWODNIK_ID, KLUB_ID) VALUES"
                + "(?,?,?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, cadre.getMatchId().getId());
            preparedStatement.setInt(2, cadre.getPlayerId().getId());
            preparedStatement.setInt(3, cadre.getClubId().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cadre;
    }

    @Override
    public void removeById(int cadreId) {

        String cadre = "DELETE FROM KADRA WHERE KADRA_ID= " + cadreId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(cadre)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(Cadre cadre) {

        String updateCadreSql = "UPDATE KADRA SET MECZ_ID= ?, ZAWODNIK_ID= ?, KLUB_ID= ? WHERE KADRA_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(updateCadreSql)){
            preparedStatement.setInt(1, cadre.getMatchId().getId());
            preparedStatement.setInt(2, cadre.getPlayerId().getId());
            preparedStatement.setInt(3, cadre.getClubId().getId());
            preparedStatement.setInt(4, cadre.getCadreId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
