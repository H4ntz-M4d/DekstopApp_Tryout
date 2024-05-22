package com.example.projectapk.Controller;

import com.example.projectapk.DB_Connection.SoalConnection;
import com.example.projectapk.HelloApplication;
import com.example.projectapk.model.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class ScoreViewController {

    @FXML
    private Label usernameLabel = new Label();
    @FXML
    private Label nameLabel = new Label();
    @FXML
    private Label emailLabel = new Label();
    @FXML
    private Label labelBenar = new Label();
    @FXML
    private Label labelSalah = new Label();
    @FXML
    private Label labelNilai = new Label();
    private SoalConnection sc = new SoalConnection();
    private ResultSet rs = sc.scoreView();
    private StackPane root;
    private Parent rootMenu;
    @FXML
    private Stage stage = new Stage();

    public void initPrimaryStage(Stage stage){
        this.stage = stage;
    }

    public void initialize(){
        try {

            if (rs.next()){
                usernameLabel.setText(rs.getString("username"));
                nameLabel.setText(rs.getString("name"));
                emailLabel.setText(rs.getString("email"));
            }
            System.out.println("Jawaban Benar: " + UserSession.getJawabanBenar());
            System.out.println("Jawaban Salah: " + UserSession.getJawabanSalah());
            System.out.println("Nilai Total: " + UserSession.getNilaiTotal());

            labelBenar.setText(String.valueOf(Integer.valueOf(UserSession.getJawabanBenar())));
            labelSalah.setText(String.valueOf(Integer.valueOf(UserSession.getJawabanSalah())));
            labelNilai.setText(String.valueOf(Math.round(UserSession.getNilaiTotal())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void KlikBackButton(){
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
        stage.close();
    }

}
