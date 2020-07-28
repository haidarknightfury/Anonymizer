package com.smartfox.anonymizer.batch.dummy.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableTransactionManagement
public class DummyDataDBConfig {

    @Autowired
    private Environment env;

    @Bean(name = "dummyDataSource")
    public DataSource dummyDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.env.getProperty("ds.anonymizer.driver"));
        dataSource.setUrl(this.env.getProperty("ds.anonymizer.url"));
        dataSource.setUsername(this.env.getProperty("ds.anonymizer.username"));
        dataSource.setPassword(this.env.getProperty("ds.anonymizer.password"));
        return dataSource;
    }

    @Bean(name = "dummyJDBCTemplate")
    public JdbcTemplate dummyJDBCTemplate(@Qualifier(value = "dummyDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "dummyTransactionManager")
    public PlatformTransactionManager dummyTransactionManager(@Qualifier("dummyDataSource") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    @Bean
    public SpringLiquibase liquibase(@Qualifier(value = "dummyDataSource") DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:batch/dummy/db-changelog.xml");
        liquibase.setDefaultSchema("person_ref");
        return liquibase;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
