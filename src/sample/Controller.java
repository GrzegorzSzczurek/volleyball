package sample;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.util.StringConverter;
import oracle.jdbc.OracleTypes;
import sample.dbConnector.DbConnector;
import sample.model.*;
import sample.repositories.*;

import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField tfHallName;
    @FXML
    private TextField tfHallCapacity;
    @FXML
    private TextField tfHallCity;
    @FXML
    private TextField tfHallPostalCode;
    @FXML
    private TextField tfHallStreet;
    @FXML
    private TableView<Hall> hallTable;
    @FXML
    private TableColumn<Hall, String> hallNameColumn;
    @FXML
    private TableColumn<Hall, Integer> hallCapacityColumn;
    @FXML
    private TableColumn<Hall, String> hallCityColumn;
    @FXML
    private TableColumn<Hall, Integer> hallPostalCodeColumn;
    @FXML
    private TableColumn<Hall, String> hallStreetColumn;
    @FXML
    private Button editHallButton;

    @FXML
    private TextField tfCoachName;
    @FXML
    private TextField tfCoachSurname;
    @FXML
    private TextField tfCoachNationality;
    @FXML
    private DatePicker dpCoachDayOfBirth;
    @FXML
    private TableView<Coach> coachTable;
    @FXML
    private Button editCoachButton;
    @FXML
    private TableColumn<Coach, String> coachNameColumn;
    @FXML
    private TableColumn<Coach, String> coachSurnameColumn;
    @FXML
    private TableColumn<Coach, String> coachDayOfBirthColumn;
    @FXML
    private TableColumn<Coach, String> coachNationalityColumn;

    @FXML
    private TextField tfLeagueName;
    @FXML
    private TextField tfLeagueNumberOfTeams;
    @FXML
    private TextField tfLeagueLevel;
    @FXML
    private TextField tfLeagueYear;
    @FXML
    private TableView<League> leagueTable;
    @FXML
    private TableColumn<League, String> leagueNameColumn;
    @FXML
    private TableColumn<League, Integer> leagueNumberOfTeamsColumn;
    @FXML
    private TableColumn<League, Integer> leagueNumberOfMatchesColumn;
    @FXML
    private TableColumn<League, Integer> leagueYearColumn;
    @FXML
    private TableColumn<League, String> leagueLevelColumn;
    @FXML
    private Button editLeagueButton;

    @FXML
    private TableView<Club> clubTable;
    @FXML
    private TableColumn<Club, String> clubNameColumn;
    @FXML
    private TableColumn<Club, Integer> clubHallColumn;
    @FXML
    private TableColumn<Club, Integer> clubCountryColumn;
    @FXML
    private TableColumn<Club, String> clubLeagueColumn;
    @FXML
    private TableColumn<Club, String> clubCoachColumn;
    @FXML
    private Button editClubButton;
    @FXML
    private TextField tfClubCountry;
    @FXML
    private TextField tfClubName;
    @FXML
    private ComboBox<Hall> hallComboboxInClub;
    @FXML
    private ComboBox<League> leagueComboboxInClub;
    @FXML
    private ComboBox<Coach> coachComboboxInClub;

    @FXML
    private TextField tfPlayerName;
    @FXML
    private TextField tfPlayerSurname;
    @FXML
    private TextField tfPlayerAge;
    @FXML
    private TextField tfPlayerHeight;
    @FXML
    private TextField tfPlayerScoredPoints;
    @FXML
    private ComboBox<Club> playerClubCombobox;
    @FXML
    private TableView<Player> playerTable;
    @FXML
    private TableView<Team> playersCadreTable;
    @FXML
    private TableColumn<Player, String> playerClubColumn;
    @FXML
    private TableColumn<Player, String> playerNameColumn;
    @FXML
    private TableColumn<Player, String> playerSurnameColumn;
    @FXML
    private TableColumn<Player, Integer> playerAgeColumn;
    @FXML
    private TableColumn<Player, Integer> playerHeightColumn;
    @FXML
    private TableColumn<Card, Integer> playerIdInCardsColumn;
    @FXML
    private TableColumn<Player, Integer> playerScoredPointsColumn;
    @FXML
    private ComboBox<Club> playerClubCombobox2;
    @FXML
    private Label labelPlayer;
    @FXML
    private Label labelMatch;
    @FXML
    private Button editPlayerButton;

    @FXML
    private TableView<Card> cardTable;
    @FXML
    private ComboBox<String> playerCombobox;
    @FXML
    private ComboBox<String> cardCombobox;
    @FXML
    private TableColumn<Card, String> cardsColumn;

    @FXML
    private TableColumn<Match, Integer> hostColumn;
    @FXML
    private TableColumn<Match, Integer> guestColumn;
    @FXML
    private TableColumn<Match, Integer> frequencyColumn;
    @FXML
    private TableColumn<Match, Integer> fixtureColumn;

    @FXML
    private TableColumn<Cadre, Integer> cadreIdColumn;
    @FXML
    private TableColumn<Cadre, Integer> matchIdColumn;
    @FXML
    private TableColumn<Cadre, Integer> clubIdColumn;
    @FXML
    private TableColumn<Team, Integer> cadreIdInCadreColumn;
    @FXML
    private TableColumn<Team, Integer> playerIdColumn;
    @FXML
    private ComboBox<Cadre> hostCadreCombobox;
    @FXML
    private ComboBox<Cadre> guestCadreCombobox;
    @FXML
    private ComboBox<Cadre> cadreCombobox;
    @FXML
    private TextField tfFrequency;
    @FXML
    private TextField tfFixture;

    @FXML
    private ComboBox<Club> cadreClubCombobox;
    @FXML
    private ComboBox<Player> cadrePlayerCombobox;
    @FXML
    private ComboBox<Match> matchCombobox;
    @FXML
    private TableView<Match> matchTable;
    @FXML
    private TableView<Cadre> cadreTable;

    @FXML
    private TableView<Suspension> suspensionTable;
    @FXML
    private TableColumn<Suspension, String> suspensionPlayerNameColumn;
    @FXML
    private TableColumn<Suspension, String> suspensionPlayerSurnameColumn;
    @FXML
    private TableColumn<Suspension, Date> suspensionStartDate;
    @FXML
    private TableColumn<Suspension, Date> suspensionEndDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDataInHallTable();
        refreshHallTable();
        mouseHandlerOnHallTable();

        setDataInCoachTable();
        refreshCoachTable();
        mouseHandlerOnCoachTable();

        setDataInLeagueTable();
        refreshLeagueTable();
        mouseHandlerOnLeagueTable();

        fillHallCombobox();
        refreshLeagueComboboxInClub();

        fillLeagueCombobox();
        refreshHallComboboxInClub();

        setDataInClubTable();
        fillCoachCombobox();
        refreshCoachCombobox();
        refreshClubTable();
        mouseHandlerClubTable();

        fillPlayerClubCombobox();
        refreshPlayerClubCombobox();
        fillPlayerClubCombobox2();
        refreshPlayerClubCombobox2();

        setDataInPlayerTable();
        refreshPlayerTable();
        mouseHandlerOnPlayerTable();

        setDataInCardTable();
        mouseHandlerOnCardTable();
        fillPlayerCombobox();
        refreshCardTable();

        setDataInSuspensionTable();
        refreshSuspensionTable();

        cardCombobox.getItems().addAll(
                "Żółta",
                "Czerwona");

        fillCadreClubCombobox();
        refreshCadreClubCombobox();
        //refreshCadrePlayerCombobox();
        setDataInMatchesTables();
        refreshCadrePlayerTable();
        refreshMatchTable();
        refreshCadreTable();

        fillHostCadreCombobox();
        fillGuestCadreCombobox();
        refreshHostCadreCombobox();
        refreshGuestCadreCombobox();
    }

    private void refreshPlayerClubCombobox() {
        List<Club> allClubs = new ClubRepository().findAll();
        ObservableList<Club> clubs = FXCollections.observableArrayList(allClubs);
        playerClubCombobox.setItems(clubs);
    }

    private void refreshPlayerClubCombobox2() {
        List<Club> allClubs = new ClubRepository().findAll();
        ObservableList<Club> clubs = FXCollections.observableArrayList(allClubs);
        playerClubCombobox2.setItems(clubs);
    }

    private void refreshCadreClubCombobox() {
        List<Club> allClubs = new ClubRepository().findAll();
        ObservableList<Club> clubs = FXCollections.observableArrayList(allClubs);
        cadreClubCombobox.setItems(clubs);
    }

    private void refreshHostCadreCombobox() {
        List<Cadre> allCadres = new CadreRepository().findAll();
        ObservableList<Cadre> cadres = FXCollections.observableArrayList(allCadres);
        hostCadreCombobox.setItems(cadres);
    }

    private void refreshGuestCadreCombobox() {
        List<Cadre> allCadres = new CadreRepository().findAll();
        ObservableList<Cadre> cadres = FXCollections.observableArrayList(allCadres);
        guestCadreCombobox.setItems(cadres);
    }

    private void fillPlayerClubCombobox() {
        StringConverter<Club> scConverter = new StringConverter<Club>() {

            @Override
            public String toString(Club club) {
                return club.getClubName() + " " + club.getCountry();
            }

            @Override
            public Club fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        playerClubCombobox.setConverter(scConverter);
    }

    private void fillPlayerClubCombobox2() {
        StringConverter<Club> scConverter = new StringConverter<Club>() {

            @Override
            public String toString(Club club) {
                return club.getClubName() + " " + club.getCountry();
            }

            @Override
            public Club fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        playerClubCombobox2.setConverter(scConverter);
    }

    private void fillCadreClubCombobox() {
        StringConverter<Club> scConverter = new StringConverter<Club>() {

            @Override
            public String toString(Club club) {
                return club.getClubName() + " " + club.getCountry();
            }

            @Override
            public Club fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        cadreClubCombobox.setConverter(scConverter);
    }

    private void setDataInClubTable() {
        clubNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClubName()));
        clubHallColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getHallId().getHallName()));
        clubCountryColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getCountry()));
        clubLeagueColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getLeagueId().getLeagueName()));
        clubCoachColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCoachId().getName()));
        editClubButton.setDisable(true);
    }

    private void setDataInSuspensionTable() {
        suspensionPlayerNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPlayerId().getName()));
        suspensionPlayerSurnameColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getPlayerId().getSurname()));
        suspensionStartDate.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getStart()));
        suspensionEndDate.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getEnd()));
    }

    private void refreshCoachCombobox() {
        List<Coach> allCoaches = new CoachRepository().findAll();
        ObservableList<Coach> coaches = FXCollections.observableArrayList(allCoaches);
        coachComboboxInClub.setItems(coaches);
    }

    private void fillCoachCombobox() {
        StringConverter<Coach> scConverter = new StringConverter<Coach>() {

            @Override
            public String toString(Coach coaches) {
                return coaches.getName() + " " + coaches.getSurname();
            }

            @Override
            public Coach fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        coachComboboxInClub.setConverter(scConverter);
    }

    private void fillHallCombobox() {
        StringConverter<Hall> scConverter = new StringConverter<Hall>() {

            @Override
            public String toString(Hall halls) {
                return halls.getHallName() + " " + halls.getPostCode();
            }

            @Override
            public Hall fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        hallComboboxInClub.setConverter(scConverter);
    }

    private void fillLeagueCombobox() {
        StringConverter<League> scConverter1 = new StringConverter<League>() {

            @Override
            public String toString(League leagues) {
                return leagues.getLeagueName() + " " + leagues.getYear();
            }

            @Override
            public League fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        leagueComboboxInClub.setConverter(scConverter1);
    }

    private void fillPlayerCombobox() {
        try {
            Connection dbConnection = DbConnector.getDBConnection();
            CallableStatement cst;
            cst = dbConnection.prepareCall("{?=call PLAYER_COMBOBOX()}");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();

            ResultSet result = (ResultSet) cst.getObject(1);
            while (result.next()) {
                playerCombobox.getItems().addAll(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*private void oldFillingPlayerCombobox(){
        StringConverter<Player> scConverter1 = new StringConverter<Player>() {

            @Override
            public String toString(Player players) {
                return players.getName() + " " + players.getSurname();
            }

            @Override
            public Player fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        playerCombobox.setConverter(scConverter1);
    }*/

    private void fillHostCadreCombobox() {
        StringConverter<Cadre> scConverter1 = new StringConverter<Cadre>() {

            @Override
            public String toString(Cadre cadre) {
                return cadre.getCadreId() + " " + cadre.getClubId().getClubName();
            }

            @Override
            public Cadre fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        hostCadreCombobox.setConverter(scConverter1);
    }

    private void fillGuestCadreCombobox() {
        StringConverter<Cadre> scConverter1 = new StringConverter<Cadre>() {

            @Override
            public String toString(Cadre cadre) {
                return cadre.getCadreId() + " " + cadre.getClubId().getClubName();
            }

            @Override
            public Cadre fromString(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        guestCadreCombobox.setConverter(scConverter1);
    }

    private void refreshHallComboboxInClub() {
        List<Hall> allHalls = new HallRepository().findAll();
        ObservableList<Hall> halls = FXCollections.observableArrayList(allHalls);
        hallComboboxInClub.setItems(halls);
    }

    private void refreshLeagueComboboxInClub() {
        List<League> allLeagues = new LeagueRepository().findAll();
        ObservableList<League> leagues = FXCollections.observableArrayList(allLeagues);
        leagueComboboxInClub.setItems(leagues);
    }

    private void refreshPlayerCombobox() {
        List<Player> allPlayers = new PlayerRepository().findAll();
        ObservableList<Player> players = FXCollections.observableArrayList(allPlayers);
        //playerCombobox.setItems(players);
    }

    /*private void refreshCadrePlayerCombobox() {
        List<Player> allPlayers = new PlayerRepository().findAll();
        ObservableList<Player> players = FXCollections.observableArrayList(allPlayers);
        cadrePlayerCombobox.setItems(players);
    }*/

    private void setDataInLeagueTable() {
        leagueNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLeagueName()));
        leagueNumberOfTeamsColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getNumberOfClubs())));
        leagueLevelColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getLeagueLevel())));
        leagueNumberOfMatchesColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getNumberOfMatches())));
        leagueYearColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getYear())));
        editLeagueButton.setDisable(true);
    }

    private void setDataInMatchesTables() {

        hostColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getHostCadre().getClubId().getClubName())));
        guestColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getGuestCadre().getClubId().getClubName())));
        frequencyColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getFrequency())));
        fixtureColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getFixture())));

        cadreIdColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getCadreId())));
        matchIdColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getMatchId().getId())));
        clubIdColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getClubId().getClubName())));

        cadreIdInCadreColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getCadreId().getCadreId())));
        playerIdColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getPlayerId().getSurname())));
    }

    private void setDataInCardTable() {
        cardsColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCardType()));
        playerIdInCardsColumn.setCellValueFactory(param -> new SimpleObjectProperty((param.getValue().getPlayer().getName())));
        editLeagueButton.setDisable(true);
    }

    private void setDataInCoachTable() {
        coachNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        coachSurnameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSurname()));
        coachDayOfBirthColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBirthDay().toString()));
        coachNationalityColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNationality()));
        editCoachButton.setDisable(true);
    }

    private void setDataInHallTable() {
        hallNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getHallName()));
        hallCapacityColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getCapacity()));
        hallCityColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCity()));
        hallPostalCodeColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getPostCode()));
        hallCityColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCity()));
        hallStreetColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStreet()));
        editHallButton.setDisable(true);
    }

    private void setDataInPlayerTable() {
        playerClubColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClubId().getClubName()));
        playerNameColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getName()));
        playerSurnameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSurname()));
        playerAgeColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getAge()));
        playerHeightColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getHeight()));
        playerScoredPointsColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getScoredPoints()));
        editPlayerButton.setDisable(true);
    }

    private void mouseHandlerOnHallTable() {
        hallTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Hall hall = hallTable.getSelectionModel().getSelectedItem();
            if (hall != null) {
                String capacity = String.valueOf(hall.getCapacity());
                String postalCode = String.valueOf(hall.getPostCode());
                editHallButton.setDisable(false);
                tfHallName.setText(hall.getHallName());
                tfHallCapacity.setText(capacity);
                tfHallCity.setText(hall.getCity());
                tfHallPostalCode.setText(postalCode);
                tfHallStreet.setText(hall.getStreet());
            }
        });
    }

    private void mouseHandlerOnCoachTable() {
        coachTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Coach coach = coachTable.getSelectionModel().getSelectedItem();
            if (coach != null) {
                tfCoachName.setText(coach.getName());
                tfCoachSurname.setText(coach.getSurname());
                tfCoachNationality.setText(coach.getNationality());
                editCoachButton.setDisable(false);
            }
        });
    }

    private void mouseHandlerClubTable() {
        clubTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Club club = clubTable.getSelectionModel().getSelectedItem();
            if (club != null) {

                editHallButton.setDisable(false);
                tfClubCountry.setText(club.getCountry());
                tfClubName.setText(club.getClubName());
                editClubButton.setDisable(false);
            }
        });
    }

    private void mouseHandlerOnLeagueTable() {
        leagueTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            League league = leagueTable.getSelectionModel().getSelectedItem();
            String numberOfTeams = String.valueOf(league.getNumberOfClubs());
            if (league != null) {
                String year = String.valueOf(league.getYear());
                tfLeagueName.setText(league.getLeagueName());
                tfLeagueLevel.setText(league.getLeagueLevel());
                tfLeagueNumberOfTeams.setText(numberOfTeams);
                tfLeagueYear.setText(year);
                editLeagueButton.setDisable(false);
            }
        });
    }

    private void mouseHandlerOnPlayerTable() {
        playerTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Player player = playerTable.getSelectionModel().getSelectedItem();
            if (player != null) {
                String age = String.valueOf(player.getAge());
                String height = String.valueOf(player.getHeight());
                String scoredPoints = String.valueOf(player.getScoredPoints());
                tfPlayerName.setText(player.getName());
                tfPlayerSurname.setText(player.getSurname());
                tfPlayerAge.setText(age);
                tfPlayerHeight.setText(height);
                tfPlayerScoredPoints.setText(scoredPoints);
                editPlayerButton.setDisable(false);
            }
        });
    }

    private void mouseHandlerOnCardTable() {
        cardTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Card card = cardTable.getSelectionModel().getSelectedItem();
            if (card != null) {
            }
        });
    }

    public void deleteHall(ActionEvent event) {
        Hall hall = hallTable.getSelectionModel().getSelectedItem();
        if (hall != null) {
            try {
                HallRepository deleteHall = new HallRepository();
                deleteHall.removeById(hall.getId());
            } catch (RuntimeException exception) {
                System.err.println("Can't delete an hall");
            }
        }
        refreshHallTable();
        refreshHallComboboxInClub();
        clearHallTextFields();
    }

    public void editHall(ActionEvent event) {
        Hall hall = hallTable.getSelectionModel().getSelectedItem();
        if (hall != null) {
            int capacity = Integer.parseInt(tfHallCapacity.getText());
            int postalCode = Integer.parseInt(tfHallPostalCode.getText());
            Hall updatedHall = new Hall(hall.getId(), tfHallName.getText(), capacity, tfHallCity.getText(), postalCode, tfHallStreet.getText());
            new HallRepository().update(updatedHall);
            refreshHallTable();
            clearHallTextFields();
            refreshHallComboboxInClub();
            editHallButton.setDisable(true);
        }
    }

    public void addHall(ActionEvent event) {

        Integer capacity = Integer.parseInt(tfHallCapacity.getText());
        Integer postalCode = Integer.parseInt(tfHallPostalCode.getText());
        Hall hall = new Hall(tfHallName.getText(), capacity, tfHallCity.getText(), postalCode, tfHallStreet.getText());
        HallRepository hallRepository = new HallRepository();
        hallRepository.insert(hall);

        clearHallTextFields();
        refreshHallTable();
        refreshHallComboboxInClub();
    }

    private void clearHallTextFields() {
        tfHallName.clear();
        tfHallCapacity.clear();
        tfHallCity.clear();
        tfHallPostalCode.clear();
        tfHallStreet.clear();
    }

    public void deleteCoach(ActionEvent actionEvent) {
        Coach coach = coachTable.getSelectionModel().getSelectedItem();
        if (coach != null) {
            try {
                new CoachRepository().removeById(coach.getId());
            } catch (RuntimeException e) {
                System.err.println("Can't delete coach!");
            }
            refreshCoachTable();
            clearCoachFields();
            refreshCoachCombobox();
        }
    }

    public void addCoach(ActionEvent actionEvent) {
        Date dateOfBirth = Date.valueOf(dpCoachDayOfBirth.getValue());
        Coach coach = new Coach(tfCoachName.getText(), tfCoachSurname.getText(), dateOfBirth, tfCoachNationality.getText());
        new CoachRepository().insert(coach);
        clearCoachFields();
        refreshCoachTable();
    }

    private void clearCoachFields() {
        tfCoachName.clear();
        tfCoachSurname.clear();
        tfCoachNationality.clear();
        dpCoachDayOfBirth.getEditor().clear();
    }

    public void editCoach(ActionEvent actionEvent) {
        Coach coach = coachTable.getSelectionModel().getSelectedItem();
        if (coach != null) {
            Date dateOfBirth = Date.valueOf(dpCoachDayOfBirth.getValue());
            Coach updatedCoach = new Coach(coach.getId(), tfCoachName.getText(), tfCoachSurname.getText(), dateOfBirth, tfCoachNationality.getText());
            new CoachRepository().update(updatedCoach);
            refreshCoachTable();
            clearCoachFields();
            refreshCoachCombobox();
            editCoachButton.setDisable(true);
        }
    }

    public void deleteLeague(ActionEvent actionEvent) {
        League league = leagueTable.getSelectionModel().getSelectedItem();
        if (league != null) {
            try {
                new LeagueRepository().removeById(league.getId());
            } catch (RuntimeException e) {
                System.err.println("Can't delete league!");
            }
            refreshLeagueTable();
            clearLeagueFields();
            refreshLeagueComboboxInClub();
        }
    }

    public void addLeague(ActionEvent actionEvent) {

        Integer numberOfTeams = Integer.parseInt(tfLeagueNumberOfTeams.getText());
        Integer year = Integer.parseInt(tfLeagueYear.getText());
        League league = new League(tfLeagueName.getText(), tfLeagueLevel.getText(), numberOfTeams, year);
        new LeagueRepository().insert(league);

        clearLeagueFields();
        refreshLeagueTable();
        refreshLeagueComboboxInClub();
    }

    public void editLeague(ActionEvent actionEvent) {
        League league = leagueTable.getSelectionModel().getSelectedItem();
        if (league != null) {
            Integer numberOfTeams = Integer.parseInt(tfLeagueNumberOfTeams.getText());
            Integer year = Integer.parseInt(tfLeagueYear.getText());
            League updatedLeague = new League(league.getId(), tfLeagueName.getText(), tfLeagueLevel.getText(), numberOfTeams, year);
            new LeagueRepository().update(updatedLeague);
            refreshLeagueTable();
            clearLeagueFields();
            refreshLeagueComboboxInClub();

            editLeagueButton.setDisable(true);
        }
    }

    private void clearLeagueFields() {
        tfLeagueYear.clear();
        tfLeagueNumberOfTeams.clear();
        tfLeagueName.clear();
        tfLeagueLevel.clear();
    }

    private void clearClubFields() {
        tfClubCountry.clear();
        tfClubName.clear();
        hallComboboxInClub.getSelectionModel().clearSelection();
        leagueComboboxInClub.getSelectionModel().clearSelection();
        coachComboboxInClub.getSelectionModel().clearSelection();
    }

    public void addClub(ActionEvent actionEvent) {

        Hall hall = hallComboboxInClub.getSelectionModel().getSelectedItem();
        League league = leagueComboboxInClub.getSelectionModel().getSelectedItem();
        Coach coach = coachComboboxInClub.getSelectionModel().getSelectedItem();

        Club club = new Club(hall, tfClubCountry.getText(), league, coach, tfClubName.getText());
        new ClubRepository().insert(club);
        refreshClubTable();
        clearClubFields();
        refreshPlayerClubCombobox();
    }

    public void editClub(ActionEvent actionEvent) {
        Club club = clubTable.getSelectionModel().getSelectedItem();
        if (club != null) {
            Hall hall = hallComboboxInClub.getSelectionModel().getSelectedItem();
            League league = leagueComboboxInClub.getSelectionModel().getSelectedItem();
            Coach coach = coachComboboxInClub.getSelectionModel().getSelectedItem();

            Club updatedClub = new Club(club.getId(), hall, tfClubCountry.getText(), league, coach, tfClubName.getText());
            new ClubRepository().update(updatedClub);
            refreshClubTable();
            refreshLeagueTable();
            clearLeagueFields();
            clearClubFields();
            refreshPlayerClubCombobox();

            editLeagueButton.setDisable(true);
        }
    }

    public void deleteClub(ActionEvent actionEvent) {
        Club club = clubTable.getSelectionModel().getSelectedItem();
        if (club != null) {
            try {
                new ClubRepository().removeById(club.getId());
            } catch (RuntimeException e) {
                System.err.println("Can't delete club!");
            }
            refreshClubTable();
            clearClubFields();
            refreshPlayerClubCombobox();

        }
    }

    @FXML
    public void sumPoints(ActionEvent event) {
        Club club = playerClubCombobox2.getSelectionModel().getSelectedItem();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement("select SUMOFSCOREDPOINTSBYPLAYERS(" + club.getId() + ")from dual")) {
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int res = rs.getInt(1);
            labelPlayer.setText("Suma zdobytych punktów przez zawodników klubu: " + club.getClubName() + " wynosi: " + String.valueOf(String.valueOf(res)));
            playerClubCombobox2.getSelectionModel().clearSelection();
            labelVisibility(labelPlayer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void averageAge(ActionEvent actionEvent) {
        Club club = playerClubCombobox2.getSelectionModel().getSelectedItem();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement("select AVERAGEOFAGE(" + club.getId() + ")from dual")) {
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int res = rs.getInt(1);
            labelPlayer.setText("Średnia wieku zawodników klubu: " + club.getClubName() + " wynosi: " + String.valueOf(String.valueOf(res)));
            playerClubCombobox2.getSelectionModel().clearSelection();
            labelVisibility(labelPlayer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void averageHeight(ActionEvent actionEvent) {
        Club club = playerClubCombobox2.getSelectionModel().getSelectedItem();
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement("select AVERAGEOFHEIGHT(" + club.getId() + ")from dual")) {
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int res = rs.getInt(1);
            labelPlayer.setText("Średnia wzrostu zawodników klubu: " + club.getClubName() + " wynosi: " + String.valueOf(String.valueOf(res)));
            playerClubCombobox2.getSelectionModel().clearSelection();
            labelVisibility(labelPlayer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void averageFrequency(ActionEvent actionEvent) {
        try (Connection dbConnection = DbConnector.getDBConnection();
             PreparedStatement preparedStatement = dbConnection.prepareStatement("select AVERAGEOFFREQUENCY from dual")) {
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int res = rs.getInt(1);
            labelMatch.setText("Średnia frekwencja na meczach wynosi: " + String.valueOf(String.valueOf(res)));
            labelVisibility(labelMatch);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void labelVisibility(Label label) {
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3)
        );
        visiblePause.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                labelPlayer.setVisible(false);
            }
        });
        visiblePause.play();
        label.setVisible(true);
    }

    public void addPlayer(ActionEvent actionEvent) {
        Club club = playerClubCombobox.getSelectionModel().getSelectedItem();
        Integer playerAge = Integer.parseInt(tfPlayerAge.getText());
        Integer playerHeight = Integer.parseInt(tfPlayerHeight.getText());
        Integer scoredPoints = Integer.parseInt(tfPlayerScoredPoints.getText());

        Player player = new Player(club, tfPlayerName.getText(), tfPlayerSurname.getText(), playerAge, playerHeight, scoredPoints);
        new PlayerRepository().insertBasic(player);
        refreshPlayerTable();
        refreshPlayerCombobox();
        fillPlayerCombobox();
        clearPlayerFields();
    }

    public void deletePlayer(ActionEvent actionEvent) {
        Player player = playerTable.getSelectionModel().getSelectedItem();
        if (player != null) {
            try {
                new PlayerRepository().removeById(player.getId());
            } catch (RuntimeException e) {
                System.err.println("Can't delete player!");
            }
            refreshPlayerTable();
            refreshPlayerCombobox();
            clearPlayerFields();
        }
    }

    public void editPlayer(ActionEvent actionEvent) {

        Player player = playerTable.getSelectionModel().getSelectedItem();
        if (player != null) {
            Club club = playerClubCombobox.getSelectionModel().getSelectedItem();
            int playerAge = Integer.parseInt(tfPlayerAge.getText());
            int playerHeight = Integer.parseInt(tfPlayerHeight.getText());
            int scoredPoints = Integer.parseInt(tfPlayerScoredPoints.getText());

            Player updatedPlayer = new Player(player.getId(), club, tfPlayerName.getText(), tfPlayerSurname.getText(), playerAge, playerHeight, scoredPoints);
            new PlayerRepository().updateBasic(updatedPlayer);
            refreshPlayerTable();
            clearPlayerFields();
            refreshPlayerCombobox();
            editPlayerButton.setDisable(true);
        }
    }

    public void addCardToPlayer(ActionEvent actionEvent) {
        try {
            Connection dbConnection = DbConnector.getDBConnection();
            CallableStatement cst;
            cst = dbConnection.prepareCall("{call ADDSUSPENSION(" + playerCombobox.getSelectionModel().getSelectedItem().substring(0, 3) + ", '" + cardCombobox.getSelectionModel().getSelectedItem() + "')}");
            cst.execute();
            playerCombobox.getSelectionModel().clearSelection();
            cardCombobox.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        refreshCardTable();
        refreshSuspensionTable();
    }

    /*public void oldAddCardToPlayer(ActionEvent actionEvent){
        Player playerFromCombobox = playerCombobox.getSelectionModel().getSelectedItem();
        Card card = new Card(cardCombobox.getSelectionModel().getSelectedItem(), playerFromCombobox);
        new CardRepository().insert(card);
        playerCombobox.getSelectionModel().clearSelection();
        cardCombobox.getSelectionModel().clearSelection();
        refreshCardTable();
    }*/

    public void addCadre(ActionEvent actionEvent) {

        Club club = cadreClubCombobox.getSelectionModel().getSelectedItem();
        Cadre cadre = new Cadre(club);
        new CadreRepository().insertWithoutMatch(cadre);
        cadreClubCombobox.getSelectionModel().clearSelection();
    }

    public void deleteCadre(ActionEvent actionEvent) {
    }

    public void editCadre(ActionEvent actionEvent) {
    }

    private void clearPlayerFields() {
        tfPlayerName.clear();
        tfPlayerSurname.clear();
        tfPlayerAge.clear();
        tfPlayerHeight.clear();
        tfPlayerScoredPoints.clear();
        playerClubCombobox.getSelectionModel().clearSelection();
    }

    private void refreshHallTable() {
        List<Hall> halls = new HallRepository().findAll();
        ObservableList<Hall> hall = FXCollections.observableArrayList(halls);
        hallTable.setItems(hall);
    }

    private void refreshMatchTable() {
        List<Match> matches = new MatchRepository().findAll();
        ObservableList<Match> match = FXCollections.observableArrayList(matches);
        matchTable.setItems(match);
    }

    private void refreshPlayerTable() {
        List<Player> players = new PlayerRepository().findAll();
        ObservableList<Player> player = FXCollections.observableArrayList(players);
        playerTable.setItems(player);
    }

    private void refreshCadreTable() {
        List<Cadre> cadres = new CadreRepository().findAll();
        ObservableList<Cadre> cadre = FXCollections.observableArrayList(cadres);
        cadreTable.setItems(cadre);
    }

    private void refreshCadrePlayerTable() {
        List<Team> playerCadre = new TeamRepository().findAll();
        ObservableList<Team> team = FXCollections.observableArrayList(playerCadre);

        playersCadreTable.setItems(team);
    }

    private void refreshCoachTable() {
        List<Coach> coaches = new CoachRepository().findAll();
        ObservableList<Coach> coach = FXCollections.observableArrayList(coaches);
        coachTable.setItems(coach);
    }

    private void refreshLeagueTable() {
        List<League> leagues = new LeagueRepository().findAll();
        ObservableList<League> league = FXCollections.observableArrayList(leagues);
        leagueTable.setItems(league);
    }

    private void refreshClubTable() {
        List<Club> clubs = new ClubRepository().findAll();
        ObservableList<Club> club = FXCollections.observableArrayList(clubs);
        clubTable.setItems(club);
    }

    private void refreshCardTable() {
        List<Card> cards = new CardRepository().findAll();
        ObservableList<Card> card = FXCollections.observableArrayList(cards);
        cardTable.setItems(card);
    }

    private void refreshSuspensionTable() {
        List<Suspension> suspensions = new SuspensionRepository().findAll();
        ObservableList<Suspension> suspension = FXCollections.observableArrayList(suspensions);
        suspensionTable.setItems(suspension);
    }

    public void addPlayerToCadre(ActionEvent actionEvent) {
    }

    public void deleteCard(ActionEvent actionEvent) {
        Card card = cardTable.getSelectionModel().getSelectedItem();
        new CardRepository().removeById(card.getId());
        refreshCardTable();
    }

    public void addMatch(ActionEvent actionEvent) {
        Cadre hostCadre = hostCadreCombobox.getSelectionModel().getSelectedItem();
        Cadre guestCadre = guestCadreCombobox.getSelectionModel().getSelectedItem();
        Integer frequency = Integer.parseInt(tfFrequency.getText());
        Integer fixture = Integer.parseInt(tfFixture.getText());
        /*
        Match match = new Match(hostCadre, guestCadre, frequency, fixture);
        new MatchRepository().insert(match);
        clearMatchFields();*/

        try {
            Connection dbConnection = DbConnector.getDBConnection();
            CallableStatement cst;
            cst = dbConnection.prepareCall("{call CORRECTFREQUENCY(" + frequency + ", " + hostCadre.getCadreId() + ", " + guestCadre.getCadreId() + ", " + fixture + ")}");
            cst.execute();
            clearMatchFields();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearMatchFields() {
        tfFrequency.clear();
        hostCadreCombobox.getSelectionModel().clearSelection();
        tfFixture.clear();
        guestCadreCombobox.getSelectionModel().clearSelection();
    }
}
