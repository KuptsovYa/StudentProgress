package com.student.progress.repo;


import com.student.progress.entity.dto.UserDataTransferObject;
import com.student.progress.entity.entities.UserEntity;

public interface UserRepository<P extends UserDataTransferObject>{

    void addAUser(P p);

    boolean checkEqualsLogin(P p);

    UserEntity findByLogin(String login);

    String getRoleByCred(String login, String password);
}
