package model;

import java.time.LocalDate;

public class Reminders {
    private int reminderId,vehicleId;
    private String serviceType,staus;
    private LocalDate dueDate;

    public Reminders() {
    }

    public Reminders(int reminderId, String serviceType, int vehicleId, String staus, LocalDate dueDate) {
        this.reminderId = reminderId;
        this.serviceType = serviceType;
        this.vehicleId = vehicleId;
        this.staus = staus;
        this.dueDate = dueDate;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStaus() {
        return staus;
    }

    public void setStaus(String staus) {
        this.staus = staus;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
