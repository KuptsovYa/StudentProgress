package com.student.progress.entity.entities;

import com.student.progress.entity.Entity;

public class UserEntity extends Entity {

    private int idUser;
    private String userName;
    private String password;
    private int role_idRole;

    public UserEntity() {
    }

    public UserEntity(int idUser, String userName, String password, int role_idRole) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.role_idRole = role_idRole;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_idRole() {
        return role_idRole;
    }

    public void setRole_idRole(int role_idRole) {
        this.role_idRole = role_idRole;
    }
}
