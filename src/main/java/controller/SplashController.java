package controller;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashController {
    @FXML
    public void initialize() {
        PauseTransition delay = new PauseTransition(Duration.seconds(5));

        delay.setOnFinished(event -> openLoginScreen());

        delay.play();
    }

    private void openLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/vehicletacker/vehiclemaintenancetracker/login.fxml")
            );

            Parent root = loader.load();

            Stage currentStage = getCurrentStage();

            Scene loginScene = new Scene(root);
            currentStage.setScene(loginScene);
            currentStage.setTitle("Vehicle Maintenance Tracker - Login");
            currentStage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage getCurrentStage() {
        return (Stage) Stage.getWindows()
                .filtered(window -> window.isShowing())
                .get(0);
    }
}
