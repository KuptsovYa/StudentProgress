package com.student.progress.conf;

import com.student.progress.repo.UserRepository;
import com.student.progress.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeanConfig {

    @Autowired
    public UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Primary
    @Bean(name = "MyUserDetailsService")
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl(userRepository);
    }
}
