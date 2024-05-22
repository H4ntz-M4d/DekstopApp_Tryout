package com.example.projectapk.DB_Connection;

import com.example.projectapk.model.UserSession;

import java.sql.*;

public class SoalConnection {
    DatabaseConnection dbConnection = new DatabaseConnection();
    Connection connect = dbConnection.getConnection();
    int count = 0;
    public ResultSet getQuestions() {
        String sql = "SELECT * FROM question";
        try {
            PreparedStatement pst = connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pst.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getTotalNumber() {
        String sql = "SELECT COUNT(*) AS count FROM question";
        try {
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    public ResultSet getAnswers() {
        String sql = "SELECT jawaban FROM question";
        try {
            PreparedStatement pst  = connect.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pst.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ResultSet scoreView(){
        String query = "SELECT username, name, email FROM datastudent WHERE username = ?";

        try {
            PreparedStatement pst = connect.prepareStatement(query);
            pst.setString(1, UserSession.getUsername());
            ResultSet rs = pst.executeQuery();
            return rs;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
