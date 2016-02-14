package com.wordpress.ilyaps.controller;

import com.wordpress.ilyaps.Main;
import javafx.fxml.FXML;

/**
 * Created by ilyap on 05.12.2015.
 */
public class RootLayoutController {
    // Reference to the main application
    private Main main;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param main
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        main.getRefereeData().closeConnection();
        System.exit(0);
    }
}
