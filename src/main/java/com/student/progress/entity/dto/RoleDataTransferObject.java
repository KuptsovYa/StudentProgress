package com.student.progress.entity.dto;

import com.student.progress.entity.DataTransferObject;
import org.springframework.security.core.GrantedAuthority;

public class RoleDataTransferObject extends DataTransferObject implements GrantedAuthority{

    private String role;

    public RoleDataTransferObject() {
    }

    public RoleDataTransferObject(String role) {
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
