package com.vehicletacker.vehiclemaintenancetracker.controller;

import com.vehicletacker.vehiclemaintenancetracker.DAO.MaintenanceDAO;
import com.vehicletacker.vehiclemaintenancetracker.model.MaintenanceRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MaintenanceController {

    @FXML
    private TextField mechanicNameField;

    @FXML
    private TextField serviceTypeField;

    @FXML
    private TextField costField;

    @FXML
    private DatePicker serviceDatePicker;

    @FXML
    private TextArea descriptionArea;

    private final MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

    /*
     * This ID must come from the selected vehicle.
     * Set it before opening the maintenance page.
     */
    private int vehicleId;

    /*
     * Used when updating or deleting a record.
     */
    private int selectedMaintenanceId;

    @FXML
    private void initialize() {
        serviceDatePicker.setValue(LocalDate.now());
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setSelectedMaintenanceId(int selectedMaintenanceId) {
        this.selectedMaintenanceId = selectedMaintenanceId;
    }

    @FXML
    private void handleSaveRecord() {
        String mechanicName = mechanicNameField.getText().trim();
        String serviceType = serviceTypeField.getText().trim();
        String description = descriptionArea.getText().trim();
        String costText = costField.getText().trim();
        LocalDate serviceDate = serviceDatePicker.getValue();

        if (vehicleId <= 0) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Vehicle not selected",
                    "Please select a vehicle before adding a maintenance record."
            );
            return;
        }

        if (mechanicName.isEmpty()
                || serviceType.isEmpty()
                || costText.isEmpty()
                || serviceDate == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing information",
                    "Please fill in mechanic name, service type, cost and service date."
            );
            return;
        }

        double cost;

        try {
            cost = Double.parseDouble(costText);

            if (cost < 0) {
                showAlert(
                        Alert.AlertType.WARNING,
                        "Invalid cost",
                        "Cost cannot be negative."
                );
                return;
            }

        } catch (NumberFormatException e) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid cost",
                    "Cost must be a valid number."
            );
            return;
        }

        MaintenanceRecord record = new MaintenanceRecord(
                0,
                vehicleId,
                serviceType,
                mechanicName,
                description,
                serviceDate,
                cost
        );

        boolean success = maintenanceDAO.addRecord(record);

        if (success) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success",
                    "Maintenance record saved successfully."
            );

            clearFields();

        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Save failed",
                    "Maintenance record could not be saved."
            );
        }
    }

    @FXML
    private void handleUpdateRecord() {
        if (selectedMaintenanceId <= 0) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "No record selected",
                    "Select a maintenance record before updating."
            );
            return;
        }

        String mechanicName = mechanicNameField.getText().trim();
        String serviceType = serviceTypeField.getText().trim();
        String description = descriptionArea.getText().trim();
        String costText = costField.getText().trim();
        LocalDate serviceDate = serviceDatePicker.getValue();

        if (mechanicName.isEmpty()
                || serviceType.isEmpty()
                || costText.isEmpty()
                || serviceDate == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Missing information",
                    "Please fill in all required fields."
            );
            return;
        }

        double cost;

        try {
            cost = Double.parseDouble(costText);
        } catch (NumberFormatException e) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Invalid cost",
                    "Cost must be a valid number."
            );
            return;
        }

        MaintenanceRecord record = new MaintenanceRecord(
                selectedMaintenanceId,
                vehicleId,
                serviceType,
                mechanicName,
                description,
                serviceDate,
                cost
        );

        boolean success = maintenanceDAO.updateRecord(record);

        if (success) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success",
                    "Maintenance record updated successfully."
            );
        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Update failed",
                    "Maintenance record could not be updated."
            );
        }
    }

    @FXML
    private void handleDeleteRecord() {
        if (selectedMaintenanceId <= 0) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "No record selected",
                    "Select a maintenance record before deleting."
            );
            return;
        }

        boolean success =
                maintenanceDAO.deleteRecord(selectedMaintenanceId);

        if (success) {
            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Success",
                    "Maintenance record deleted successfully."
            );

            clearFields();
            selectedMaintenanceId = 0;

        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Delete failed",
                    "Maintenance record could not be deleted."
            );
        }
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

            Stage stage = (Stage) mechanicNameField
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

    private void clearFields() {
        mechanicNameField.clear();
        serviceTypeField.clear();
        costField.clear();
        descriptionArea.clear();
        serviceDatePicker.setValue(LocalDate.now());
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