package com.example.projectapk.model;

public class Quiz {
    private int id;
    private String quizTitle;
    private String quizQuestion;
    private String options_1;
    private String options_2;
    private String options_3;
    private String options_4;
    private String options_5;
    private String answer;

    public Quiz() {
    }

    public Quiz(int id, String quizTitle, String quizQuestion, String options_1, String options_2, String options_3, String options_4, String options_5, String answer) {
        this.id = id;
        this.quizTitle = quizTitle;
        this.quizQuestion = quizQuestion;
        this.options_1 = options_1;
        this.options_2 = options_2;
        this.options_3 = options_3;
        this.options_4 = options_4;
        this.options_5 = options_5;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public String getOptions_1() {
        return options_1;
    }

    public void setOptions_1(String options_1) {
        this.options_1 = options_1;
    }

    public String getOptions_2() {
        return options_2;
    }

    public void setOptions_2(String options_2) {
        this.options_2 = options_2;
    }

    public String getOptions_3() {
        return options_3;
    }

    public void setOptions_3(String options_3) {
        this.options_3 = options_3;
    }

    public String getOptions_4() {
        return options_4;
    }

    public void setOptions_4(String options_4) {
        this.options_4 = options_4;
    }

    public String getOptions_5() {
        return options_5;
    }

    public void setOptions_5(String options_5) {
        this.options_5 = options_5;
    }

    public String getAnswer() {
        return answer;

    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
