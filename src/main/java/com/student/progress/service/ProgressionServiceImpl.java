package com.student.progress.service;

import com.student.progress.entity.Grade;
import com.student.progress.entity.NamedGrade;
import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.StudDataTransferObject;
import com.student.progress.repo.ProgressionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProgressionServiceImpl implements ProgressionService {

    private final ProgressionRepository progressionService;
    private Logger logger = LogManager.getLogger(this);

    @Autowired
    public ProgressionServiceImpl(ProgressionRepository progressionService) {
        this.progressionService = progressionService;
    }

    @Override
    public Map<String, List<Grade>> getProgression(StudDataTransferObject userDto) {
        logger.info("User looking for " + userDto.getPersonFirstName() + " " + userDto.getPersonSecondName());
        List<Map<String, Object>> result = progressionService.getProgressionByStudName(userDto);
        return transformValues(result);
    }

    private Map<String, List<Grade>> transformValues(List<Map<String, Object>> result) {
        if (result == null) return null;
        Map<String, List<Grade>> transformedResult = new HashMap<>();
        List<String> sortedDates = getDates(result);
        List<Grade> gradeList = new ArrayList<>();
        Collections.reverse(sortedDates);
        int j = 0;
        for (int i = 0; i < result.size(); i++) {
            String date = (String) result.get(i).get("date");
            if (sortedDates.get(j).equals(date)) {
                gradeList.add(createGrade(result.get(i)));
            } else {
                transformedResult.put((String) result.get(i - 1).get("date"), gradeList);
                gradeList = new ArrayList<>();
                j++;
                gradeList.add(createGrade(result.get(i)));
            }
            if (result.size() - 1 == i) {
                transformedResult.put(date, gradeList);
            }
        }

        return transformedResult;
    }

    private Grade createGrade(Map<String, Object> gradeList) {
        String assessment = (String) gradeList.get("assessment");
        String discipline = (String) gradeList.get("disciplineName");
        return new Grade(assessment, discipline);
    }

    private NamedGrade createGradeNamed(Map<String, Object> gradeList) {
        Grade defGrade = createGrade(gradeList);
        String firstName = (String) gradeList.get("personFirstName");
        String lastName = (String) gradeList.get("personSecondName");
        return new NamedGrade(defGrade.getAssessment(), defGrade.getDiscipline(), firstName + " " + lastName);
    }

    private Map<String, List<NamedGrade>> transformValuesForGroup(List<Map<String, Object>> result) {
        if (result == null) return null;
        List<NamedGrade> gradeList = new ArrayList<>();
        Map<String, List<NamedGrade>> transformedResult = new HashMap<>();
        List<String> sortedDates = getDates(result);
        int j = 0;
        for (int i = 0; i < result.size(); i++) {
            String date = (String) result.get(i).get("date");
            if (sortedDates.get(j).equals(date)) {
                gradeList.add(createGradeNamed(result.get(i)));
            } else {
                transformedResult.put((String) result.get(i - 1).get("date"), gradeList);
                gradeList = new ArrayList<>();
                j++;
                gradeList.add(createGradeNamed(result.get(i)));
            }
            if (result.size() - 1 == i) {
                transformedResult.put(date, gradeList);
            }
        }
        return transformedResult;
    }

    private List<String> getDates(List<Map<String, Object>> result) {
        Set<String> dates = new HashSet<>();
        for (int i = 0; i < result.size(); i++) {
            String date = (String) result.get(i).get("date");
            dates.add(date);
        }
        List<String> sortedDates = new ArrayList<>(dates);
        Collections.reverse(sortedDates);
        return sortedDates;
    }

    @Override
    public Map<String, List<NamedGrade>> getProgression(GroupDataTransferObject groupDto) {
        logger.info("User looking for " + groupDto + " group");
        return transformValuesForGroup(progressionService.getProgressionByStudGroup(groupDto));
    }
}
