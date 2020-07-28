package com.smartfox.anonymizer;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = { WebMvcAutoConfiguration.class })
@EnableBatchProcessing
public class AnonymizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnonymizerApplication.class, args);
    }

}
