package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.MatchRepo;
import sample.model.Cadre;
import sample.model.Match;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchRepository implements MatchRepo {
    @Override
    public List<Match> findAll() {
        String findAllSQL = "SELECT * FROM MECZ";
        List<Match> matchList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MECZ_ID");
                int guest = rs.getInt("GOSC_KADRA");
                int host = rs.getInt("GOSPODARZ_KADRA");
                int points = rs.getInt("PKT_ZA_MECZ");
                int frequency = rs.getInt("FREKWENCJA");
                int fixture = rs.getInt("KOLEJKA");

                CadreRepository cadreRepository = new CadreRepository();
                Cadre cadreById = cadreRepository.findById(guest);

                CadreRepository cadreRepository1 = new CadreRepository();
                Cadre cadreById1 = cadreRepository.findById(host);

                Match match = new Match(id, cadreById, cadreById, points, frequency, fixture);

                matchList.add(match);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matchList;
    }

    @Override
    public Match findById(int matchId) {
        String findMatchById = "SELECT * FROM MECZ WHERE MECZ_ID=" + matchId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findMatchById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("MECZ_ID");
                int guest = rs.getInt("GOSC_KADRA");
                int host = rs.getInt("GOSPODARZ_KADRA");
                int points = rs.getInt("PKT_ZA_MECZ");
                int frequency = rs.getInt("FREKWENCJA");
                int fixture = rs.getInt("KOLEJKA");

                CadreRepository cadreRepository = new CadreRepository();
                Cadre cadreById = cadreRepository.findById(guest);

                CadreRepository cadreRepository1 = new CadreRepository();
                Cadre cadreById1 = cadreRepository.findById(host);

                return new Match(id, cadreById, cadreById, points, frequency, fixture);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Match insert(Match match) {
        String insertTableSQL = "INSERT INTO MECZ"
                + "(GOSC_KADRA, GOSPODARZ_KADRA, PKT_ZA_MECZ, FREKWENCJA, KOLEJKA) VALUES"
                + "(?,?,?,?,?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, match.getGuestCadre().getCadreId());
            preparedStatement.setInt(2, match.getHostCadre().getCadreId());
            preparedStatement.setInt(3, match.getPointsForMatch());
            preparedStatement.setInt(4, match.getFrequency());
            preparedStatement.setInt(5, match.getFixture());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return match;
    }

    @Override
    public void removeById(int matchId) {
        String club = "DELETE FROM MECZ WHERE MECZ_ID= " + matchId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(club)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Match match) {
        String matchUpdate = "UPDATE MECZ SET GOSC_KADRA= ? , GOSPODARZ_KADRA= ? , PKT_ZA_MECZ= ? , FREKWENCJA= ? , KOLEJKA= ? WHERE MECZ_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(matchUpdate)) {
            preparedStatement.setInt(1, match.getGuestCadre().getCadreId());
            preparedStatement.setInt(2, match.getHostCadre().getCadreId());
            preparedStatement.setInt(3, match.getPointsForMatch());
            preparedStatement.setInt(4, match.getFrequency());
            preparedStatement.setInt(5, match.getFixture());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
