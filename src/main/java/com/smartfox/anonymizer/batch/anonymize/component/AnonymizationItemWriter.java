package com.smartfox.anonymizer.batch.anonymize.component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import com.smartfox.anonymizer.batch.anonymize.model.SourceItem;
import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Anonymization Item Writer - To write the modified items to the database -
 * performing update statements
 *
 * @author hdargaye
 *
 */
@Component
public class AnonymizationItemWriter implements ItemWriter<Pair<SourceItem, List<SourceValue>>> {

    private static final String PUBLIC = "PUBLIC";

    @Resource(name = "mainJDBCTemplate")
    JdbcTemplate jdbcTemplate;

    private Logger LOGGER = LoggerFactory.getLogger(AnonymizationItemWriter.class);

    @Override
    public void write(List<? extends Pair<SourceItem, List<SourceValue>>> items) throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start(String.format("Thread %s", Thread.currentThread().getName()));

        this.LOGGER.info("Thread {} writing changes", Thread.currentThread().getName());
        StringBuilder sqlBuilder = new StringBuilder();

        writetoDb(items, sqlBuilder);

        stopWatch.stop();
        this.LOGGER.info(stopWatch.prettyPrint());
    }

	private synchronized void  writetoDb(List<? extends Pair<SourceItem, List<SourceValue>>> items, StringBuilder sqlBuilder) {
		items.stream().forEach(pair ->
            {

                sqlBuilder.setLength(0);
                SourceItem sourceItem = pair.getFirst();
                String tableName = sourceItem.getSchema() == null || sourceItem.getSchema().equalsIgnoreCase(PUBLIC) ? sourceItem.getTable() : sourceItem.getSchema() + "." + sourceItem.getTable();
                sqlBuilder.append("UPDATE " + tableName);
                sqlBuilder.append(" SET " + sourceItem.getColumn() + " = ?");
                sqlBuilder.append(" WHERE " + sourceItem.getIdName() + " = ?");

                // this.LOGGER.info("Executing SQL for updates {}", sqlBuilder.toString());
                this.jdbcTemplate.batchUpdate(sqlBuilder.toString(), new BatchPreparedStatementSetter() {

                    @Override
                    public int getBatchSize() {
                        return pair.getSecond().size();
                    }

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setObject(1, pair.getSecond().get(i).getValue());
                        // ps.setInt(2, Integer.valueOf(pair.getSecond().get(i).getId()));
                        ps.setObject(2, pair.getSecond().get(i).getId());
                    }
                });
            });
	}

}
