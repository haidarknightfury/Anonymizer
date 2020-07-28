package com.smartfox.anonymizer.batch.anonymize.config;

import java.util.List;

import com.smartfox.anonymizer.batch.anonymize.component.AnonymizationCompletionListener;
import com.smartfox.anonymizer.batch.anonymize.component.AnonymizationItemWriter;
import com.smartfox.anonymizer.batch.anonymize.component.SourceItemProcessor;
import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.util.Pair;

import com.smartfox.anonymizer.batch.anonymize.model.SourceItem;
import com.github.javafaker.Faker;

/**
 * Anonymize configurations and steps
 *
 * @author hdargaye
 *
 */
@Configuration
public class AnonymizeBatchConfig {

    @Value("${batch.anonymize.source}")
    private String batchUpdateSource;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private TaskExecutor taskExecutor;

    @Bean
    public AnonymizationItemWriter anonymiseWriter() {
        return new AnonymizationItemWriter();
    }

    @Bean(name = "anonymize")
    public Step anonymizationStep() {
        return this.stepBuilderFactory.get("anonymize").<SourceItem, Pair<SourceItem, List<SourceValue>>>chunk(1).reader(this.sourceItemReader()).processor(this.sourceItemProcessor())
                .writer(this.anonymiseWriter()).taskExecutor(this.taskExecutor).build();
    }

    @Bean(name = "anonymizeJob")
    public Job anonymizeItemJob(AnonymizationCompletionListener completionListener, @Qualifier("anonymize") Step anonymizationStep) {
        return this.jobBuilderFactory.get("anonymizeJob").incrementer(new RunIdIncrementer()).listener(completionListener).flow(anonymizationStep).end().build();

    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public SourceItemProcessor sourceItemProcessor() {
        return new SourceItemProcessor();
    }

    @Bean
    public FlatFileItemReader<SourceItem> sourceItemReader() {
        return new FlatFileItemReaderBuilder<SourceItem>().name("sourceItemReader").resource(new ClassPathResource(this.batchUpdateSource)).delimited()
                .names(new String[] { "schema", "table", "column", "sourceType", "idName" }).fieldSetMapper(new BeanWrapperFieldSetMapper<SourceItem>() {

                    {
                        this.setTargetType(SourceItem.class);
                    }
                }).linesToSkip(1).build();
    }

}
