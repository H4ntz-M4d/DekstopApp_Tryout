package com.example.projectapk.Controller;

import com.example.projectapk.DB_Connection.DatabaseConnection;
import com.example.projectapk.HelloApplication;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {

//    @FXML
//    private TextField emailTextField;
//    @FXML
//    private TextField passwordTextField;
//
//    @FXML
//    private TextField emailTextField1;
//    @FXML
//    private TextField passwordTextField1;
    @FXML
    private TextField emailTextField2;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField passwordTextField2;


    @FXML
    private Button loginButton1;
    @FXML
    private Parent rootLogin;
    @FXML
    private TextField usernameTextField;

    @FXML
    private static Stage stage;
    @FXML
    private BorderPane loginScene;

    public static Stage getStage() {
        return stage;
    }

    public void initPrimaryStage(Stage stage){
        this.stage = stage;
    }

    public void initialize(){
        loginButton1.setOnAction(event -> loginScene());
    }

    public void loginScene(){

        // Buat stage baru
        FXMLLoader loginLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        try {
            rootLogin = loginLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoginController controller = loginLoader.getController();

        controller.initPrimaryStage(stage);

        Stage newStage = new Stage();
        Scene scene = new Scene(rootLogin);
        newStage.setScene(scene);
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.showAndWait();
    }

    @FXML
    private Label inform = new Label();
    public void clear(){

    }
    @FXML
    public void klikRegister() throws SQLException {

        PreparedStatement pst = null;
        PreparedStatement pstInsert = null;
        PreparedStatement pstInsertnilai = null;
        ResultSet resultSet = null;

        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDB = connectnow.getConnection();

        String username = usernameTextField.getText();
        String name = nameTextField.getText();
        String email = emailTextField2.getText();
        String password = passwordTextField2.getText();

        String queryCheck = "SELECT * FROM dataStudent WHERE username = ?";
        pst = connectDB.prepareStatement(queryCheck);
        pst.setString(1, username);
        resultSet = pst.executeQuery();

        if (resultSet.isBeforeFirst()){
            System.out.println("User is Already exists!");
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("You cannot use this username!!!");
            error.show();
            
        } else if (username.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            inform.setText("You have an empty column");
            inform.setVisible(true);

            PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(3)
            );
            visiblePause.setOnFinished(
                    event -> inform.setVisible(false)
            );
            visiblePause.play();
        } else {

            pstInsert = connectDB.prepareStatement("INSERT INTO dataStudent (username, name, email, password) VALUES (?, ?, ?, ?)");

            pstInsert.setString(1, username);
            pstInsert.setString(2, name);
            pstInsert.setString(3, email);
            pstInsert.setString(4, password);

            pstInsert.executeUpdate();

            pstInsertnilai = connectDB.prepareStatement("INSERT INTO nilai (username) VALUES (?)");
            pstInsertnilai.setString(1, username);

            pstInsertnilai.executeUpdate();

            inform.setText("Registration success!. Please Sign In");
            inform.setVisible(true);

            PauseTransition visiblePause = new PauseTransition(
                    Duration.seconds(2)
            );
            visiblePause.setOnFinished(
                    event -> {
                        inform.setVisible(false);
                        usernameTextField.clear();
                        nameTextField.clear();
                        emailTextField2.clear();
                        passwordTextField2.clear();
                    }
            );
            visiblePause.play();
        }
        connectDB.close();
    }
}

