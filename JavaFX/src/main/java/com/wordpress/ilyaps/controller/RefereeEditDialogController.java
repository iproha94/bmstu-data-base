package com.wordpress.ilyaps.controller;

import com.wordpress.ilyaps.model.Referee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by ilyap on 05.12.2015.
 */
public class RefereeEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField middleNameField;
    @FXML
    private TextField cityField;

    private Stage dialogStage;
    private Referee referee;
    private boolean okClicked = false;

    /**
     * Initializes the view class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the referee to be edited in the dialog.
     *
     * @param referee
     */
    public void setReferee(Referee referee) {
        this.referee = referee;

        firstNameField.setText(referee.getFirstName());
        lastNameField.setText(referee.getLastName());
        middleNameField.setText(referee.getMiddleName());
        cityField.setText(Long.toString(referee.getCityId()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            referee.setFirstName(firstNameField.getText());
            referee.setLastName(lastNameField.getText());
            referee.setMiddleName(middleNameField.getText());
            referee.setCityId(new Long(cityField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (middleNameField.getText() == null || middleNameField.getText().length() == 0) {
            errorMessage += "No valid middleName!\n";
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Long.parseLong(cityField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid id city!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
