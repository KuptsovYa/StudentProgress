package com.student.progress.repo;

import com.student.progress.entity.dto.GroupDataTransferObject;
import com.student.progress.entity.dto.NewStudInsertDataTransformObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminRepositoryImpl implements AdminRepository {

    private final JdbcOperations jdbcOperations;

    @Autowired
    public AdminRepositoryImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<String> getGroups() {
        String sql = "SELECT specialityName FROM speciality";
        List<String> result = jdbcOperations.queryForList(sql, String.class);
        return result;
    }

    @Override
    public List<String> getGroupNumbers() {
        String sql = "SELECT groupName FROM GroupTable";
        List<String> result = jdbcOperations.queryForList(sql, String.class);
        return result;
    }

    @Override
    public List<String> getDates() {
        String sql = "SELECT date FROM dateTable";
        List<String> result = jdbcOperations.queryForList(sql, String.class);
        return result;
    }

    @Override
    public List<String> getAssessments() {
        String sql = "SELECT assessment FROM assessment";
        List<String> result = jdbcOperations.queryForList(sql, String.class);
        return result;
    }

    @Override
    public List<String> getDisciplineByGroup(GroupDataTransferObject group) {
        String sql = "SELECT disciplineName FROM Speciality s, SpecialityToDiscipline std, Discipline d WHERE s.specialityName = ? " +
                "AND s.idSpeciality = std.idSpeciality " +
                "AND std.idDiscipline = d.idDiscipline ";
        List<String> result = jdbcOperations.queryForList(sql, new Object[]{group.getGroupName()}, String.class);
        return result;
    }

    @Override
    public String insertStudData(NewStudInsertDataTransformObject newStudInsertDataTransformObject) {
        String selectSpecStatement = "SELECT idSpeciality FROM Speciality WHERE specialityName = ?";
        Object[] selectSpecObjects = new Object[]{newStudInsertDataTransformObject.getGroupName()};
        Integer idSpec = jdbcOperations.queryForObject(selectSpecStatement, selectSpecObjects, Integer.class);

        String selectGroupStatement = "SELECT idGroup FROM GroupTable WHERE groupName = ?";
        Object[] selectGroupObjects = new Object[]{newStudInsertDataTransformObject.getGroupNum()};
        Integer idGroup = jdbcOperations.queryForObject(selectGroupStatement, selectGroupObjects, Integer.class);

        String selectDateStatement = "SELECT idDate FROM DateTable WHERE date = ?";
        Object[] selectDateObjects = new Object[]{newStudInsertDataTransformObject.getDate()};
        Integer idDate = jdbcOperations.queryForObject(selectDateStatement, selectDateObjects, Integer.class);

        String insertStatement = "INSERT INTO Person(personFirstName, personSecondName, idSpeciality, idGroup) VALUES (?,?,?,?)";
        Object[] insertObjects = new Object[]{newStudInsertDataTransformObject.getFirstName(),
                newStudInsertDataTransformObject.getSecondName(),
                idSpec,
                idGroup};
        jdbcOperations.update(insertStatement, insertObjects);

        String lastId = "SELECT LAST_INSERT_ID()";
        Integer idPerson = jdbcOperations.queryForObject(lastId, Integer.class);

        String selectForAssessmentId = "SELECT idAssessment FROM Assessment WHERE assessment = ?";
        String selectForDiscipline = "SELECT idDiscipline FROM Discipline WHERE disciplineName = ?";

        for (int i =0; i < newStudInsertDataTransformObject.getAssessmentValues().size(); i++){
            Object[] selectForAssessment = new Object[]{newStudInsertDataTransformObject.getAssessmentValues().get(i).getAssessment()};
            Integer idAssessment = jdbcOperations.queryForObject(selectForAssessmentId, selectForAssessment, Integer.class);

            Object[] selectForDisciplineId = new Object[]{newStudInsertDataTransformObject.getAssessmentValues().get(i).getDiscipline()};
            Integer idDiscipline = jdbcOperations.queryForObject(selectForDiscipline, selectForDisciplineId, Integer.class);

            String insertValuesStatement = "INSERT INTO PersonToDiscipline(idPerson, idDiscipline, idAssessment, idDate) VALUES (?,?,?,?)";
            Object[] objectToInsert = new Object[]{idPerson, idDiscipline, idAssessment, idDate};
            jdbcOperations.update(insertValuesStatement, objectToInsert);
        }
        return "Success";
    }
}
