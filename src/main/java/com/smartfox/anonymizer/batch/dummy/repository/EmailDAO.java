package com.smartfox.anonymizer.batch.dummy.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import com.smartfox.anonymizer.batch.dummy.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.smartfox.anonymizer.batch.dummy.repository.base.BaseDAO;

/**
 * Email DAO Class
 *
 * @author hdargaye
 *
 */
@Repository
public class EmailDAO extends BaseDAO<Email> {

    private static final String INSERT_STATEMENT = "INSERT INTO person_ref.email(id, email) values (NEXTVAL('person_ref.email_seq'), ?)";

    private static final String TABLE_NAME = "email";

    @Resource(name = "dummyJDBCTemplate")
    JdbcTemplate jdbcTemplate;

    @Autowired
    public EmailDAO(@Qualifier(value = "dummyJDBCTemplate") JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    public RowMapper<Email> getRowMapper() {
        return (resultSet, i) ->
            {
                Email email = new Email();
                email.setId(resultSet.getLong("id"));
                email.setEmail(resultSet.getString("email"));
                return email;
            };

    }

    @Override
    public void insertAll(List<Email> elements) {
        this.jdbcTemplate.batchUpdate(INSERT_STATEMENT, new BatchPreparedStatementSetter() {

            @Override
            public int getBatchSize() {
                return elements.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, elements.get(i).getEmail());
            }
        });
    }

}
