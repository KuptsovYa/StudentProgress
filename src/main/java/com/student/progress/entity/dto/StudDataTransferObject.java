package com.student.progress.entity.dto;

import com.student.progress.entity.DataTransferObject;

public class StudDataTransferObject extends DataTransferObject {

    private int idPerson;
    private String firstName;
    private String secondName;

    public StudDataTransferObject(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
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
