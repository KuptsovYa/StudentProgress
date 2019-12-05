package com.student.progress.entity.dto;

import com.student.progress.entity.DataTransferObject;

public class UserDataTransferObject extends DataTransferObject {

    private int idUsers;
    private String login;
    private String password;
    private int roleId;

    public UserDataTransferObject() {
    }

    public UserDataTransferObject(int idUsers, String login, String password, int roleId) {
        this.idUsers = idUsers;
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public UserDataTransferObject(String login, String password, int roleId) {
        this.login = login;
        this.password = password;
        this.roleId = roleId;
    }

    public int getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(int idUsers) {
        this.idUsers = idUsers;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = Integer.valueOf(roleId);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDataTransferObject{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
