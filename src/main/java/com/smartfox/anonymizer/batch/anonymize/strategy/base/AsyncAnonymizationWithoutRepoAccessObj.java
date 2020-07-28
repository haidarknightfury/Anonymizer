package com.smartfox.anonymizer.batch.anonymize.strategy.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.function.Function;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Anonymisation - Without using fake repository Takes a supplier as input
 * 
 * @author hdargaye
 * @author kbechoo
 *
 */

public class AsyncAnonymizationWithoutRepoAccessObj implements Callable<List<SourceValue>> {

    private Logger LOGGER = LoggerFactory.getLogger(AsyncAnonymizationWithoutRepoAccessObj.class);

    private Spliterator<SourceValue> spliterator;
    private Function<SourceValue, Object> func;

    public AsyncAnonymizationWithoutRepoAccessObj(Spliterator<SourceValue> spliterator, Function<SourceValue, Object> func) {
        super();
        this.spliterator = spliterator;
        this.func = func;
        }
    
    @Override
    public List<SourceValue> call() throws Exception {
        this.LOGGER.info("{} Modifying values ", Thread.currentThread().getName());

        List<SourceValue> results = new ArrayList<>();
//        Stack<SourceValue> results = new Stack<SourceValue>();
        	
        
        // 2. Loop on spliterator and Call Function in Strategy class
        this.spliterator.forEachRemaining(sourceVal -> {
            
        	sourceVal.setValue((this.func).apply(sourceVal));
        		results.add(sourceVal);
        });
        
        // 3. return list of modified values
        return results;

    }

}
