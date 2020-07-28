package com.smartfox.anonymizer.batch.anonymize.component;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.stereotype.Component;

@Component
public class NoPersistenceBatchConfigurer extends DefaultBatchConfigurer {

    @Override
    public void setDataSource(DataSource dataSource) {
        // Use In memory MapJobRepository
    }

}
