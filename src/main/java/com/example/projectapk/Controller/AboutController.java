package com.example.projectapk.Controller;

import com.example.projectapk.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutController{
    @FXML
    private Stage stage;
    public void initPrimaryStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private void webPolinema(ActionEvent event) {
        String url = "https://www.polinema.ac.id/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    @FXML
    private void instaPolinema(ActionEvent event) {
        String url = "https://www.instagram.com/polinema_campus/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    @FXML
    private void instaIlham(ActionEvent event) {
        String url = "https://www.instagram.com/rayyailhamf_15/";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    @FXML
    private void instaInce(ActionEvent event) {
        String url = "https://www.instagram.com/ahmad_muhadi18?igshid=YTQwZjQ0NmI0OA==";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    @FXML
    private void instaIqbal(ActionEvent event) {
        String url = "https://instagram.com/iqbalmaulana.25?igshid=OGQ5ZDc2ODk2ZA==";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
    @FXML
    private void instaYuda(ActionEvent event) {
        String url = "https://www.instagram.com/_jadulll?utm_source=qr&igshid=ZDExYjZkNGI0OA==";
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    @FXML
    private Parent rootMenu;
    @FXML
    public void BackIconButton(){
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

