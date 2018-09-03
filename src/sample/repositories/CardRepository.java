package sample.repositories;

import sample.dbConnector.DbConnector;
import sample.interfaces.CardRepo;
import sample.model.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRepository implements CardRepo {
    @Override
    public List<Card> findAll() {
        String findAllSQL = "SELECT * FROM KARTKI";
        List<Card> cardList = new ArrayList<>();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findAllSQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("KARTKA_ID");
                String cardColor = rs.getString("KOLOR_KARTKI");

                Card card = new Card(id, cardColor);
                cardList.add(card);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cardList;
    }

    @Override
    public Card findById(int cardId) {
        String findCadreById = "SELECT * FROM KARTKI WHERE KARTKA_ID=" + cardId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(findCadreById)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("KARTKA_ID");
                String cardColor = rs.getString("KOLOR_KARTKI");

                return new Card(id, cardColor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Card insert(Card card) {
        String insertTableSQL = "INSERT INTO KARTKI"
                + "(KOLOR_KARTKI) VALUES"
                + "(?)";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL)) {
            preparedStatement.setString(1, card.getCardType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return card;
    }

    @Override
    public void removeById(int cardId) {
        String card = "DELETE FROM KARTKI WHERE KARTKA_ID= " + cardId;
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(card)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Card card) {
        String cardUpdate = "UPDATE KARTKI SET KOLOR_KARTKI= ? WHERE KARTKA_ID= ?";
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(cardUpdate)) {
            preparedStatement.setString(1, card.getCardType());
            preparedStatement.setInt(2, card.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
