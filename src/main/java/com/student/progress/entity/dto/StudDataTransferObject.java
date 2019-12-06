package com.student.progress.entity.dto;

import com.student.progress.entity.DataTransferObject;

public class StudDataTransferObject extends DataTransferObject {

    private int idPerson;
    private String personFirstName;
    private String personSecondName;
    private String personDateOfBith;
    private int idSpeciality;
    private int idGroup;

    public StudDataTransferObject(int idPerson, String personFirstName, String personSecondName, String personDateOfBith, int idSpeciality, int idGroup) {
        this.idPerson = idPerson;
        this.personFirstName = personFirstName;
        this.personSecondName = personSecondName;
        this.personDateOfBith = personDateOfBith;
        this.idSpeciality = idSpeciality;
        this.idGroup = idGroup;
    }

    public StudDataTransferObject(String personFirstName, String personSecondName) {
        this.personFirstName = personFirstName;
        this.personSecondName = personSecondName;
    }

    public StudDataTransferObject(){}

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonSecondName() {
        return personSecondName;
    }

    public void setPersonSecondName(String personSecondName) {
        this.personSecondName = personSecondName;
    }

    public String getPersonDateOfBith() {
        return personDateOfBith;
    }

    public void setPersonDateOfBith(String personDateOfBith) {
        this.personDateOfBith = personDateOfBith;
    }

    public int getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(int idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }
}
