package com.smartfox.anonymizer.batch.anonymize.component;

import java.util.List;

import javax.annotation.Resource;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymizationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.smartfox.anonymizer.batch.anonymize.model.SourceItem;
import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;

/**
 *
 * @author hdargaye
 *
 *         Source Item Processor - Retrieving values from the Database and group
 *         them by SourceItem SourceValue containing the actual value from DB
 *         and the ID to be used for updates SourceItem is the value from the
 *         CSV File Row
 *
 */
public class SourceItemProcessor implements ItemProcessor<SourceItem, Pair<SourceItem, List<SourceValue>>> {

    private static final String PUBLIC = "PUBLIC";

    @Autowired
    private AnonymizationStrategy anonymizationStrategy;

    @Resource(name = "mainJDBCTemplate")
    private JdbcTemplate jdbcTemplate;

    private Logger LOGGER = LoggerFactory.getLogger(SourceItemProcessor.class);

    @Override
    public Pair<SourceItem, List<SourceValue>> process(SourceItem sourceItem) throws Exception {

        // 1. Get all values from DB
        StringBuilder sqlBuilder = new StringBuilder();
        String tableName = sourceItem.getSchema() == null || sourceItem.getSchema().equalsIgnoreCase(PUBLIC) ? sourceItem.getTable() : sourceItem.getSchema() + "." + sourceItem.getTable();
        sqlBuilder.append("SELECT " + sourceItem.getColumn() + " , " + sourceItem.getIdName());
        sqlBuilder.append(" FROM " + tableName);

        // this.LOGGER.info("Executing SQL " + sqlBuilder.toString());

        List<SourceValue> values = this.jdbcTemplate.query(sqlBuilder.toString(), (RowMapper<SourceValue>) (rs, rownumber) ->
            {
                // Modify values
                // TODO: Do not need the real value of the table - Just need the ID
                // ResultSetMetaData resultSetMetaData = rs.getMetaData();
                // Integer idType = resultSetMetaData.getColumnType(1);
                // this.LOGGER.info("ID TYPE is {}", idType);
                SourceValue sourceValue = new SourceValue(rs.getObject(2), rs.getString(1));
                return sourceValue;
            });

        this.LOGGER.info("Source Values Retrieved from the Database");
        return Pair.of(sourceItem, this.anonymizationStrategy.anonymize(values, sourceItem));
    }

}