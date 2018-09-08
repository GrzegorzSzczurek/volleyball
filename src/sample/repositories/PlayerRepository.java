package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.PlayerRepo;
import sample.model.Club;
import sample.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository implements PlayerRepo {
    @Override
    public List<Player> findAll() {
        String findAllSQL = "SELECT * FROM ZAWODNIK";
        List<Player> playerList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int playerId = rs.getInt("ZAWODNIK_ID");
                int clubId = rs.getInt("KLUB_ID");
                String playerName = rs.getString("IMIE");
                String playerSurname = rs.getString("NAZWISKO");
                int age = rs.getInt("WIEK");
                int height = rs.getInt("WZROST");
                int scoredPoints = rs.getInt("PKT_ZDOBYTE");

                ClubRepository clubRepository = new ClubRepository();
                Club clubById = clubRepository.findById(clubId);

                Player player = new Player(playerId, clubById, playerName, playerSurname, age, height, scoredPoints);
                playerList.add(player);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playerList;
    }

    @Override
    public Player findById(int playerId) {
        String findHallById = "SELECT * FROM ZAWODNIK WHERE ZAWODNIK_ID=" + playerId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findHallById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ZAWODNIK_ID");
                int clubId = rs.getInt("KLUB_ID");
                String playerName = rs.getString("IMIE");
                String playerSurname = rs.getString("NAZWISKO");
                int age = rs.getInt("WIEK");
                int height = rs.getInt("WZROST");
                int scoredPoints = rs.getInt("PKT_ZDOBYTE");

                ClubRepository clubRepository = new ClubRepository();
                Club clubById = clubRepository.findById(clubId);

                Player player = new Player(playerId, clubById, playerName, playerSurname, age, height, scoredPoints);

                return player;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Player insertAll(Player player) {
        String insertTableSQL = "INSERT INTO ZAWODNIK"
                + "(KLUB_ID, IMIE, NAZWISKO, WIEK, WZROST, PKT_ZDOBYTE) VALUES"
                + "(?,?,?,?,?, ?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, player.getClubId().getId());
            preparedStatement.setString(2, player.getName());
            preparedStatement.setString(3, player.getSurname());
            preparedStatement.setInt(4, player.getAge());
            preparedStatement.setInt(5, player.getHeight());
            preparedStatement.setInt(6, player.getScoredPoints());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return player;
    }

    @Override
    public Player insertBasic(Player player) {
        String insertTableSQL = "INSERT INTO ZAWODNIK"
                + "(KLUB_ID, IMIE, NAZWISKO, WIEK, WZROST, PKT_ZDOBYTE) VALUES"
                + "(?,?,?,?,?, ?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, player.getClubId().getId());
            preparedStatement.setString(2, player.getName());
            preparedStatement.setString(3, player.getSurname());
            preparedStatement.setInt(4, player.getAge());
            preparedStatement.setInt(5, player.getHeight());
            preparedStatement.setInt(6, player.getScoredPoints());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return player;
    }

    /*@Override
    public Player insertCard(Player player) {
        String insertTableSQL = "UPDATE ZAWODNIK SET KARTKA_ID= ? WHERE ZAWODNIK_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setInt(1, player.getCardId().getId());
            preparedStatement.setInt(2, player.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return player;
    }*/

    @Override
    public void removeById(int hallId) {

        String autor = "DELETE FROM ZAWODNIK WHERE ZAWODNIK_ID= " + hallId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(autor)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void updateBasic(Player player) {

        String updatePlayerSql = "UPDATE ZAWODNIK SET KLUB_ID= ?, IMIE= ?, NAZWISKO= ?, WIEK= ?, WZROST= ?, PKT_ZDOBYTE= ? WHERE ZAWODNIK_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(updatePlayerSql)) {
            preparedStatement.setInt(1, player.getClubId().getId());
            preparedStatement.setString(2, player.getName());
            preparedStatement.setString(3, player.getSurname());
            preparedStatement.setInt(4, player.getAge());
            preparedStatement.setInt(5, player.getHeight());
            preparedStatement.setInt(6, player.getScoredPoints());
            preparedStatement.setInt(7, player.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*@Override
    public void updateWithSuspensionAndCards(Player player) {

        String updatePlayerSql = "UPDATE ZAWODNIK SET KLUB_ID= ?, IMIE= ?, NAZWISKO= ?, WIEK= ?, WZROST= ?, ZAWIESZENIE_ID= ?, KARTKA_ID= ?, PKT_ZDOBYTE= ? WHERE ZAWODNIK_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(updatePlayerSql)) {
            preparedStatement.setInt(1, player.getClubId().getId());
            preparedStatement.setString(2, player.getName());
            preparedStatement.setString(3, player.getSurname());
            preparedStatement.setInt(4, player.getAge());
            preparedStatement.setInt(5, player.getHeight());
            preparedStatement.setInt(6, player.getSuspensionId().getId());
            preparedStatement.setInt(7, player.getCardId().getId());
            preparedStatement.setInt(8, player.getScoredPoints());
            preparedStatement.setInt(9, player.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
