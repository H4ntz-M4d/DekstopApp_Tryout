package com.example.projectapk.Controller;

import com.example.projectapk.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MenuController {
    @FXML
    private Parent rootSoal;
    @FXML
    private Parent rootScore;
    @FXML
    private Stage stage = new Stage();
    @FXML
    private Button TryOut1;
    @FXML
    private Button TryOut2;
    @FXML
    private Button btnLogout;

    @FXML
    public void initPrimaryStage(Stage stage){
        this.stage = stage;
    }

    public void initialize(){
        TryOut1.setOnAction(event -> klikKerjakanUTBKIpa());
        TryOut2.setDisable(true);
        btnLogout.setOnAction(event -> LogOut());
    }

    @FXML
    public void klikKerjakanUTBKIpa(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Kerjakan Ujian");
        alert.setContentText("Apakah Anda yakin ingin memulai ujian?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            FXMLLoader soalLoader = new FXMLLoader(HelloApplication.class.getResource("soal-layout.fxml"));
            try {
                rootSoal = soalLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            SoalController controller = soalLoader.getController();
            Stage newStage = new Stage();
            controller.initPrimaryStage(newStage);

            Scene scene = new Scene(rootSoal);
            newStage.setScene(scene);
            newStage.setMaximized(true);
            newStage.show();
            stage.close();
        } else {
            // user chose CANCEL or closed the dialog
            System.out.println("Pengguna membatalkan ujian.");
        }
    }


    @FXML
    public void klikMyScore(){

        FXMLLoader scoreMenu = new FXMLLoader(HelloApplication.class.getResource("score-layout.fxml"));
        try {
            rootScore = scoreMenu.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ScoreController controller = scoreMenu.getController();

        Stage newStage = new Stage();
        controller.initPrimaryStage(newStage);

        Scene scene = new Scene(rootScore);
        newStage.setScene(scene);
        newStage.setMaximized(true);
        newStage.show();
        stage.close();
    }

    @FXML
    public void LogOut(){
        FXMLLoader loginLoader = new FXMLLoader(HelloApplication.class.getResource("loginUser-layout.fxml"));
        try {
            rootScore = loginLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HelloController controller = loginLoader.getController();
        Stage newStage = new Stage();

        controller.initPrimaryStage(newStage);
        Scene scene = new Scene(rootScore);
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.show();
        stage.close();
    }

    @FXML
    public void klikAbout(){
        FXMLLoader aboutMenu = new FXMLLoader(HelloApplication.class.getResource("about-view.fxml"));
        try {
            rootScore = aboutMenu.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AboutController controller = aboutMenu.getController();

        Stage newStage = new Stage();
        controller.initPrimaryStage(newStage);

        Scene scene = new Scene(rootScore);
        newStage.setScene(scene);
        newStage.setMaximized(true);
        newStage.show();
        stage.close();
    }
}
