package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.model.Hall;
import sample.repositories.HallRepository;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hallNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hall, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hall, String> param) {
                return new SimpleStringProperty(param.getValue().getHallName());
            }
        });
        refreshHallTable();
        hallCapacityColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Hall, Integer>, ObservableValue<Integer>>) param -> new SimpleObjectProperty(param.getValue().getCapacity()));
        refreshHallTable();

        hallCityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hall, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hall, String> param) {
                return new SimpleStringProperty(param.getValue().getCity());
            }
        });
        refreshHallTable();

        hallPostalCodeColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Hall, Integer>, ObservableValue<Integer>>) param -> new SimpleObjectProperty<>(param.getValue().getPostCode()));
        refreshHallTable();

        hallCityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hall, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hall, String> param) {
                return new SimpleStringProperty(param.getValue().getCity());
            }
        });
        refreshHallTable();
        hallStreetColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hall, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hall, String> param) {
                return new SimpleStringProperty(param.getValue().getStreet());
            }
        });
        refreshHallTable();

        editHallButton.setDisable(true);
        hallTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Hall hall= hallTable.getSelectionModel().getSelectedItem();
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
            int id = hallTable.getSelectionModel().getSelectedItem().getId();
            int capacity = Integer.parseInt(tfHallCapacity.getText());
            int postalCode = Integer.parseInt(tfHallPostalCode.getText());
            Hall updatedHall = new Hall(hall.getId(), tfHallName.getText(), capacity, tfHallCity.getText(), postalCode, tfHallStreet.getText());
            new HallRepository().update(updatedHall);
            refreshHallTable();
//            tfAuthorSurname.clear();
//            tfAuthorName.clear();
//            refreshAuthorComboBox();
//            refreshBookTable();
        }
    }

    @FXML
    public void addHall(ActionEvent event) {

        Integer capacity = Integer.parseInt(tfHallCapacity.getText());
        Integer postalCode = Integer.parseInt(tfHallPostalCode.getText());
        Hall hall = new Hall( tfHallName.getText(), capacity, tfHallCity.getText(), postalCode, tfHallStreet.getText());
        HallRepository hallRepository = new HallRepository();
        Hall addHall = hallRepository.insert(hall);
        refreshHallTable();
//        refreshAuthorComboBox();
//        tfAuthorName.clear();
//        tfAuthorSurname.clear();
//        refreshAuthorComboBox();
    }

    public void deleteCoach(ActionEvent actionEvent) {

    }

    public void addCoach(ActionEvent actionEvent) {
    }

    public void editCoach(ActionEvent actionEvent) {
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
}
