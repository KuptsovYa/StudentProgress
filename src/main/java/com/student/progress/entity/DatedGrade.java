package com.student.progress.entity;

public class DatedGrade extends NamedGrade {

    private String date;

    public DatedGrade(String assessment, String discipline, String name, String date) {
        super(assessment, discipline, name);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
