package com.student.progress.controller.rest;

import com.student.progress.entity.dto.StudDataTransferObject;
import com.student.progress.service.ProgressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileRestController {

    private final ProgressionService progressionService;

    @Autowired
    public ProfileRestController(ProgressionService progressionService) {
        this.progressionService = progressionService;
    }

    @PostMapping("/profile/getByName")
    public Object getProgressionByStudName(StudDataTransferObject studDataTransferObject){
        return progressionService.getProgression(studDataTransferObject);
    }
}
