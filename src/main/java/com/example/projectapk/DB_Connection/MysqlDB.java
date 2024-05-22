package com.example.projectapk.DB_Connection;

import com.example.projectapk.model.Quiz;
import com.example.projectapk.model.Student;
import javafx.scene.control.Alert;

import java.sql.*;

public class MysqlDB {
    static Connection con;
    boolean flag = false;
    public static DatabaseConnection db = new DatabaseConnection();

    public static void create() throws ClassNotFoundException {
        //load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/projectapk";
        // create the connection
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean InsertToDB(Student std) throws SQLException {
        //jdbc code here
        boolean flag = false;
        try {
            Connection connection = db.getConnection();
            String query = "INSERT INTO datastudent(username,name,email,password) VALUES(?,?,?,?)";
            // prepared connection
            PreparedStatement statement = connection.prepareStatement(query);
            // set the values of parameter
            statement.setString(1, std.getUsername());
            statement.setString(2, std.getStudentName());
            statement.setString(3, std.getStudentEmail());
            statement.setString(4, std.getPassword());
            //execute

            PreparedStatement pstInsertnilai = null;

            pstInsertnilai = connection.prepareStatement("INSERT INTO nilai (username) VALUES (?)");
            pstInsertnilai.setString(1, std.getUsername());

            if(getInfo(std.getUsername()) == null){
                statement.executeUpdate();
                pstInsertnilai.executeUpdate();
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static String getInfo(String id) {
        String send = null;
        try {
            Connection connection = db.getConnection();
            String query = "select username,name,email from datastudent where username =?";

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            {
                preparedStatement.setString(1, id);
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    String stdId = rs.getString("username");
                    String sname = rs.getString("name");
                    String uname = rs.getString("email");
//                    String points = rs.getString("points");

                    //send = stdId + " " + sname + " " + uname + " " + points;
                    send = "Student{" + "\n" +
                            "Username = " + stdId + ",\n" +
                            "Student Name = " + sname + ",\n" +
                            "Student Email = " + uname + ",\n" +
//                            "quizPoint= " + points +
                            '}';
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return send;
    }


    // ***********************************************************************************************************
    //String id, String pass


    public static boolean InsertToQuizDB(Quiz quiz) throws SQLException {
        //jdbc code here
        boolean flag = false;
        PreparedStatement statement = null;
        try {
            Connection connection = db.getConnection();
            String idCheck = "SELECT * FROM question WHERE id_soal = ?";
            statement =connection.prepareStatement(idCheck);
            statement.setInt(1, quiz.getId());
            ResultSet rs = statement.executeQuery();

            if (rs.isBeforeFirst()){
                System.out.println("User is Already exists!");
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setContentText("This number is Already exists!!!");
                error.show();
            }else {
                String query = "INSERT INTO question(id_soal, kd_materi, soal, pilihan1, pilihan2, pilihan3, pilihan4, pilihan5, jawaban) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                // prepared connection
                statement = connection.prepareStatement(query);
                // set the values of parameter
                statement.setInt(1, quiz.getId());
                statement.setString(2, quiz.getQuizTitle());
                statement.setString(3, quiz.getQuizQuestion());
                statement.setString(4, quiz.getOptions_1());
                statement.setString(5, quiz.getOptions_2());
                statement.setString(6, quiz.getOptions_3());
                statement.setString(7, quiz.getOptions_4());
                statement.setString(8, quiz.getOptions_5());
                statement.setString(9, quiz.getAnswer());
                //execute
                statement.executeUpdate();
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}