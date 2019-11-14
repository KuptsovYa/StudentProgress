package com.student.progress.repo;

import com.student.progress.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository<UserDto> {

    private JdbcOperations jdbcOperations;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    @Override
    public void addAUser(UserDto user) {
        String queryForUser = "INSERT INTO users(userName, password) VALUES (?, ?)";
        Object[] params = new Object[]{user.getLogin(), user.getPassword()};
        jdbcOperations.update(queryForUser, params);

    }

    @Override
    public boolean checkEqualsLogin(UserDto user) {
        String sql = "SELECT COUNT(*) FROM user WHERE UserName = ?";
        Object[] params = new Object[]{user.getLogin()};
        Integer count = jdbcOperations.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return true;
        } else return false;
    }

    @Override
    public UserDto findByLogin(String login) {
        String sql = "SELECT idUser, UserName, password FROM user WHERE UserName = ?";
        Object[] params = new Object[]{login};
        UserDto userEntity = (UserDto) jdbcOperations.queryForObject(sql, params, new BeanPropertyRowMapper(UserDto.class));
        return userEntity;
    }
}

