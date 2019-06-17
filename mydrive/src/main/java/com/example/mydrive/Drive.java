package com.example.mydrive;

public class Drive {
    int id;
    String type;
    String title;
    String date;

    // 생성자
    Drive(){}
    Drive(String type, String title, String date){
        this.type = type;
        this.title = title;
        this.date = date;
    }
}
