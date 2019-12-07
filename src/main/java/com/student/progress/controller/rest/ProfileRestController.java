package com.student.progress.controller.rest;

import com.student.progress.entity.Grade;
import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.StudDataTransferObject;
import com.student.progress.service.ProgressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProfileRestController {

    private final ProgressionService progressionService;

    @Autowired
    public ProfileRestController(ProgressionService progressionService) {
        this.progressionService = progressionService;
    }

    @PostMapping("/profile/getByName")
    @ResponseBody
    public Map<String, List<Grade>> getProgressionByStudName(@RequestBody final StudDataTransferObject studDataTransferObject){
        return progressionService.getProgression(studDataTransferObject);
    }

    @PutMapping("/profile/getByGroup")
    public Object getProgressionByGroupName(GroupDataTransferObject groupDataTransferObject){
        return progressionService.getProgression(groupDataTransferObject);
    }
}
