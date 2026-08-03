package com.vehicletacker.vehiclemaintenancetracker.model;

public class User {

    private int userId;
    private String fullName;
    private String userContact;
    private String email;
    private String username;
    private String passwordHash;
    private String role;

    // Empty constructor
    public User() {
    }

    // Constructor used when reading a complete user from the database
    public User(
            int userId,
            String fullName,
            String userContact,
            String email,
            String username,
            String passwordHash,
            String role
    ) {
        this.userId = userId;
        this.fullName = fullName;
        this.userContact = userContact;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Constructor used during sign up before MySQL generates the user ID
    public User(
            String fullName,
            String userContact,
            String email,
            String username,
            String passwordHash,
            String role
    ) {
        this.fullName = fullName;
        this.userContact = userContact;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", userContact='" + userContact + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public void setPassword(String hashedPassword) {
    }
}