package com.wordpress.ilyaps;

import com.wordpress.ilyaps.controller.RefereeEditDialogController;
import com.wordpress.ilyaps.controller.RefereeOverviewController;
import com.wordpress.ilyaps.controller.RootLayoutController;
import com.wordpress.ilyaps.model.Referee;
import com.wordpress.ilyaps.model.RefereeListWrapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static final String PROPERTIES_FILE = "src/main/java/com/wordpress/ilyaps/cfg/db.properties";

    private Stage primaryStage;
    private BorderPane rootLayout;

    private RefereeListWrapper refereeData;

    public Main() {
        refereeData = new RefereeListWrapper(PROPERTIES_FILE);
        refereeData.openConnection();
        System.out.println(refereeData.printInfoOfConnection());
        refereeData.loadData();
    }

    public RefereeListWrapper getRefereeData() {
        return refereeData;
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("List referee");

        initRootLayout();
        showRefereeOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the view access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showRefereeOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RefereeOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the view access to the main app.
            RefereeOverviewController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified referee. If the user
     * clicks OK, the changes are saved into the provided referee object and true
     * is returned.
     *
     * @param referee the referee object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showRefereeEditDialog(Referee referee) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/RefereeEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit referee");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the referee into the view.
            RefereeEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReferee(referee);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
