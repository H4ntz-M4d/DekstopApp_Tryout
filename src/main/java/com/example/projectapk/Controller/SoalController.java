package com.example.projectapk.Controller;
import com.example.projectapk.DB_Connection.DatabaseConnection;
import com.example.projectapk.DB_Connection.SoalConnection;
import com.example.projectapk.HelloApplication;
import com.example.projectapk.model.UserSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SoalController extends HelloController{
    @FXML
    private Label soalLabel;
    @FXML
    private RadioButton answerButton1;
    @FXML
    private RadioButton answerButton2;
    @FXML
    private RadioButton answerButton3;
    @FXML
    private RadioButton answerButton4;
    @FXML
    private RadioButton answerButton5;
    @FXML
    private HBox allButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button prevButton;
    @FXML
    private Button stopButton;

    // Connection Database
    private DatabaseConnection db = new DatabaseConnection();
    private SoalConnection connect = new SoalConnection();
    private ResultSet questions = connect.getQuestions();
    private ResultSet answers = connect.getAnswers();
    // Connection Database

    private RadioButton[] radioButtons;
    private String[] jawabanUser;
    private int currentQuestion = 0;
    private double score = 0;
    private int jawabanBenar = 0;
    private int jawabanSalah = 0;
    @FXML
    private Parent rootScoreView;
    @FXML
    private Button n[];

    @FXML
    private FlowPane allNumberButton;

    @FXML
    private Stage stage = new Stage();
    private int allQuestion;

    public void initPrimaryStage(Stage stage){
        this.stage = stage;
    }


    public void initialize(){

        try {
            Connection connection = db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM question");
            if (rs.next()){
                allQuestion = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        jawabanUser = new String[allQuestion + 1];

        radioButtons = new RadioButton[]{answerButton1,answerButton2,answerButton3,answerButton4,answerButton5};
        ToggleGroup group = new ToggleGroup();
        for (RadioButton answerButton : radioButtons) {
            answerButton.setToggleGroup(group);
        }

        for (int i = 0; i < 5; i++){
            final int index = i;
            radioButtons[i].setOnAction(e -> {
                System.out.println(jawabanUser[currentQuestion] = radioButtons[index].getText());
            });

        }

        nextButton = new Button("Next");
        nextButton.setOnAction(e -> {
            nextSoal();
            prevButton.setDisable(false);
        });

        prevButton = new Button("Prev");
        prevButton.setOnAction(e -> {
            prevSoal();
            nextButton.setDisable(false);
        });

        stopButton = new Button("Hentikan Test");
        stopButton.setOnAction(e -> stopTest());

        int countNumber = connect.getTotalNumber();
        n = new Button[countNumber];
        try {
            for (int i = 0; i < countNumber; i++){
                n[i] = new Button(Integer.toString(i + 1));
                n[i].setPrefHeight(40);
                n[i].setPrefWidth(40);
                final int id_soal = i + 1;
                n[i].setOnAction(event -> goToSoal(id_soal));
                allNumberButton.getChildren().add(n[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        nextSoal();

        allButton.getChildren().addAll(prevButton,nextButton,stopButton);
    }

    private void checkAnswer() throws SQLException {
        score = 0;
        try {
            for (int i = 1; i < jawabanUser.length; i++) {
                if (answers.next() && jawabanUser[i] != null && jawabanUser[i].equals(answers.getString("jawaban"))) {
                    score += 16.6;
                    jawabanBenar++;

                } else {
                    jawabanSalah++;
                }
            }

            UserSession.setJawabanBenar(jawabanBenar);
            UserSession.setJawabanSalah(jawabanSalah);
            UserSession.setNilaiTotal(score);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Connection con = db.getConnection();

        String query = "UPDATE nilai SET nilai = ? WHERE username = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setDouble(1,score);
        pst.setString(2,UserSession.getUsername());
        pst.executeUpdate();
        con.close();
        System.out.println("Total skor Anda adalah: " + score);
    }

    private void nextSoal() {
        try {
            if (questions.next()) {
                currentQuestion++;
                soalLabel.setText(questions.getString("soal"));
                for (int i = 0; i < 5; i++) {
                    radioButtons[i].setText(questions.getString("pilihan" + (i + 1)));
                    radioButtons[i].setSelected(radioButtons[i].getText().equals(jawabanUser[currentQuestion]));
                }
                prevButton.setDisable(true);
            } else {
                nextButton.setDisable(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void prevSoal() {
        try {
            if (questions.previous()) {
                currentQuestion--;
                soalLabel.setText(questions.getString("soal"));
                for (int i = 0; i < 5; i++) {
                    radioButtons[i].setText(questions.getString("pilihan" + (i + 1)));
                    radioButtons[i].setSelected(radioButtons[i].getText().equals(jawabanUser[currentQuestion]));
                }
                nextButton.setDisable(true);
            } else {
                prevButton.setDisable(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void stopTest(){
        try {
            checkAnswer();  // Hitung skor setelah test dihentikan
            FXMLLoader soalLoader = new FXMLLoader(HelloApplication.class.getResource("score-view.fxml"));
            try {
                rootScoreView = soalLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ScoreViewController controller = soalLoader.getController();

            Stage newStage = new Stage();
            controller.initPrimaryStage(newStage);

            Scene scene = new Scene(rootScoreView);
            newStage.setScene(scene);
            newStage.setMaximized(true);
            newStage.show();
            stage.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void goToSoal(int id_soal) {
        try {
            // Pindah ke soal dengan id_soal tertentu
            questions.absolute(id_soal);
            currentQuestion = id_soal;
            soalLabel.setText(questions.getString("soal"));
            for (int i = 0; i < 5; i++) {
                radioButtons[i].setText(questions.getString("pilihan" + (i + 1)));
                radioButtons[i].setSelected(radioButtons[i].getText().equals(jawabanUser[currentQuestion]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}