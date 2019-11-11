package com.student.progress.repo;


import com.student.progress.dto.UserDto;

public interface UserRepository<P extends UserDto>{

    void addAUser(P p);

    boolean checkEqualsLogin(P p);

    UserDto findByLogin(String login);
}
