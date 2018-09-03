package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.TeamRepo;
import sample.model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TeamRepository implements TeamRepo {
    @Override
    public List<Team> findAll() {
        return null;
    }

    @Override
    public Team insert(Team team) {
        String insertTableSQL = "INSERT INTO ZESPOL"
                + "(KLUB_ID, ZAWODNIK_ID) VALUES"
                + "(?,?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, team.getClubId().getId());
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
