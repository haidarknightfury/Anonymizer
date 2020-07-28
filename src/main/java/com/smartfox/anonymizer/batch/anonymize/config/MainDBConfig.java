package com.smartfox.anonymizer.batch.anonymize.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Database configuration - Find a way to load configurations dynamically from
 * the config files
 * 
 * @author hdargaye
 *
 */

@Configuration
@EnableTransactionManagement
public class MainDBConfig {

    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "mainDataSource")
    public DataSource mainDataSource() {
        HikariConfig dataSource = new HikariConfig();
        dataSource.setDriverClassName(this.env.getProperty("ds.database-driver1"));
        dataSource.setJdbcUrl(this.env.getProperty("ds.url1"));
        dataSource.setUsername(this.env.getProperty("ds.username1"));
        dataSource.setPassword(this.env.getProperty("ds.password1"));
        return new HikariDataSource(dataSource);
    }

    @Primary
    @Bean(name = "mainJDBCTemplate")
    public JdbcTemplate mainJDBCTemplate(@Qualifier("mainDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Primary
    @Bean(name = "mainTransactionManager")
    public PlatformTransactionManager mainTransactionManager(@Qualifier("mainDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
