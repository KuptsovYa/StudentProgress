package com.student.progress.dto;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private String role;

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
