package com.smartfox.anonymizer.batch.dummy.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import com.smartfox.anonymizer.batch.dummy.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.smartfox.anonymizer.batch.dummy.repository.base.BaseDAO;

@Repository
public class CommentDAO extends BaseDAO<Comment> {

    private static final String INSERT_STATEMENT = "INSERT INTO person_ref.comment(id, comment) values (NEXTVAL('person_ref.comment_seq'), ?)";

    private static final String TABLE_NAME = "comment";

    @Resource(name = "dummyJDBCTemplate")
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDAO(@Qualifier(value = "dummyJDBCTemplate") JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, TABLE_NAME);
    }

    @Override
    public RowMapper<Comment> getRowMapper() {
        return (resultSet, i) -> {
            Comment comment = new Comment();
            comment.setId(resultSet.getLong("id"));
            comment.setComment(resultSet.getString("comment"));
            return comment;
        };

    }

    @Override
    public void insertAll(List<Comment> elements) {
        this.jdbcTemplate.batchUpdate(INSERT_STATEMENT, new BatchPreparedStatementSetter() {

            @Override
            public int getBatchSize() {
                return elements.size();
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, elements.get(i).getComment());
            }
        });
    }

}
