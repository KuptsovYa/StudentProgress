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
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("User with username " + s + " trying to authorise");
        try {
            UserEntity usersEntity = userRepository.findByLogin(s);
            if (usersEntity == null){
                throw new UsernameNotFoundException("User with login: " + s + " not found");
            }
            return new UserDetailsImpl(usersEntity);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

}
