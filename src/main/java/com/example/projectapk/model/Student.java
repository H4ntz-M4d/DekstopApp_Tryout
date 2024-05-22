package com.example.projectapk.model;

public class Student {
    private String Username;
    private String studentName;
    private String studentEmail;
    private String password;
    private Integer quizPoint;

    public Integer getQuizPoint() {
        return quizPoint;
    }

    public void setQuizPoint(Integer quizPoint) {
        this.quizPoint = quizPoint;
    }

    public Student() {
    }

    public Student(String Username, String password) {
        this.Username = Username;
        this.password = password;
    }

    public Student(String Username, String studentName, String studentEmail, String password) {
        this.Username = Username;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.password = password;
    }

    public Student(String Username, String studentName, String studentEmail, Integer quizPoint) {
        this.Username = Username;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.quizPoint = quizPoint;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Student{" +
                "Username='" + Username + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", password='" + password + '\'' +
                ", quizPoint=" + quizPoint +
                '}';
    }
}
