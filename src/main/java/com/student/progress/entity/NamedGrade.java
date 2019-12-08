package com.student.progress.entity;

public class NamedGrade extends Grade {

    private String name;

    public NamedGrade(String assessment, String discipline, String name) {
        super(assessment, discipline);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
