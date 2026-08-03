package com.vehicletacker.vehiclemaintenancetracker.controller;

import com.vehicletacker.vehiclemaintenancetracker.DAO.UserDAO;
import com.vehicletacker.vehiclemaintenancetracker.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleLogin(ActionEvent event) {

        String usernameOrEmail = usernameField.getText().trim();
        String password = passwordField.getText();

        if (usernameOrEmail.isEmpty() || password.isEmpty()) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing Information",
                    "Please enter your username or email and password."
            );
            return;
        }

        User loggedInUser = userDAO.loginUser(usernameOrEmail, password);

        if (loggedInUser != null) {

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Login Successful",
                    "You have logged in successfully."
            );

            openDashboard(event, loggedInUser);

        } else {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Login Failed",
                    "Invalid username, email or password."
            );
        }
    }

    private void openDashboard(ActionEvent event, User loggedInUser) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/vehicletacker/vehiclemaintenancetracker/dashboard.fxml"
                    )
            );

            Parent root = loader.load();

            DashboardController dashboardController = loader.getController();

            if (dashboardController != null) {
                dashboardController.setUsername(loggedInUser.getUsername());
            }

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Vehicle Maintenance Tracker - Dashboard");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Navigation Error",
                    "Could not open the dashboard.\n\n" + e.getMessage()
            );

            e.printStackTrace();
        }
    }

    @FXML
    private void openSignUp(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/vehicletacker/vehiclemaintenancetracker/Signup.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Vehicle Maintenance Tracker - Sign Up");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Navigation Error",
                    "Could not open the signup page.\n\n" + e.getMessage()
            );

            e.printStackTrace();
        }
    }

    private void showAlert(
            Alert.AlertType alertType,
            String title,
            String message
    ) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}