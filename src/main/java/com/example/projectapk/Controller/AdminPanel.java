package com.example.projectapk.Controller;

import com.example.projectapk.DB_Connection.DatabaseConnection;
import com.example.projectapk.DB_Connection.MysqlDB;
import com.example.projectapk.model.Quiz;
import com.example.projectapk.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class AdminPanel {

    // Quiz add
    public DatabaseConnection db = new DatabaseConnection();
    @FXML
    public TextArea quizQuestion;
    @FXML
    public TextField option1;
    @FXML
    public TextField option2;
    @FXML
    public TextField option3;
    @FXML
    public TextField option4;
    @FXML
    public TextField option5;
    @FXML
    public RadioButton radioButton_1;
    @FXML
    public RadioButton radioButton_2;
    @FXML
    public RadioButton radioButton_3;
    @FXML
    public RadioButton radioButton_4;
    @FXML
    public RadioButton radioButton_5;
    @FXML
    public TextField quizID;

    @FXML private ComboBox<String> materiComboBox;

    @FXML
    private ToggleGroup toggleGroup;


    @FXML
    public TextArea displayDB;


    // Student add & show
    @FXML
    private TextField stdID;
    @FXML
    private TextField stdName;
    @FXML
    private PasswordField studentPassword;
    @FXML
    private TextField userName;
    @FXML
    private TextArea detailsLabel;
    @FXML
    private TextField studentIDforDetails;

    // Show DB section
    @FXML
    public TableView<Student> table;
    @FXML
    public TableColumn<Student, String> col_stdID;
    @FXML
    public TableColumn<Student, String> col_name;
    @FXML
    public TableColumn<Student, String> col_uname;
    @FXML
    public TableColumn<Student, String> col_points;

    public void conformationAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successful");
        alert.setContentText("Successfully Added to Database");
        alert.show();
    }
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Username or pass is wrong\nPlease try again");
        alert.show();
    }

    public void cancelButton(ActionEvent actionEvent) {
        stdID.setText("");
        stdName.setText("");
        userName.setText("");
        studentPassword.setText("");
    }

    public void notFoundAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Not found on database Student ID is wrong\nPlease try again");
        alert.show();
    }

    public void foundAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Found on database");
        alert.setContentText("Successfully Found on database");
        alert.show();
    }

    public void adminStdSubmit(ActionEvent actionEvent) {
        //submit to database

        try {
            String id = stdID.getText();
            String name = stdName.getText();
            String uName = userName.getText();
            String pass = studentPassword.getText();

            Student student = new Student(id, name, uName, pass);
            boolean check = MysqlDB.InsertToDB(student);

            if (check) {
                //show alert
                conformationAlert();
            } else {
                showAlert();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearField();
    }

    @FXML
    public void showDetailButton(ActionEvent actionEvent) {
        String id = studentIDforDetails.getText();

        detailsLabel.setText(MysqlDB.getInfo(id));

        if(MysqlDB.getInfo(id) == null){
            notFoundAlert();
        }
        else {
            foundAlert();
        }
    }


    public void clearField() {
        stdID.setText("");
        stdName.setText("");
        userName.setText("");
        studentPassword.setText("");
    }

    public void clearQuetionField(){
        quizID.clear();
        quizQuestion.clear();
        option1.clear();
        option2.clear();
        option3.clear();
        option4.clear();
        option5.clear();
    }

    // ******** Quiz section *********

    public void initialize(){
        Connection connection = db.getConnection();
        try {
            String query = "SELECT kd_materi FROM learning";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                materiComboBox.getItems().add(rs.getString("kd_materi"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void radioButtonSetup(){
        toggleGroup = new ToggleGroup();
        radioButton_1.setToggleGroup(toggleGroup);
        radioButton_2.setToggleGroup(toggleGroup);
        radioButton_3.setToggleGroup(toggleGroup);
        radioButton_4.setToggleGroup(toggleGroup);
        radioButton_5.setToggleGroup(toggleGroup);
    }

    public void quizSubmitButton(ActionEvent actionEvent) {
        Integer id = Integer.parseInt(quizID.getText());
        String title = materiComboBox.getSelectionModel().getSelectedItem();
        String question = quizQuestion.getText();
        String option_1 = option1.getText();
        String option_2 = option2.getText();
        String option_3 = option3.getText();
        String option_4 = option4.getText();
        String option_5 = option5.getText();

        String answer = "";

        radioButtonSetup();

        if (radioButton_1.isSelected()) {
            answer = option_1;
        } else if (radioButton_2.isSelected()) {
            answer = option_2;
        } else if (radioButton_3.isSelected()) {
            answer = option_3;
        } else if (radioButton_4.isSelected()) {
            answer = option_4;
        } else if (radioButton_5.isSelected()) {
            answer = option_5;
        }

        Quiz quiz = new Quiz(id, title, question, option_1, option_2, option_3, option_4, option_5, answer);

        try {
            if(MysqlDB.InsertToQuizDB(quiz)){
                clearQuetionField();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void quizCancelButton(ActionEvent actionEvent) {
        clearQuetionField();
    }

    // ****** Show data *****

    ObservableList<Student> list = FXCollections.observableArrayList();

    public void showAllData(ActionEvent actionEvent) {

        Connection connection = db.getConnection();
        try {
            // getting from database
            ResultSet rs = connection.createStatement().executeQuery("select s.username, s.name, s.email, n.nilai from datastudent s join nilai n on s.username = n.username");
            // this is data is adding to table from database
            while (rs.next()){
                list.add(new Student (
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt( "nilai")));

                table.setItems(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // this information is getting from Student Class
        col_stdID.setCellValueFactory(new PropertyValueFactory<>("Username"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_uname.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        col_points.setCellValueFactory(new PropertyValueFactory<>("quizPoint"));
    }


    // ****** Delete Student & quiz *****
    public Stage stage;
    public void initPrimaryStage(Stage stage){
        this.stage = stage;
    }

}
