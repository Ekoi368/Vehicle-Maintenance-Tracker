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
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class SignupController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField contactField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField roleField;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void handleSignUp(ActionEvent event) {

        String fullName = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String contact = contactField.getText().trim();
        String password = passwordField.getText();
        String role = roleField.getText().trim();

        if (fullName.isEmpty()
                || username.isEmpty()
                || email.isEmpty()
                || contact.isEmpty()
                || password.isEmpty()
                || role.isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing Information",
                    "Please fill in all fields."
            );
            return;
        }

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        )) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid Email",
                    "Please enter a valid email address."
            );
            return;
        }

        if (!contact.matches("\\d{10,15}")) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid Contact",
                    "Contact must contain between 10 and 15 digits."
            );
            return;
        }

        if (password.length() < 6) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Weak Password",
                    "Password must contain at least 6 characters."
            );
            return;
        }

        if (userDAO.usernameOrEmailExists(username, email)) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Account Exists",
                    "The username or email already exists."
            );
            return;
        }

        String passwordHash = BCrypt.hashpw(
                password,
                BCrypt.gensalt()
        );

        User newUser = new User(
                fullName,
                contact,
                email,
                username,
                passwordHash,
                role
        );

        boolean registered = userDAO.registerUser(newUser);

        if (registered) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Sign Up Successful",
                    "Your account has been created successfully."
            );

            clearFields();

            // Remove this line if you want to remain
            // on the signup page after registration.
            openLoginScreen(event);

        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Sign Up Failed",
                    "The account could not be saved. Check the IntelliJ console."
            );
        }
    }

    @FXML
    private void openLoginScreen(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/vehicletacker/vehiclemaintenancetracker/login.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle(
                    "Vehicle Maintenance Tracker - Login"
            );
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Navigation Error",
                    "Could not open the login page."
            );
        }
    }

    private void clearFields() {
        fullNameField.clear();
        usernameField.clear();
        emailField.clear();
        contactField.clear();
        passwordField.clear();
        roleField.clear();
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
