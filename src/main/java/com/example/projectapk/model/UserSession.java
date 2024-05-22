package com.example.projectapk.model;

import javafx.application.Platform;

public class UserSession {
    private static String username;
    private static int jawabanBenar;
    private static int jawabanSalah;
    private static double nilaiTotal;

    public static double getNilaiTotal() {
        return nilaiTotal;
    }

    public static void setNilaiTotal(double nilaiTotal) {
        UserSession.nilaiTotal = nilaiTotal;
    }

    public static int getJawabanBenar() {
        return jawabanBenar;
    }

    public static void setJawabanBenar(int jawabanBenar) {
        UserSession.jawabanBenar = jawabanBenar;
    }

    public static int getJawabanSalah() {
        return jawabanSalah;
    }

    public static void setJawabanSalah(int jawabanSalah) {
        UserSession.jawabanSalah = jawabanSalah;
    }

    public static String getUsername() {

        return username;
    }

    public static void setUsername(String username) {

        UserSession.username = username;
    }

//    Runnable running = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                Thread.sleep(20000);
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println(UserSession.getUsername());
//                        dataQuiz();
//                    }
//                });
//            }catch (InterruptedException event){
//
//            }
//        }
//    };
//
//        new Thread(running).start();
}
