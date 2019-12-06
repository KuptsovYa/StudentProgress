package com.student.progress.entity.dto;

import com.student.progress.entity.DataTransferObject;

public class GroupDataTransferObject extends DataTransferObject {

    private String groupName;
    private String groupNumber;

    public GroupDataTransferObject() {
    }

    public GroupDataTransferObject(String groupName, String groupNumber) {
        this.groupName = groupName;
        this.groupNumber = groupNumber;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
}
