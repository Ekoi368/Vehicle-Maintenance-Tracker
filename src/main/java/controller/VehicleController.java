package controller;

import DAO.VehicleDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Vehicle;

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
    private ComboBox<String> vehicleTypeBox;

    private final VehicleDAO vehicleDAO = new VehicleDAO();

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    public void initialize() {
        vehicleTypeBox.getItems().addAll("Car", "Motorcycle", "Truck", "Van", "SUV");
    }

    @FXML
    private void handleSaveVehicle() {
        String ownerName = ownerNameField.getText().trim();
        String regNumber = regNumberField.getText().trim();
        String brand = brandField.getText().trim();
        String model = modelField.getText().trim();
        String vehicleType = vehicleTypeBox.getValue();

        if (ownerName.isEmpty() || regNumber.isEmpty() || brand.isEmpty() || model.isEmpty() || vehicleType == null) {
            showAlert("Please fill in all fields.");
            return;
        }

        Vehicle vehicle = new Vehicle(0, userId, ownerName, regNumber, brand, model, vehicleType);
        boolean success = vehicleDAO.addVehicle(vehicle);

        if (success) {
            showAlert("Vehicle saved successfully.");
            clearFields();
        } else {
            showAlert("Failed to save vehicle. Please try again.");
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

            Stage stage = (Stage) ownerNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Vehicle Maintenance Tracker - Dashboard");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        ownerNameField.clear();
        regNumberField.clear();
        brandField.clear();
        modelField.clear();
        vehicleTypeBox.setValue(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vehicle Form");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}