package com.student.progress.service;

import com.student.progress.dto.UserDto;
import com.student.progress.repo.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Logger logger = LogManager.getLogger(this);
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto addAUser(UserDto userDTO) {
        try {
            logger.info("Adding new user " + userDTO);
            UserDto usersEntity = new UserDto();
            usersEntity.setLogin(userDTO.getLogin());
            usersEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.addAUser(usersEntity);
            logger.info("New user " + userDTO + " added database");
            return userDTO;
        } catch (Exception e) {
            logger.error("Adding new user failed exception: " + e);
            return null;
        }
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
