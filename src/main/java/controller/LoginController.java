package controller;

import DAO.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

    public class LoginController {

        @FXML
        private TextField usernameField;

        @FXML
        private PasswordField passwordField;

        private final UserDAO userDAO = new UserDAO();

        @FXML
        private void handleSignIn() {
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                showAlert("Please enter both username and password.");
                return;
            }

            User user = userDAO.validateLogin(username, password);

            if (user != null) {
                openDashboard(user.getUserId());
            } else {
                showAlert("Invalid username or password.");
            }
        }

        @FXML
        private void handleSignUp() {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/vehicletacker/vehiclemaintenancetracker/sign-up.fxml")
                );
                Parent root = loader.load();
                Stage stage = getCurrentStage();
                stage.setScene(new Scene(root));
                stage.setTitle("Vehicle Maintenance Tracker - Sign Up");
                stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void openDashboard(int userId) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/vehicletacker/vehiclemaintenancetracker/Dashboard.fxml")
                );
                Parent root = loader.load();

                DashboardConroller dashboardConroller = loader.getController();
                dashboardConroller.setUserId(userId);

                Stage stage = getCurrentStage();
                stage.setScene(new Scene(root));
                stage.setTitle("Vehicle Maintenance Tracker - Dashboard");
                stage.centerOnScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void showAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        private Stage getCurrentStage() {
            return (Stage) usernameField.getScene().getWindow();
        }
    }
