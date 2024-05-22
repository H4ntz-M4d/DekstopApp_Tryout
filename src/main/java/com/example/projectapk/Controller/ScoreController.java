package com.example.projectapk.Controller;

import com.example.projectapk.DB_Connection.DatabaseConnection;
import com.example.projectapk.HelloApplication;
import com.example.projectapk.model.DataNilai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScoreController extends HelloController{
    @FXML
    private TableView<DataNilai> dataTable;
    @FXML
    private TableColumn<DataNilai, Integer> no;
    @FXML
    private TableColumn<DataNilai, String> username;
    @FXML
    private TableColumn<DataNilai, Integer> nilai;
    @FXML
    private Stage stage = new Stage();
    @FXML
    private Parent rootMenu;
    @FXML
    private Button BackIconButton;
    private DatabaseConnection connectNow = new DatabaseConnection();
    private Connection connectScore = connectNow.getConnection();


    public void initialize() {

        try {
            ObservableList<DataNilai> nilaiData = FXCollections.observableArrayList();

            String query = "SELECT * FROM nilai";
            Statement st = connectScore.prepareStatement(query);
            ResultSet rs = st.executeQuery(query);

            while (rs.next()){
                nilaiData.add(new DataNilai(
                        rs.getInt("no"),
                        rs.getString("username"),
                        rs.getInt("nilai")));
            }

            no.setCellValueFactory(new PropertyValueFactory<>("no"));
            username.setCellValueFactory(new PropertyValueFactory<>("username"));
            nilai.setCellValueFactory(new PropertyValueFactory<>("nilai"));

            dataTable.setItems(nilaiData);
        } catch (SQLException e){
            e.printStackTrace();
        }

        BackIconButton.setOnAction(event -> KlikBackButton());
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
        closePreviousStage();
    }

    private void closePreviousStage() {
        // Dapatkan stage sebelumnya dari scene yang sedang ditampilkan
        Stage previousStage = (Stage) BackIconButton.getScene().getWindow();

        // Tutup stage sebelumnya
        previousStage.close();
    }
}
