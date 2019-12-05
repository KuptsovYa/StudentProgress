package com.student.progress.repo;

import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.StudDataTransferObject;
import com.student.progress.entity.dto.UserDataTransferObject;

public interface ProgressionRepository {

    Object getProgressionByStudName(StudDataTransferObject studDataTransferObjectDto);

    Object getProgressionByStudGroup(GroupDataTransferObject groupDataTransferObject);
}
