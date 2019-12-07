package com.student.progress.entity;

public class Grade {

    private String assessment;
    private String discipline;

    public Grade(String assessment, String discipline) {
        this.assessment = assessment;
        this.discipline = discipline;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
