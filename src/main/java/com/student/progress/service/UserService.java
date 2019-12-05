package com.student.progress.service;


import com.student.progress.entity.dto.UserDataTransferObject;

public interface UserService {

    UserDataTransferObject addAUser(UserDataTransferObject userDTO);

    boolean checkEqualsLogin(String login);

}
