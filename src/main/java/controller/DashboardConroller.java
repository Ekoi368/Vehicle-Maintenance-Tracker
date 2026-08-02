package controller;

import DAO.MaintenanceDAO;
import DAO.VehicleDAO;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.MaintenanceRecord;
import model.Vehicle;

import java.util.List;

public class DashboardConroller {

    @FXML
    private ListView<Vehicle> vehicleListView;

    private final VehicleDAO vehicleDAO = new VehicleDAO();
    private final MaintenanceDAO maintenanceDAO = new MaintenanceDAO();

    private int userId; // set after login

    public void setUserId(int userId) {
        this.userId = userId;
        loadVehicles();
    }

    @FXML
    public void initialize() {
        // Custom cell to show vehicle info + its maintenance records
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
}