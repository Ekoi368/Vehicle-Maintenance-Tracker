package model;

import java.time.LocalDate;

public class MaintenanceRecord {
    private int maintenanceId, vehicleId;
    private String serviceType, mechanicName, description;
    private LocalDate serviceDate;
    private double cost;

    public MaintenanceRecord() {
    }


    public MaintenanceRecord(int maintenanceId, int vehicleId, String serviceType, String mechanicName, String description, LocalDate serviceDate, double cost) {
        this.maintenanceId = maintenanceId;
        this.vehicleId = vehicleId;
        this.serviceType = serviceType;
        this.mechanicName = mechanicName;
        this.description = description;
        this.serviceDate = serviceDate;
        this.cost = cost;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }



    @Override
    public String toString() {
        return "MaintenanceRecord{" +
                "maintenanceId=" + maintenanceId +
                ", vehicleId=" + vehicleId +
                ", serviceType='" + serviceType + '\'' +
                ", mechanicName='" + mechanicName + '\'' +
                ", description='" + description + '\'' +
                ", serviceDate=" + serviceDate +
                ", cost=" + cost +
                '}';


    }

}
}
