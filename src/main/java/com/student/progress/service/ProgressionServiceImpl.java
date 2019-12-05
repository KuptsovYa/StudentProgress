package com.student.progress.service;

import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.StudDataTransferObject;
import com.student.progress.repo.ProgressionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressionServiceImpl implements ProgressionService {

    private final ProgressionRepository progressionService;
    private Logger logger = LogManager.getLogger(this);

    @Autowired
    public ProgressionServiceImpl(ProgressionRepository progressionService) {
        this.progressionService = progressionService;
    }

    @Override
    public Object getProgression(StudDataTransferObject userDto) {
        logger.info("User looking for " + userDto.getFirstName() + " " + userDto.getSecondName());
        return progressionService.getProgressionByStudName(userDto);
    }

    @Override
    public Object getProgression(GroupDataTransferObject groupDto) {
        logger.info("User looking for " + groupDto + " group");
        return progressionService.getProgressionByStudGroup(groupDto);
    }
}
