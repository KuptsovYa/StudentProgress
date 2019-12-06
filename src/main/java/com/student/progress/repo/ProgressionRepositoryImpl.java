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
    public Object getProgressionByStudName(StudDataTransferObject studDataTransferObject) {
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
    public Object getProgressionByStudGroup(GroupDataTransferObject groupDataTransferObject) {
        String sql = "Select DateTable.date, assessment.assessment, Discipline.disciplineName, Person.personFirstName, Person.personSecondName\n" +
                "FROM Person, PersonToDiscipline, Discipline, Assessment, DateTable, GroupTable, Speciality\n" +
                "WHERE Speciality.specialityName = ?\n" +
                "AND Speciality.idSpeciality = Person.idSpeciality  \n" +
                "AND GroupTable.GroupName = ?\n" +
                "AND GroupTable.idGroup = Person.idGroup\n" +
                "AND Person.idPerson = PersonToDiscipline.idPerson\n" +
                "AND PersonToDiscipline.idDiscipline = Discipline.idDiscipline\n" +
                "AND PersonToDiscipline.idAssessment = Assessment.idAssessment\n" +
                "AND PersonToDiscipline.idDate = DateTable.idDate;";
        Object result = jdbcOperations.queryForList(sql,
                new Object[]{groupDataTransferObject.getGroupName(),
                        groupDataTransferObject.getGroupNumber()});
        return result;
    }
}
