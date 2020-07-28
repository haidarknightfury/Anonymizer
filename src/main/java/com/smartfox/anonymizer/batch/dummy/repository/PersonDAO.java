package com.smartfox.anonymizer.batch.dummy.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.smartfox.anonymizer.batch.dummy.entity.Person;
import com.smartfox.anonymizer.batch.dummy.repository.base.BaseDAO;

@Repository
public class PersonDAO extends BaseDAO<Person> {

    private static final String INSERT_STATEMENT = "INSERT INTO person_ref.person(id, first, last, gender, city, street) values (NEXTVAL('person_ref.person_seq'), ?, ?, ?, ?, ?)";

    private static final String TABLE_NAME = "person";

    @Resource(name = "dummyJDBCTemplate")
    JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(@Qualifier(value = "dummyJDBCTemplate") JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    public RowMapper<Person> getRowMapper() {
        return (resultSet, i) -> {
            Person person = new Person();
            person.setId(resultSet.getLong("id"));
            person.setFirst(resultSet.getString("first"));
            person.setLast(resultSet.getString("last"));
            person.setCity(resultSet.getString("city"));
            person.setGender(resultSet.getString("gender"));
            
            return person;
        };
    }

    @Override
    public void insertAll(List<Person> elements) {
        this.jdbcTemplate.batchUpdate(INSERT_STATEMENT, new BatchPreparedStatementSetter() {

            @Override
            public int getBatchSize() {
                return elements.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, elements.get(i).getFirst());
                ps.setString(2, elements.get(i).getLast());
                ps.setString(3, elements.get(i).getGender());
                ps.setString(4, elements.get(i).getCity());
                ps.setString(5, elements.get(i).getStreet());
            }

        });

    }

}
