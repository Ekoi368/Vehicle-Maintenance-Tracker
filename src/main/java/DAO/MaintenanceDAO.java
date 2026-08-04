package DAO;

import model.MaintenanceRecord;
import util.DBAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAO {

    private final Connection connection;

    public MaintenanceDAO() {
        this.connection = DBAccess.getInstance().getconnection();
    }

    // Create
    public boolean addRecord(MaintenanceRecord record) {
        String sql = "INSERT INTO maintenance_record (vehicleID, mechanic_name, service_type, cost, description, service_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, record.getVehicleId());
            ps.setString(2, record.getMechanicName());
            ps.setString(3, record.getServiceType());
            ps.setDouble(4, record.getCost());
            ps.setString(5, record.getDescription());
            ps.setDate(6, record.getServiceDate() != null ? Date.valueOf(record.getServiceDate()) : null);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read - all records for a vehicle
    public List<MaintenanceRecord> getRecordsByVehicleId(int vehicleId) {
        List<MaintenanceRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_record WHERE vehicleID = ? ORDER BY service_date DESC";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, vehicleId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                records.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    // Read - single record
    public MaintenanceRecord getRecordById(int maintenanceId) {
        String sql = "SELECT * FROM maintenance_record WHERE maintenanceID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, maintenanceId);
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
    public boolean updateRecord(MaintenanceRecord record) {
        String sql = "UPDATE maintenance_record SET mechanic_name=?, service_type=?, cost=?, description=?, service_date=? WHERE maintenanceID=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, record.getMechanicName());
            ps.setString(2, record.getServiceType());
            ps.setDouble(3, record.getCost());
            ps.setString(4, record.getDescription());
            ps.setDate(5, record.getServiceDate() != null ? Date.valueOf(record.getServiceDate()) : null);
            ps.setInt(6, record.getMaintenanceId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean deleteRecord(int maintenanceId) {
        String sql = "DELETE FROM maintenance_record WHERE maintenanceID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, maintenanceId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private MaintenanceRecord mapRow(ResultSet rs) throws SQLException {
        return new MaintenanceRecord(
                rs.getInt("maintenanceID"),
                rs.getInt("vehicleID"),
                rs.getString("service_type"),
                rs.getString("mechanic_name"),
                rs.getString("description"),
                rs.getDate("service_date").toLocalDate(),
                rs.getDouble("cost")
        );
    }
}