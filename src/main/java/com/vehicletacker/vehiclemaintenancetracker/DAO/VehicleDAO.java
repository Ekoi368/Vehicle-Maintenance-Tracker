
package com.vehicletacker.vehiclemaintenancetracker.DAO;

import com.vehicletacker.vehiclemaintenancetracker.model.Vehicle;
import com.vehicletacker.vehiclemaintenancetracker.util.DBAccess;
import com.vehicletacker.vehiclemaintenancetracker.util.DBAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    public boolean addVehicle(Vehicle vehicle) {

        String sql = """
                INSERT INTO vehicles
                (owner_name, registration_number, brand, model, vehicle_type)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vehicle.getOwnerName());
            ps.setString(2, vehicle.getRegNumber());
            ps.setString(3, vehicle.getBrand());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getVehicleType());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVehicle(Vehicle vehicle) {

        String sql = """
                UPDATE vehicles
                SET owner_name=?,
                    registration_number=?,
                    brand=?,
                    model=?,
                    vehicle_type=?
                WHERE vehicle_id=?
                """;

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, vehicle.getOwnerName());
            ps.setString(2, vehicle.getRegNumber());
            ps.setString(3, vehicle.getBrand());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getVehicleType());
            ps.setInt(6, vehicle.getVehicleId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVehicle(int vehicleId) {

        String sql = "DELETE FROM vehicles WHERE vehicle_id=?";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vehicleId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Vehicle> getAllVehicles() {

        List<Vehicle> vehicles = new ArrayList<>();

        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DBAccess.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Vehicle vehicle = new Vehicle(
                        rs.getInt("vehicleID"),
                        rs.getString("owner_name"),
                        rs.getString("registration_number"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("vehicle_type")
                );

                vehicles.add(vehicle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }
}