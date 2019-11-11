package com.student.progress.service;


import com.student.progress.dto.UserDto;

public interface UserService {

    UserDto addAUser(UserDto userDTO);

    boolean checkEqualsLogin(String login);

}
