package com.student.progress.service;

import com.student.progress.entity.DatedGrade;
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

    private final ProgressionRepository progressionRepository;
    private Logger logger = LogManager.getLogger(this);

    @Autowired
    public ProgressionServiceImpl(ProgressionRepository progressionRepository) {
        this.progressionRepository = progressionRepository;
    }

    @Override
    public Map<String, List<Grade>> getProgression(StudDataTransferObject userDto) {
        logger.info("User looking for " + userDto.getPersonFirstName() + " " + userDto.getPersonSecondName());
        List<Map<String, Object>> result = progressionRepository.getProgressionByStudName(userDto);
        return transformValues(result);
    }

    private Map<String, List<Grade>> transformValues(List<Map<String, Object>> result) {
        if (result == null) return null;
        Map<String, List<Grade>> transformedResult = new HashMap<>();
        List<String> sortedDates = getDates(result);
        List<Grade> gradeList = new ArrayList<>();
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

    private DatedGrade createGradeDated(Map<String, Object> gradeList) {
        Grade defGrade = createGrade(gradeList);
        return new DatedGrade(
                defGrade.getAssessment(),
                defGrade.getDiscipline(),
                gradeList.get("personFirstName") + " " + gradeList.get("personSecondName"),
                (String) gradeList.get("date"));
    }

    private Map<String, List<NamedGrade>> transformValuesForGroup(List<Map<String, Object>> result,
                                                                  Integer disciplineCount,
                                                                  Integer peopleCount) {
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
        return deletePeople(transformedResult, disciplineCount, peopleCount);
    }

    private Map<String, List<NamedGrade>> deletePeople(Map<String, List<NamedGrade>> transformedPeople, Integer disciplineCount, Integer peopleCount) {
        List<String> listToRemove = new ArrayList<>();
        for (Map.Entry<String, List<NamedGrade>> entry : transformedPeople.entrySet()) {
            if (entry.getValue().size() % disciplineCount * peopleCount != 0) {
                listToRemove.add(entry.getKey());
            }
        }
        for (String item : listToRemove) {
            transformedPeople.remove(item);
        }
        return transformedPeople;
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
        return transformValuesForGroup(progressionRepository.getProgressionByStudGroup(groupDto),
                progressionRepository.getPeopleCountInGroup(groupDto.getGroupName(), groupDto.getGroupNumber()),
                progressionRepository.getDisciplineCountGroup(groupDto.getGroupName(), groupDto.getGroupNumber()));
    }

    @Override
    public Map<String, List<Grade>> getDvoichniki() {
        List<Map<String, Object>> result = progressionRepository.getAllDvoichinki();
        List<String> names = getNames(result);
        Map<String, List<Grade>> transformedResult = new HashMap<>();
        List<Grade> gradeList = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < result.size(); i++) {
            String fullName = getFullName(result, i);
            if (names.get(j).equals(fullName)) {
                gradeList.add(createGradeDated(result.get(i)));
            } else {
                j++;
                String prevFullName = getFullName(result, i - 1);
                transformedResult.put(prevFullName, gradeList);
                gradeList = new ArrayList<>();
                gradeList.add(createGradeDated(result.get(i)));
            }
            if (result.size() - 1 == i) {
                transformedResult.put(fullName, gradeList);
            }
        }
        return transformedResult;
    }

    private List<String> getNames(List<Map<String, Object>> result) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            String fullName = getFullName(result, i);
            if (!names.contains(fullName)) {
                names.add(fullName);
            }
        }
        List<String> sortedNames = new ArrayList<>(names);
        return sortedNames;
    }

    private String getFullName(List<Map<String, Object>> result, int i) {
        String firstName = (String) result.get(i).get("personFirstName");
        String secondName = (String) result.get(i).get("personSecondName");
        return firstName + " " + secondName;
    }
}
