package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.TeamRepo;
import sample.model.Cadre;
import sample.model.Player;
import sample.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamRepository implements TeamRepo {
    @Override
    public List<Team> findAll() {
        String findAllSQL = "SELECT * FROM ZAWODNIK_KADRA";
        List<Team> teamList = new ArrayList<>();
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

                Team team = new Team(cadreById, playerById);

                teamList.add(team);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teamList;
    }

    @Override
    public Team insert(Team team) {
        String insertTableSQL = "INSERT INTO ZAWODNIK_KADRA"
                + "(KADRA_ID, ZAWODNIK_ID) VALUES"
                + "(?, ?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, team.getCadreId().getCadreId());
            preparedStatement.setInt(2, team.getPlayerId().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return team;
    }

    @Override
    public Team remove(Team team) {
        return null;
    }
}
