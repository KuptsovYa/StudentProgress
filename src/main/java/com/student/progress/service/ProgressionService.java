package com.student.progress.service;

import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.StudDataTransferObject;

public interface ProgressionService {

   Object getProgression(StudDataTransferObject studDataTransferObject);

   Object getProgression(GroupDataTransferObject groupDataTransferObject);

}
