package com.example.projectapk.model;

import com.mysql.cj.conf.IntegerProperty;
import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataNilai {
    private SimpleIntegerProperty no;
    private SimpleStringProperty username;
    private SimpleIntegerProperty nilai;

    public DataNilai(int no, String username, int nilai) {
        this.no = new SimpleIntegerProperty(no);
        this.username = new SimpleStringProperty(username);
        this.nilai = new SimpleIntegerProperty(nilai);

    }

    public DataNilai() {
        this.no = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.nilai = new SimpleIntegerProperty();
    }

    public int getNo() {
        return no.get();
    }

    public SimpleIntegerProperty noProperty() {
        return no;
    }

    public void setNo(int no) {
        this.no.set(no);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public int getNilai() {
        return nilai.get();
    }

    public SimpleIntegerProperty nilaiProperty() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai.set(nilai);
    }
}
