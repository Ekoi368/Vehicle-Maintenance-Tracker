package controller;

import DAO.MaintenanceDAO;
import DAO.VehicleDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.MaintenanceRecord;
import model.Vehicle;

import java.io.IOException;
import java.util.List;

public class DashboardConroller {

    @FXML
    private ListView<Vehicle> vehicleListView;

    private final VehicleDAO vehicleDAO = new VehicleDAO();
    private final MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
        loadVehicles();
    }

    @FXML
    public void initialize() {
        vehicleListView.setCellFactory(list -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Vehicle vehicle, boolean empty) {
                super.updateItem(vehicle, empty);
                if (empty || vehicle == null) {
                    setText(null);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(vehicle.getBrand()).append(" ")
                            .append(vehicle.getModel())
                            .append(" (").append(vehicle.getRegNumber()).append(")\n");

                    List<MaintenanceRecord> records = maintenanceDAO.getRecordsByVehicleId(vehicle.getVehicleId());
                    if (records.isEmpty()) {
                        sb.append("   No maintenance records yet");
                    } else {
                        for (MaintenanceRecord record : records) {
                            sb.append("   - ").append(record.getServiceType())
                                    .append(" on ").append(record.getServiceDate())
                                    .append(" (₵").append(record.getCost()).append(")\n");
                        }
                    }
                    setText(sb.toString());
                }
            }
        });
    }

    private void loadVehicles() {
        List<Vehicle> vehicles = vehicleDAO.getVehiclesByUserId(userId);
        vehicleListView.getItems().setAll(vehicles);
    }

    @FXML
    private void handleAddVehicle() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/vehicletacker/vehiclemaintenancetracker/vehicle-form.fxml")
            );
            Parent root = loader.load();

            VehicleController vehicleController = loader.getController();
            vehicleController.setUserId(userId);

            Stage stage = getCurrentStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Vehicle");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddMaintenance() {
        Vehicle selected = vehicleListView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Please select a vehicle from the list first.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/vehicletacker/vehiclemaintenancetracker/maintenance-form.fxml")
            );
            Parent root = loader.load();

            MaintenanceController maintenanceController = loader.getController();
            maintenanceController.setUserId(userId);
            maintenanceController.setVehicleId(selected.getVehicleId());

            Stage stage = getCurrentStage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Maintenance Record");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stage getCurrentStage() {
        return (Stage) vehicleListView.getScene().getWindow();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Vehicle Selected");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}