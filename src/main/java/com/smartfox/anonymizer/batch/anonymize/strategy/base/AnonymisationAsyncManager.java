package com.smartfox.anonymizer.batch.anonymize.strategy.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.smartfox.anonymizer.batch.common.BaseDAOOperation;

/**
 * Async Manager class - responsible for managing partition Right now only value
 * list divided by 2
 *
 * @author hdargaye
 *
 */
@Component
public class AnonymisationAsyncManager {

    private Logger LOGGER = LoggerFactory.getLogger(AnonymisationAsyncManager.class);

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    
    //USED ONLY FOR NAME/SURNAME/ADDRESS
    public <E> List<SourceValue> executeAsync(List<SourceValue> source, BaseDAOOperation<E> baseOperation, Function<E, String> transformationalFunction) {
        // this.LOGGER.info("Changing values from database {}", source);

    	//split already done
    	//the second half of the true values
        Spliterator<SourceValue> sp1 = source.spliterator();
        
        //the first half of the true value 
        Spliterator<SourceValue> sp2 = sp1.trySplit();

        Callable<List<SourceValue>> partition1 = new AsyncAnonymization<E>(sp1, baseOperation, 0, transformationalFunction);
        Callable<List<SourceValue>> partition2 = new AsyncAnonymization<E>(sp2, baseOperation, (int) sp1.estimateSize(), transformationalFunction);

        return this.getAsyncValues(partition1, partition2);
    }

    public List<SourceValue> executeAsync(List<SourceValue> source, Supplier<String> transformationalFunction) {
    	Spliterator<SourceValue> sp1 = source.spliterator();
        Spliterator<SourceValue> sp2 = sp1.trySplit();
        
        Callable<List<SourceValue>> partition1 = new AsyncAnonymizationWithoutRepoAccess(sp1, transformationalFunction);
        Callable<List<SourceValue>> partition2 = new AsyncAnonymizationWithoutRepoAccess(sp2, transformationalFunction);

        return this.getAsyncValues(partition1, partition2);

    }
    

    public List<SourceValue> executeAsync(List<SourceValue> source, Function<SourceValue, Object> func) {
        Spliterator<SourceValue> sp1 = source.spliterator();
        Spliterator<SourceValue> sp2 = sp1.trySplit();

        Callable<List<SourceValue>> partition1 = new AsyncAnonymizationWithoutRepoAccessObj(sp1, func);
        Callable<List<SourceValue>> partition2 = new AsyncAnonymizationWithoutRepoAccessObj(sp2, func);

        return this.getAsyncValues(partition1, partition2);

    }
    


    private List<SourceValue> getAsyncValues(Callable<List<SourceValue>> partition1, Callable<List<SourceValue>> partition2) {
        List<SourceValue> results = new ArrayList<SourceValue>();
        try {
            List<Future<List<SourceValue>>> futureList = this.taskExecutor.getThreadPoolExecutor().invokeAll(Arrays.asList(partition1, partition2));
            for (Future<List<SourceValue>> val : futureList) {
                List<SourceValue> res = val.get();
                results.addAll(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
    


    
}
