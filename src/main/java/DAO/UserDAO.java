package DAO;

import model.User;
import util.DBAccess;

import java.sql.*;

public class UserDAO {

    private final Connection connection;

    public UserDAO() {
        this.connection = DBAccess.getInstance().getconnection();
    }

    // Used by LoginController
    public User validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE user_name = ? AND password_hash = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Used by SignupController
    public boolean addUser(User user) {
        String sql = "INSERT INTO users (full_name, phone, email, user_name, password_hash, role) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, String.valueOf(user.getUserContact()));
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getRole());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private User mapRow(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("userID"),
                Integer.parseInt(rs.getString("phone")),
                rs.getString("full_name"),
                rs.getString("user_name"),
                rs.getString("password_hash"),
                rs.getString("email"),
                rs.getString("role")
        );
    }
}