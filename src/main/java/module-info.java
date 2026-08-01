module com.vehicletacker.vehiclemaintenancetracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.vehicletacker.vehiclemaintenancetracker to javafx.fxml;
    opens controller to javafx.fxml;
    exports com.vehicletacker.vehiclemaintenancetracker;
    exports controller;
}