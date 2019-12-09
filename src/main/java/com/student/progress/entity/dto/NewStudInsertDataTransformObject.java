package com.student.progress.entity.dto;

import com.student.progress.entity.Grade;

import java.util.List;

public class NewStudInsertDataTransformObject {

    private String date;
    private String firstName;
    private String secondName;
    private String groupName;
    private String groupNum;
    private List<Grade> assessmentValues;

    public NewStudInsertDataTransformObject(String date,
                                            String firstName,
                                            String secondName,
                                            String groupName,
                                            String groupNum,
                                            List<Grade> assessmentValues) {
        this.date = date;
        this.firstName = firstName;
        this.secondName = secondName;
        this.groupName = groupName;
        this.groupNum = groupNum;
        this.assessmentValues = assessmentValues;
    }

    public NewStudInsertDataTransformObject() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public List<Grade> getAssessmentValues() {
        return assessmentValues;
    }

    public void setAssessmentValues(List<Grade> assessmentValues) {
        this.assessmentValues = assessmentValues;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
