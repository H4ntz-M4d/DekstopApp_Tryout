package com.example.projectapk.Controller;

import com.example.projectapk.DB_Connection.DatabaseConnection;
import com.example.projectapk.HelloApplication;
import com.example.projectapk.model.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField emailTextField1;
    @FXML
    private TextField passwordTextField1;
    @FXML
    private Parent rootMenu;
    @FXML
    private Button loginButton2;
    @FXML
    private Button btnCancel;
    @FXML
    private Stage stage = new Stage();
    public void initPrimaryStage(Stage stage){
        this.stage = stage;
    }


    public void initialize(){
        loginButton2.setOnAction(event -> {
            login();

        });
        btnCancel.setOnAction(event -> cancel());
    }

    public void cancel(){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void login() {

        try {
            if (validateLogin()) {
                FXMLLoader menuLoader = new FXMLLoader(HelloApplication.class.getResource("menu-layout.fxml"));
                try {
                    rootMenu = menuLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                MenuController controller = menuLoader.getController();

                Stage newStage = new Stage();
                controller.initPrimaryStage(newStage);

                Scene scene = new Scene(rootMenu);
                newStage.setScene(scene);
                newStage.setMaximized(true);
                newStage.show();
                closePreviousStage();

                if (HelloController.getStage() == null){
                    HelloApplication.getPrimaryStage().close();
                } else if (HelloApplication.getPrimaryStage() != null){
                    HelloController.getStage().close();
                }

            } else if (emailTextField1.getText().equals("AdminTryout") && passwordTextField1.getText().equals("1234321")) {
                FXMLLoader adminLoader = new FXMLLoader(HelloApplication.class.getResource("adminPanel.fxml"));
                try {
                    rootMenu = adminLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                AdminPanel controller = adminLoader.getController();

                Stage newStage = new Stage();
                controller.initPrimaryStage(newStage);

                Scene scene = new Scene(rootMenu);
                newStage.setScene(scene);
                newStage.setResizable(false);
                newStage.show();
                closePreviousStage();

                if (HelloController.getStage() == null){
                    HelloApplication.getPrimaryStage().close();
                } else if (HelloApplication.getPrimaryStage() != null){
                    HelloController.getStage().close();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Peringatan");
                alert.setHeaderText(null);
                alert.setContentText("Username atau password salah!");

                alert.showAndWait();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean validateLogin() throws SQLException {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();

        String username = emailTextField1.getText();
        String password = passwordTextField1.getText();

        String query = "SELECT * FROM dataStudent WHERE username = ? AND password = ?";
        PreparedStatement pst = connectDB.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();


        if(rs.next()) {
            UserSession.setUsername(username);
            System.out.println("berhasil");
            return true;
        } else {
            System.out.println("gagal");
            return false;
        }
    }
    private void closePreviousStage() {
        // Dapatkan stage sebelumnya dari scene yang sedang ditampilkan
        Stage previousStage = (Stage) loginButton2.getScene().getWindow();

        // Tutup stage sebelumnya
        previousStage.close();
    }

    @FXML
    public void handleKeyRelease1() {
        String email = emailTextField1.getText();
        String password = passwordTextField1.getText();

        boolean disableButton = email.isEmpty() || password.isEmpty() || email.trim().isEmpty() || password.trim().isEmpty();
        loginButton2.setDisable(disableButton);
    }
}
