package com.vehicletacker.vehiclemaintenancetracker.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class VehicleController {

    @FXML
    private TextField ownerNameField;

    @FXML
    private TextField regNumberField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField modelField;

    @FXML
    private ComboBox<String> vehicleTypeComboBox;

    @FXML
    private void initialize() {
        vehicleTypeComboBox.setItems(
                FXCollections.observableArrayList(
                        "Car",
                        "Motorcycle",
                        "Truck",
                        "Bus",
                        "Van",
                        "Other"
                )
        );
    }

    @FXML
    private void handleSaveVehicle() {
        String ownerName = ownerNameField.getText().trim();
        String regNumber = regNumberField.getText().trim();
        String brand = brandField.getText().trim();
        String model = modelField.getText().trim();
        String vehicleType = vehicleTypeComboBox.getValue();

        if (ownerName.isEmpty()
                || regNumber.isEmpty()
                || brand.isEmpty()
                || model.isEmpty()
                || vehicleType == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing information",
                    "Please complete all vehicle fields."
            );
            return;
        }

        /*
         * Add VehicleDAO save code here after confirming
         * your Vehicle model fields.
         */

        showAlert(
                Alert.AlertType.INFORMATION,
                "Vehicle details",
                "The form data is valid."
        );
    }

    @FXML
    private void handleViewVehicle() {
        showAlert(
                Alert.AlertType.INFORMATION,
                "View vehicle",
                "Connect this button to your vehicle table page."
        );
    }

    @FXML
    private void handleUpdateVehicle() {
        showAlert(
                Alert.AlertType.INFORMATION,
                "Update vehicle",
                "Select a vehicle before updating it."
        );
    }

    @FXML
    private void handleDeleteVehicle() {
        showAlert(
                Alert.AlertType.INFORMATION,
                "Delete vehicle",
                "Select a vehicle before deleting it."
        );
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/vehicletacker/vehiclemaintenancetracker/Dashboard.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ownerNameField
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Navigation error",
                    "Dashboard could not be opened."
            );
        }
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}