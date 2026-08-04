package DAO;

import com.vehicletacker.vehiclemaintenancetracker.model.Vehicle;
import com.vehicletacker.vehiclemaintenancetracker.util.DBAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO{

    private final Connection connection;

    public VehicleDAO() {
        this.connection = DBAccess.getInstance().getconnection();
    }

    // Create
    public boolean addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicle (userID, owner_name, reg_number, brand, model, vehicle_type) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, vehicle.getUserId());
            ps.setString(2, vehicle.getOwnerName());
            ps.setString(3, vehicle.getRegNumber());
            ps.setString(4, vehicle.getBrand());
            ps.setString(5, vehicle.getModel());
            ps.setString(6, vehicle.getVehicleType());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read - all vehicles for a user
    public List<Vehicle> getVehiclesByUserId(int userId) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicle WHERE userID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vehicles.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Read - single vehicle
    public Vehicle getVehicleById(int vehicleId) {
        String sql = "SELECT * FROM vehicle WHERE vehicleID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update
    public boolean updateVehicle(Vehicle vehicle) {
        String sql = "UPDATE vehicle SET owner_name=?, reg_number=?, brand=?, model=?, vehicle_type=? WHERE vehicleID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
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

    // Delete
    public boolean deleteVehicle(int vehicleId) {
        String sql = "DELETE FROM vehicle WHERE vehicleID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Vehicle mapRow(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getInt("vehicleID"),
                rs.getInt("userID"),
                rs.getString("owner_name"),
                rs.getString("reg_number"),
                rs.getString("brand"),
                rs.getString("model"),
                rs.getString("vehicle_type")
        );
    }
}