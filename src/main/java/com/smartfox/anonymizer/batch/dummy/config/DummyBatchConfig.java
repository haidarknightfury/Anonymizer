package com.smartfox.anonymizer.batch.dummy.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.smartfox.anonymizer.batch.dummy.component.DummyDataReader;
import com.smartfox.anonymizer.batch.dummy.component.DummyDataWriter;
import com.smartfox.anonymizer.batch.dummy.dto.RootResponse;

@Configuration
public class DummyBatchConfig {

    @Autowired
    private JobBuilderFactory JobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean("dummyDataJob")
    public Job dummyDataJob() {
        return this.JobBuilderFactory.get("dummyDataJob").incrementer(new RunIdIncrementer()).flow(this.fetchDummyData()).end().build();
    }

    @Bean
    public DummyDataReader dummyDataReader() {
        return new DummyDataReader();
    }

    @Bean
    public Step fetchDummyData() {
        return this.stepBuilderFactory.get("fetchDummy").<RootResponse, RootResponse>chunk(1).reader(this.dummyDataReader()).writer(this.writer()).build();
    }

    @Bean
    public DummyDataWriter writer() {
        return new DummyDataWriter();
    }
}
