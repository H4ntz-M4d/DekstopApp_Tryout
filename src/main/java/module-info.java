module com.example.projectapk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires mysql.connector.j;
    requires java.desktop;

    opens com.example.projectapk to javafx.fxml;
    exports com.example.projectapk;
    exports com.example.projectapk.DB_Connection;
    opens com.example.projectapk.DB_Connection to javafx.fxml;
    exports com.example.projectapk.Controller;
    opens com.example.projectapk.Controller to javafx.fxml;
    exports com.example.projectapk.model;
    opens com.example.projectapk.model to javafx.fxml;
}