package model;

public class Vehicle {
    private int vehicleId,userId;
    private String ownerName,regNumber,brand,model,vehicleType;

    public Vehicle() {
    }



    public Vehicle(int vehicleId, int userId, String ownerName, String regNumber, String brand, String model, String vehicleType) {
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.ownerName = ownerName;
        this.regNumber = regNumber;
        this.brand = brand;
        this.model = model;
        this.vehicleType = vehicleType;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", userId=" + userId +
                ", ownerName='" + ownerName + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }



    private int vehicleId, userId;
    private String ownerName, regNumber, brand, model, vehicleType;
}
