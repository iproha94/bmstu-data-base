package com.wordpress.ilyaps.controller;

/**
 * Created by ilyap on 05.12.2015.
 */
import com.wordpress.ilyaps.Main;
import com.wordpress.ilyaps.model.Referee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RefereeOverviewController {
    @FXML
    private TableView<Referee> refereeTable;
    @FXML
    private TableColumn<Referee, String> firstNameColumn;
    @FXML
    private TableColumn<Referee, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label middleNameLabel;
    @FXML
    private Label cityLabel;

    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public RefereeOverviewController() {
    }

    /**
     * Initializes the view class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showRefereeDetails(null);

        refereeTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showRefereeDetails(newValue)
        );
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param main
     */
    public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
        refereeTable.setItems(main.getRefereeData().getList());
    }

    private void showRefereeDetails(Referee referee) {
        if (referee != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(referee.getFirstName());
            lastNameLabel.setText(referee.getLastName());
            middleNameLabel.setText(referee.getMiddleName());
            cityLabel.setText(Long.toString(referee.getCityId()));

        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            middleNameLabel.setText("");
            cityLabel.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewReferee() {
        Referee tempPerson = new Referee();
        boolean okClicked = main.showRefereeEditDialog(tempPerson);
        if (okClicked) {
            main.getRefereeData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditReferee() {
        Referee selectedReferee = refereeTable.getSelectionModel().getSelectedItem();
        if (selectedReferee != null) {
            boolean okClicked = main.showRefereeEditDialog(selectedReferee);
            if (okClicked) {
                if (!main.getRefereeData().update(selectedReferee)) {
                    showAlert("Error", "No exist city", "");
                } else {
                    showRefereeDetails(selectedReferee);
                }
            }
        } else {
            showAlert("No Selection", "No Person Selected", "Please select a person in the table.");
        }
    }

    private void showAlert(String str1, String str2, String str3) {
        // Nothing selected.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(main.getPrimaryStage());
        alert.setTitle(str1);
        alert.setHeaderText(str2);
        alert.setContentText(str3);

        alert.showAndWait();
    }

    @FXML
    private void handleDeleteReferee() {
        int selectedIndex = refereeTable.getSelectionModel().getSelectedIndex();
        Referee selectedReferee = refereeTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            refereeTable.getItems().remove(selectedIndex);
            main.getRefereeData().delete(selectedReferee.getId());
        } else {
            showAlert("No Selection", "No Person Selected", "Please select a person in the table.");
        }
    }
}