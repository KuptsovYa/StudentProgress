package com.student.progress.service;


import com.student.progress.entity.entities.UserEntity;
import com.student.progress.repo.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LogManager.getLogger(this);
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("User with username " + s + " trying to authorise");
        try {
            UserEntity usersEntity = userRepository.findByLogin(s);
            if (usersEntity == null){
                throw new UsernameNotFoundException("User with login: " + s + " not found");
            }
            return new UserDetailsImpl(usersEntity, userService);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

}
