package com.student.progress.service;

import com.student.progress.dto.UserDto;
import com.student.progress.repo.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Logger logger = LogManager.getLogger(this);
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto addAUser(UserDto userDTO) {
        try {
            logger.info("Adding new user " + userDTO);
            UserDto user = new UserDto();
            user.setLogin(userDTO.getLogin());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user = ValidateUser(userDTO, user);
            userRepository.addAUser(user);
            logger.info("New user " + userDTO + " added database");
            return userDTO;
        } catch (Exception e) {
            logger.error("Adding new user failed exception: " + e);
            e.printStackTrace();
            return null;
        }
    }

    private UserDto ValidateUser(UserDto userDtoExternal, UserDto currentUser){
        if (userDtoExternal.getLogin().equals("admin") && userDtoExternal.getPassword().equals("admin")){
            currentUser.setRoleId("1");
            return currentUser;
        }
        currentUser.setRoleId("2");
        return currentUser;
    }

    public boolean checkEqualsLogin(String login) {
        try {
            logger.info("Checking for already registred user " + login);
            UserDto user = new UserDto();
            return userRepository.checkEqualsLogin(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }

    }
}
