package com.vehicletacker.vehiclemaintenancetracker.DAO;
import com.vehicletacker.vehiclemaintenancetracker.util.DBAccess;
import com.vehicletacker.vehiclemaintenancetracker.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO {

    public boolean registerUser(User user) {

        String sql = """
                INSERT INTO users
                (full_name, Contact, email,username, password, role)
                               VALUES (?, ?, ?, ? ,?, ?)
                """;

        try (
                Connection connection =
                        DBAccess.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getUserContact());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPasswordHash());
            statement.setString(6, user.getRole());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    public User loginUser(String username, String enteredPassword) {

        String sql = "SELECT * FROM users WHERE username = ?";

        try (
                Connection connection = DBAccess.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String hashedPassword = resultSet.getString("password");

                if (BCrypt.checkpw(enteredPassword, hashedPassword)) {

                    User user = new User();

                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPasswordHash(hashedPassword);

                    return user;
                }
            }

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public boolean usernameExists(String username) {

        String sql = "SELECT user_id FROM users WHERE username = ?";

        try (
                Connection connection =
                        DBAccess.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            System.out.println("Username check error: " + e.getMessage());
            return false;
        }
    }

    public boolean emailExists(String email) {

        String sql = "SELECT user_id FROM users WHERE username = ?";

        try (
                Connection connection = DBAccess.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            System.out.println("Email check error: " + e.getMessage());
            return false;
        }
    }


    public boolean registerUser(String username, String email, String hashedPassword) {

        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, hashedPassword);

            int rows = pst.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    public boolean registerUser(String fullName,
                                String username,
                                String email,
                                String contact,
                                String hashedPassword,
                                String role) {

        String sql = "INSERT INTO user (full_name, username, email, Contact, password, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fullName);
            pstmt.setString(2, username);
            pstmt.setString(3, email);
            pstmt.setString(4, contact);
            pstmt.setString(5, hashedPassword);
            pstmt.setString(6, role);

            int rows = pstmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Registration error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean usernameOrEmailExists(String username, String email) {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, email);

            ResultSet rs = pstmt.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Check user error: " + e.getMessage());
            return false;
        }
    }
}

