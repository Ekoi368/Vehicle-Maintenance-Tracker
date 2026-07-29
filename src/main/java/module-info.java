module com.vehicletacker.vehiclemaintenancetracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vehicletacker.vehiclemaintenancetracker to javafx.fxml;
    exports com.vehicletacker.vehiclemaintenancetracker;
}