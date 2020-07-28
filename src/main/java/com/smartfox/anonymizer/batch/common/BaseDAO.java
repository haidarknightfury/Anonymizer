package com.smartfox.anonymizer.batch.common;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author hdargaye
 * Base DAO Class
 * @param <E>
 */

public abstract class BaseDAO<E> implements BaseDAOOperation<E> {

    private static final String FIND_ALL_QUERY = "SELECT * FROM person_ref.";

    protected JdbcTemplate jdbcTemplate;

    protected String tableName;

    public BaseDAO(JdbcTemplate jdbcTemplate, String tableName) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = tableName;
    }

    @Override
    public List<E> findAll(int limit) {
        return this.jdbcTemplate.query(FIND_ALL_QUERY + this.tableName + " LIMIT " + limit, this.getRowMapper());
    }

    @Override
    public List<E> findAll(int limit, int offset) {
        String query = FIND_ALL_QUERY + this.tableName + " LIMIT " + limit + " OFFSET " + offset;
        System.out.println(query);
        return this.jdbcTemplate.query(query, this.getRowMapper());
    }

    public abstract RowMapper<E> getRowMapper();

}
