package com.student.progress.repo;

import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.StudDataTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

@Service
public class ProgressionRepositoryImpl implements ProgressionRepository{

    private JdbcOperations jdbcOperations;

    @Autowired
    public ProgressionRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Object getProgressionByStudName(StudDataTransferObject studDataTransferObject) {
        String sql = "Select Date.date, assessment.assessment, Discipline.disciplineName\n" +
                "FROM Person, PersonToDiscipline, Discipline, Assessment, Date\n" +
                "WHERE Person.personFirstName = ?\n" +
                "AND Person.personSecondName = ?\n" +
                "AND Person.idPerson = persontodiscipline.idPerson\n" +
                "AND PersonToDiscipline.idDiscipline = Discipline.idDiscipline\n" +
                "AND PersonToDiscipline.idAssessment = Assessment.idAssessment\n" +
                "AND PersonToDiscipline.idDate = Date.idDate;";
        Object result = jdbcOperations.queryForList(sql,
                new Object[]{studDataTransferObject.getFirstName(),
                studDataTransferObject.getSecondName()});
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
