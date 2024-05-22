package com.example.projectapk.DB_Connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;


    public Connection getConnection(){
        String databaseName = "projectapk";
        String username = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/"+databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,username,password);
            System.out.println("sukses koneksi ke database");
        } catch (Exception e){
            e.printStackTrace();
        }

        return databaseLink;
    }
}
