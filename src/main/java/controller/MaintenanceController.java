package controller;

import DAO.MaintenanceDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MaintenanceRecord;

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
    private TextArea descriptionField;

    private final MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

    private int vehicleId;
    private int userId;

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    private void handleSaveRecord() {
        String mechanicName = mechanicNameField.getText().trim();
        String serviceType = serviceTypeField.getText().trim();
        String costText = costField.getText().trim();
        LocalDate serviceDate = serviceDatePicker.getValue();
        String description = descriptionField.getText().trim();

        if (mechanicName.isEmpty() || serviceType.isEmpty() || costText.isEmpty() || serviceDate == null) {
            showAlert("Please fill in all required fields.");
            return;
        }

        double cost;
        try {
            cost = Double.parseDouble(costText);
        } catch (NumberFormatException e) {
            showAlert("Cost must be a valid number.");
            return;
        }

        MaintenanceRecord record = new MaintenanceRecord(0, vehicleId, serviceType, mechanicName, description, serviceDate, cost);
        boolean success = maintenanceDAO.addRecord(record);

        if (success) {
            showAlert("Maintenance record saved successfully.");
            clearFields();
        } else {
            showAlert("Failed to save record. Please try again.");
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/vehicletacker/vehiclemaintenancetracker/Dashboard.fxml")
            );
            Parent root = loader.load();

            DashboardConroller dashboardConroller = loader.getController();
            dashboardConroller.setUserId(userId);

            Stage stage = (Stage) mechanicNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Vehicle Maintenance Tracker - Dashboard");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        mechanicNameField.clear();
        serviceTypeField.clear();
        costField.clear();
        serviceDatePicker.setValue(null);
        descriptionField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Maintenance Record");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}