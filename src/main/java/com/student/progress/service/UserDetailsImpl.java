package com.student.progress.service;

import com.student.progress.dto.Role;
import com.student.progress.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private final UserDto usersEntity;

    @Autowired
    public UserDetailsImpl(UserDto usersEntity) {
        this.usersEntity = usersEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("User"));
        return roles;
    }

    @Override
    public String getPassword() {
        return usersEntity.getPassword();
    }

    @Override
    public String getUsername() { return usersEntity.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}