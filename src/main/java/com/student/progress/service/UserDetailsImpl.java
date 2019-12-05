package com.student.progress.service;

import com.student.progress.entity.dto.RoleDataTransferObject;
import com.student.progress.entity.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private final UserEntity usersEntity;

    @Autowired
    public UserDetailsImpl(UserEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleDataTransferObject> roles = new HashSet<>();
        roles.add(new RoleDataTransferObject("User"));
        return roles;
    }

    @Override
    public String getPassword() {
        return usersEntity.getPassword();
    }

    @Override
    public String getUsername() { return usersEntity.getUserName();
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