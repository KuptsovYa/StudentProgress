package com.student.progress.service;

import com.student.progress.entity.Grade;
import com.student.progress.entity.NamedGrade;
import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.StudDataTransferObject;

import java.util.List;
import java.util.Map;

public interface ProgressionService {

   Map<String, List<Grade>> getProgression(StudDataTransferObject studDataTransferObject);

   Map<String, List<NamedGrade>> getProgression(GroupDataTransferObject groupDataTransferObject);

}
