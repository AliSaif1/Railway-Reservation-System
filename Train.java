package com.example.databaseproject;


import java.time.LocalDate;

public class Train {
    private String dept;
    private String arr;
    private LocalDate date;
    private String time;

    public Train(String dept, String arr, LocalDate date) {
        setDept(dept);
        setArr(arr);
        setDate(date);
    }
    public Train(String dept, String arr, String time) {
        setDept(dept);
        setArr(arr);
        setTime(time);
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate time) {
        this.date = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
