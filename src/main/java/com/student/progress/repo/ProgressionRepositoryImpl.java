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

    @Override
    public List<Map<String, Object>> getProgressionByStudName(StudDataTransferObject studDataTransferObject) {
        String sql = "Select DateTable.date, assessment.assessment, Discipline.disciplineName " +
                "FROM Person, PersonToDiscipline, Discipline, Assessment, DateTable " +
                "WHERE Person.personFirstName = ? " +
                "AND Person.personSecondName = ? " +
                "AND Person.idPerson = persontodiscipline.idPerson " +
                "AND PersonToDiscipline.idDiscipline = Discipline.idDiscipline " +
                "AND PersonToDiscipline.idAssessment = Assessment.idAssessment " +
                "AND PersonToDiscipline.idDate = DateTable.idDate;";
        List<Map<String, Object>> result = jdbcOperations.queryForList(sql,
                new Object[]{studDataTransferObject.getPersonFirstName(),
                        studDataTransferObject.getPersonSecondName()});
        return result;
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
                new Object[]{groupDataTransferObject.getGroupName(),
                        groupDataTransferObject.getGroupNumber()});
        return result;
    }
}
