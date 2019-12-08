package com.student.progress.repo;

import com.student.progress.entity.dto.GroupDataTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;

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
        List<String> result = jdbcOperations.queryForList(sql, new Object[]{group.getGroupName()},String.class);
        return result;
    }
}
