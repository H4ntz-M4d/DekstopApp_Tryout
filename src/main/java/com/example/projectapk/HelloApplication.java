package com.example.projectapk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        // ----------------------------- Inisialisasi Stage --------------------------------------


        Image icon = new Image("C:\\Users\\ahmad\\IdeaProjects\\ProjectApk\\src\\logo.png");

        FXMLLoader loginLoader = new FXMLLoader(HelloApplication.class.getResource("loginUser-layout.fxml"));
        Parent rootLogin = loginLoader.load();

        Scene scene = new Scene(rootLogin);

        stage.getIcons().add(icon);
        stage.setResizable(false);
        stage.setTitle("BROUT!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getPrimaryStage() { // Tambahkan metode getter untuk Stage utama
        return primaryStage;
    }
}






//        Screen screen = Screen.getPrimary();
//        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
//        stage.setX(bounds.getMinX());
//        stage.setY(bounds.getMinY());
//        stage.setWidth(bounds.getWidth());
//        stage.setHeight(bounds.getHeight());


//        FXMLLoader menuLoader = new FXMLLoader(HelloApplication.class.getResource("menu-layout.fxml"));
//        Parent rootMenu = menuLoader.load();
//
//        FXMLLoader scoreLoader = new FXMLLoader(HelloApplication.class.getResource("score-layout.fxml"));
//        Parent rootScore = scoreLoader.load();
//
//        FXMLLoader soalLoader = new FXMLLoader(HelloApplication.class.getResource("soal-layout.fxml"));
//        Parent rootSoal = soalLoader.load();
//
//        FXMLLoader scoreViewLoader = new FXMLLoader(HelloApplication.class.getResource("score-view.fxml"));
//        Parent rootScoreView = scoreViewLoader.load();
//
//        StackPane root = new StackPane();

//        root.getChildren().addAll(rootLogin, rootMenu, rootSoal, rootScore, rootScoreView);
// ----------------------------- Inisialisasi Stage --------------------------------------


// Menyembunyikan semua layout kecuali rootLogin
//        for (Node layout : root.getChildren()) {
//            layout.setVisible(false);
//        }
//        rootLogin.setVisible(true);
// Menyembunyikan semua layout kecuali rootLogin


// Menetapkan Stage dan layout baru ke controller
//        HelloController controller = loginLoader.getController();
//        controller.setRootAndRootMenu(root, rootMenu);
//
//        MenuController controller1 = menuLoader.getController();
//        controller1.setRootAndRootSoal(root, rootSoal, rootScore);
//
//        ScoreController controller2 = scoreLoader.getController(); //Back Button
//        controller2.setRootAndRootMenu(root, rootMenu);
//
//        CobaController controller3 = soalLoader.getController();
//        controller3.setRootAndRootScore(root, rootScoreView);
//
//        ScoreViewController controller4 = scoreViewLoader.getController(); //Back Button
//        controller4.setRootAndRootMenu(root, rootMenu);

// Menetapkan Stage dan layout baru ke controller