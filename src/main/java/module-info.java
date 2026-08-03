module com.vehicletacker.vehiclemaintenancetracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;

    opens com.vehicletacker.vehiclemaintenancetracker.controller
            to javafx.fxml;

    exports com.vehicletacker.vehiclemaintenancetracker;
    exports com.vehicletacker.vehiclemaintenancetracker.controller;
}