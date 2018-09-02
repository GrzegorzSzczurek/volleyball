package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import sample.model.*;
import sample.repositories.*;

import java.net.URL;
import java.sql.Date;
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
    private TextField tfLeagueNumberOfMatches;
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
    private ComboBox<Card> playerCardsCombobox;
    @FXML
    private TableView<Player> playerTable;
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
    private TableColumn<Player, String> playerCardsColumn;
    @FXML
    private TableColumn<Player, Integer> playerScoredPointsColumn;


    @FXML
    private Button editPlayerButton;

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
        refreshPlayerClubCombbox();

        setDataInPlayerTable();
        refreshPlayerTable();
        mouseHandlerOnPlayerTable();
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

    private void refreshPlayerClubCombbox() {
        List<Club> allClubs = new ClubRepository().findAll();
        ObservableList<Club> clubs = FXCollections.observableArrayList(allClubs);
        playerClubCombobox.setItems(clubs);
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

    private void setDataInClubTable() {
        clubNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getClubName()));
        clubHallColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getHallId().getHallName()));
        clubCountryColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getCountry()));
        clubLeagueColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getLeagueId().getLeagueName()));
        clubCoachColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCoachId().getName()));
        editClubButton.setDisable(true);
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

    private void setDataInLeagueTable() {
        leagueNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getLeagueName()));
        leagueNumberOfTeamsColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getNumberOfClubs())));
        leagueLevelColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getLeagueLevel())));
        leagueNumberOfMatchesColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getNumberOfMatches())));
        leagueYearColumn.setCellValueFactory(param -> new SimpleObjectProperty(String.valueOf(param.getValue().getYear())));
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
        //playerCardsColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCardId().getCardType()));
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

    private void mouseHandlerOnLeagueTable() {
        leagueTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            League league = leagueTable.getSelectionModel().getSelectedItem();
            if (league != null) {
                String numberOfMatches = String.valueOf(league.getNumberOfMatches());
                String numberOfTeams = String.valueOf(league.getNumberOfClubs());
                String year = String.valueOf(league.getYear());
                tfLeagueName.setText(league.getLeagueName());
                tfLeagueNumberOfMatches.setText(numberOfMatches);
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
        if (coach != null){
            try{
                new CoachRepository().removeById(coach.getId());
            }catch (RuntimeException e){
                System.err.println("Can't delete coach!");
            }
            refreshCoachTable();
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
            editCoachButton.setDisable(true);
        }
    }

    public void deleteLeague(ActionEvent actionEvent) {
        League league = leagueTable.getSelectionModel().getSelectedItem();
        if (league != null){
            try{
                new LeagueRepository().removeById(league.getId());
            }catch (RuntimeException e){
                System.err.println("Can't delete coach!");
            }
            refreshLeagueTable();
        }
    }

    public void addLeague(ActionEvent actionEvent) {
        Integer numberOfMatches = Integer.parseInt(tfLeagueNumberOfMatches.getText());
        Integer numberOfTeams = Integer.parseInt(tfLeagueNumberOfTeams.getText());
        Integer year = Integer.parseInt(tfLeagueYear.getText());
        League league = new League(tfLeagueName.getText(), tfLeagueLevel.getText(), numberOfTeams, numberOfMatches, year);
        new LeagueRepository().insert(league);

        clearLeagueFields();
        refreshLeagueTable();
    }

    private void clearLeagueFields() {
        tfLeagueYear.clear();
        tfLeagueNumberOfTeams.clear();
        tfLeagueNumberOfMatches.clear();
        tfLeagueName.clear();
        tfLeagueLevel.clear();
    }

    public void editLeague(ActionEvent actionEvent) {
        League league = leagueTable.getSelectionModel().getSelectedItem();
        if (league != null) {
            Integer numberOfMatches = Integer.parseInt(tfLeagueNumberOfMatches.getText());
            Integer numberOfTeams = Integer.parseInt(tfLeagueNumberOfTeams.getText());
            Integer year = Integer.parseInt(tfLeagueYear.getText());
            League updatedLeague = new League(league.getId(),tfLeagueName.getText(), tfLeagueLevel.getText(), numberOfTeams, numberOfMatches, year);
            new LeagueRepository().update(updatedLeague);
            refreshLeagueTable();
            clearLeagueFields();
            editLeagueButton.setDisable(true);
        }
    }

    public void addClub(ActionEvent actionEvent) {

        Hall hall = hallComboboxInClub.getSelectionModel().getSelectedItem();
        League league = leagueComboboxInClub.getSelectionModel().getSelectedItem();
        Coach coach = coachComboboxInClub.getSelectionModel().getSelectedItem();

        Club club = new Club(hall, tfClubCountry.getText(), league, coach, tfClubName.getText());
        new ClubRepository().insert(club);
        refreshClubTable();
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
            editLeagueButton.setDisable(true);
        }
    }

    public void deleteClub(ActionEvent actionEvent) {
        Club club = clubTable.getSelectionModel().getSelectedItem();
        if (club != null){
            try{
                new ClubRepository().removeById(club.getId());
            }catch (RuntimeException e){
                System.err.println("Can't delete club!");
            }
            refreshClubTable();
        }
    }

    public void addPlayer(ActionEvent actionEvent) {
        Club club = playerClubCombobox.getSelectionModel().getSelectedItem();
        Integer playerAge = Integer.parseInt(tfPlayerAge.getText());
        Integer playerHeight = Integer.parseInt(tfPlayerHeight.getText());
        Integer scoredPoints = Integer.parseInt(tfPlayerScoredPoints.getText());

        Player player = new Player(club, tfPlayerName.getText(), tfPlayerSurname.getText(), playerAge, playerHeight, scoredPoints);
        new PlayerRepository().insertBasic(player);
        refreshPlayerTable();
    }

    public void deletePlayer(ActionEvent actionEvent) {
    }

    public void editPlayer(ActionEvent actionEvent) {
    }

    public void deleteSuspensionDate(ActionEvent actionEvent) {
    }
    public void addSuspensionDate(ActionEvent actionEvent) {
    }

    public void editSuspensionDate(ActionEvent actionEvent) {
    }

    private void refreshHallTable() {
        List<Hall> halls = new HallRepository().findAll();
        ObservableList<Hall> hall = FXCollections.observableArrayList(halls);
        hallTable.setItems(hall);
    }
    private void refreshPlayerTable() {
        List<Player> players = new PlayerRepository().findAll();
        ObservableList<Player> player = FXCollections.observableArrayList(players);
        playerTable.setItems(player);
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
}
