package com.student.progress.service;

import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.NewStudInsertDataTransformObject;

import java.util.List;

public interface AdminService {

    List<String> getGroups();

    List<String> getGroupNumbers();

    List<String> getDates();

    List<String> getAssessments();

    List<String> getDisciplineByGroup(GroupDataTransferObject group);

    String insertStudData(NewStudInsertDataTransformObject studInsertDataTransformObject);
}
