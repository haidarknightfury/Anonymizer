package com.smartfox.anonymizer.batch.anonymize.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Listener for the completion of batch process -
 * TODO : Add support for email sending
 * @author hdargaye
 *
 */
@Component
public class AnonymizationCompletionListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(AnonymizationCompletionListener.class);

    @Autowired
    ThreadPoolTaskExecutor executor;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("ANONYMIZATION COMPLETE");
        }
        // TODO: Find a good way for it to terminate automatically
        this.executor.shutdown();
        super.afterJob(jobExecution);
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("ATTEMPTING TO ANONYMIZE RECORDS");
        super.beforeJob(jobExecution);
    }

}
