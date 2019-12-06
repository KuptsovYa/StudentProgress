package com.student.progress.repo;

import com.student.progress.entity.dto.UserDataTransferObject;
import com.student.progress.entity.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository<UserDataTransferObject> {

    private final JdbcOperations jdbcOperations;

    @Autowired
    public UserRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public void addAUser(UserDataTransferObject user) {
        String queryForUser = "INSERT INTO users(userName, password, Role_roleId) VALUES (?, ?, (SELECT idRole from role where idRole = ?))";
        Object[] params = new Object[]{user.getLogin(), user.getPassword(), user.getRoleId()};
        jdbcOperations.update(queryForUser, params);
    }

    @Override
    public boolean checkEqualsLogin(UserDataTransferObject user) {
        String sql = "SELECT COUNT(*) FROM users WHERE userName = ?";
        Object[] params = new Object[]{user.getLogin()};
        Integer count = jdbcOperations.queryForObject(sql, params, Integer.class);
        if (count != null) {
            return true;
        }
        return false;
    }

    @Override
    public UserEntity findByLogin(String login) {
        String sql = "SELECT idUsers, userName, password FROM users WHERE userName = ?";
        Object[] params = new Object[]{login};
        UserEntity userEntity = (UserEntity) jdbcOperations.queryForObject(sql, params, new BeanPropertyRowMapper(UserEntity.class));
        return userEntity;
    }
}

