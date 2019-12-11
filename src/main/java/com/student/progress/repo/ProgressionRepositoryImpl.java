package com.student.progress.repo;

import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.StudDataTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProgressionRepositoryImpl implements ProgressionRepository {

    private JdbcOperations jdbcOperations;

    @Autowired
    public ProgressionRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    private static final String PROGRESSION_BY_STUD_NAME_QUERY = "Select DateTable.date, assessment.assessment, Discipline.disciplineName " +
            "FROM Person, PersonToDiscipline, Discipline, Assessment, DateTable " +
            "WHERE Person.personFirstName = ? " +
            "AND Person.personSecondName = ? " +
            "AND Person.idPerson = persontodiscipline.idPerson " +
            "AND PersonToDiscipline.idDiscipline = Discipline.idDiscipline " +
            "AND PersonToDiscipline.idAssessment = Assessment.idAssessment " +
            "AND PersonToDiscipline.idDate = DateTable.idDate;";

    @Override
    public List<Map<String, Object>> getProgressionByStudName(StudDataTransferObject studDataTransferObject) {
        List<Map<String, Object>> result = jdbcOperations.queryForList(PROGRESSION_BY_STUD_NAME_QUERY,
                studDataTransferObject.getPersonFirstName(),
                studDataTransferObject.getPersonSecondName());
        return result;
    }

    private List<Map<String, Object>> getProgressionByStudName(String firstName, String lastName) {
        List<Map<String, Object>> result = jdbcOperations.queryForList(PROGRESSION_BY_STUD_NAME_QUERY,
                firstName,
                lastName);
        return result;
    }

    @Override
    public Integer getDisciplineCountGroup(String groupName, String groupNumber) {
        String sql = "select count(*) from (select distinct discipline.disciplineName " +
                "from person, speciality, specialityToDiscipline, discipline,groupTable " +
                "where speciality.specialityName = ? " +
                "and groupTable.groupName = ? " +
                "and person.idGroup = groupTable.idGroup " +
                "and person.idSpeciality = Speciality.idSpeciality " +
                "and specialityToDiscipline.idSpeciality = speciality.idspeciality " +
                "and specialityToDiscipline.idDiscipline = Discipline.idDiscipline) as result ";
        return jdbcOperations.queryForObject(sql, new Object[]{groupName, groupNumber}, Integer.class);
    }

    public Integer getPeopleCountInGroup(String groupName, String groupNumber) {
        String sqlForPeople = "SELECT count(*) from (Select Person.personFirstName, Person.personSecondName " +
                "FROM Person, GroupTable, Speciality " +
                "WHERE Speciality.specialityName = ? " +
                "AND Speciality.idSpeciality = Person.idSpeciality " +
                "AND GroupTable.GroupName = ? " +
                "AND GroupTable.idGroup = Person.idGroup) as result";
        return jdbcOperations.queryForObject(sqlForPeople, new Object[]{groupName, groupNumber}, Integer.class);
    }

    @Override
    public List<Map<String, Object>> getProgressionByStudGroup(GroupDataTransferObject groupDataTransferObject) {
        String sql = "Select DateTable.date, assessment.assessment, Discipline.disciplineName, Person.personFirstName, Person.personSecondName " +
                "FROM Person, PersonToDiscipline, Discipline, Assessment, DateTable, GroupTable, Speciality " +
                "WHERE Speciality.specialityName = ? " +
                "AND Speciality.idSpeciality = Person.idSpeciality " +
                "AND GroupTable.GroupName = ? " +
                "AND GroupTable.idGroup = Person.idGroup " +
                "AND Person.idPerson = PersonToDiscipline.idPerson " +
                "AND PersonToDiscipline.idDiscipline = Discipline.idDiscipline " +
                "AND PersonToDiscipline.idAssessment = Assessment.idAssessment " +
                "AND PersonToDiscipline.idDate = DateTable.idDate;";
        List<Map<String, Object>> result = jdbcOperations.queryForList(sql,
                groupDataTransferObject.getGroupName(),
                groupDataTransferObject.getGroupNumber());
        return result;
    }
}
