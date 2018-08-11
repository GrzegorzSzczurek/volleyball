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
import sample.model.Coach;
import sample.model.Hall;
import sample.model.League;
import sample.repositories.CoachRepository;
import sample.repositories.HallRepository;

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
    private TextField tfLeagueYear;

    @FXML
    private TableColumn<League, String> leagueNameColumn;
    @FXML
    private TableColumn<League, String> leagueNumberOfTeamsColumn;
    @FXML
    private TableColumn<League, String> leagueNumberOfMatchesColumn;
    @FXML
    private TableColumn<League, String> leagueYearColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hallNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getHallName()));
        hallCapacityColumn.setCellValueFactory(param -> new SimpleObjectProperty(param.getValue().getCapacity()));
        hallCityColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCity()));
        hallPostalCodeColumn.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getPostCode()));
        hallCityColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCity()));
        hallStreetColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getStreet()));
        refreshHallTable();

        mouseHandlerOnHallTable();
        editHallButton.setDisable(true);

        coachNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getName()));
        coachSurnameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getSurname()));
        coachDayOfBirthColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getBirthDay().toString()));
        coachNationalityColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNationality()));
        editCoachButton.setDisable(true);
        refreshCoachTable();

        mouseHandlerOnCoachTable();
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


    @FXML
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

    @FXML
    public void editHall(ActionEvent event) {
        Hall hall = hallTable.getSelectionModel().getSelectedItem();
        if (hall != null) {
            int capacity = Integer.parseInt(tfHallCapacity.getText());
            int postalCode = Integer.parseInt(tfHallPostalCode.getText());
            Hall updatedHall = new Hall(hall.getId(), tfHallName.getText(), capacity, tfHallCity.getText(), postalCode, tfHallStreet.getText());
            new HallRepository().update(updatedHall);
            refreshHallTable();
            clearHallTextFields();
            editHallButton.setDisable(false);
        }
    }

    @FXML
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
            editCoachButton.setDisable(false);
        }
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
    private void refreshCoachTable() {
        List<Coach> coaches = new CoachRepository().findAll();
        ObservableList<Coach> coach = FXCollections.observableArrayList(coaches);
        coachTable.setItems(coach);
    }
}
