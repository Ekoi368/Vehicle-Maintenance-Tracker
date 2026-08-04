package com.vehicletacker.vehiclemaintenancetracker.DAO;

import com.vehicletacker.vehiclemaintenancetracker.model.MaintenanceRecord;
import com.vehicletacker.vehiclemaintenancetracker.util.DBAccess;
import com.vehicletacker.vehiclemaintenancetracker.util.DBAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaintenanceDAO {

    public boolean addRecord(MaintenanceRecord record) {
        String sql = """
                INSERT INTO maintenance_records
                (
                    vehicle_id,
                    service_type,
                    mechanic_name,
                    description,
                    service_date,
                    cost
                )
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DBAccess.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setInt(1, record.getVehicleId());
            statement.setString(2, record.getServiceType());
            statement.setString(3, record.getMechanicName());
            statement.setString(4, record.getDescription());
            statement.setDate(
                    5,
                    Date.valueOf(record.getServiceDate())
            );
            statement.setDouble(6, record.getCost());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(
                    "Error adding maintenance record: "
                            + e.getMessage()
            );
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateRecord(MaintenanceRecord record) {
        String sql = """
                UPDATE maintenance_records
                SET vehicle_id = ?,
                    service_type = ?,
                    mechanic_name = ?,
                    description = ?,
                    service_date = ?,
                    cost = ?
                WHERE maintenance_id = ?
                """;

        try (
                Connection connection = DBAccess.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setInt(1, record.getVehicleId());
            statement.setString(2, record.getServiceType());
            statement.setString(3, record.getMechanicName());
            statement.setString(4, record.getDescription());
            statement.setDate(
                    5,
                    Date.valueOf(record.getServiceDate())
            );
            statement.setDouble(6, record.getCost());
            statement.setInt(7, record.getMaintenanceId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(
                    "Error updating maintenance record: "
                            + e.getMessage()
            );
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRecord(int maintenanceId) {
        String sql = """
                DELETE FROM maintenance_records
                WHERE maintenance_id = ?
                """;

        try (
                Connection connection = DBAccess.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setInt(1, maintenanceId);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(
                    "Error deleting maintenance record: "
                            + e.getMessage()
            );
            e.printStackTrace();
            return false;
        }
    }
}