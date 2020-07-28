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

import com.smartfox.anonymizer.batch.dummy.entity.Telephone;
import com.smartfox.anonymizer.batch.dummy.repository.base.BaseDAO;

//USED TO PUT DUMMY VALUES IN THE TABLES IN PERSON_REF
@Repository
public class PhoneDAO extends BaseDAO<Telephone> {

    private static final String INSERT_STATEMENT = "INSERT INTO person_ref.telephone(id, telephone) values (NEXTVAL('person_ref.tlp_seq'), ?)";
    private static final String TABLE_NAME = "telephone";

    @Resource(name = "dummyJDBCTemplate")
    JdbcTemplate jdbcTemplate;

    @Autowired
    public PhoneDAO(@Qualifier(value = "dummyJDBCTemplate") JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    //setting the dummy vals in the obj from the table
    @Override
    public RowMapper<Telephone> getRowMapper() {
        return (resultSet, i) -> {
            Telephone tlp = new Telephone();
            tlp.setId(resultSet.getLong("id"));
            tlp.setPhone(resultSet.getString("telephone"));
            return tlp;
        };
    }

    @Override
    public void insertAll(List<Telephone> elements) {
        this.jdbcTemplate.batchUpdate(INSERT_STATEMENT, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, elements.get(i).getPhone());
            }

            @Override
            public int getBatchSize() {
                return elements.size();
            }
        });
    }

}
