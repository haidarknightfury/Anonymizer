package com.smartfox.anonymizer.batch.anonymize.strategy.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Anonymisation - Without using fake repository Takes a supplier as input
 * 
 * @author hdargaye
 *
 */

public class AsyncAnonymizationWithoutRepoAccess implements Callable<List<SourceValue>> {

    private Logger LOGGER = LoggerFactory.getLogger(AsyncAnonymizationWithoutRepoAccess.class);

    private Spliterator<SourceValue> spliterator;
    private Supplier<String> transformationFunction;

    public AsyncAnonymizationWithoutRepoAccess(Spliterator<SourceValue> spliterator, Supplier<String> transformationFunction) {
        super();
        this.spliterator = spliterator;
        this.transformationFunction = transformationFunction;
    }
    

    @Override
    public List<SourceValue> call() throws Exception {
        this.LOGGER.info("{} Modifying values ", Thread.currentThread().getName());
       
        List<SourceValue> results = new ArrayList<>();
        
        // 2. Loop on spliterator and replace
        this.spliterator.forEachRemaining(t -> {
            t.setValue(this.transformationFunction.get());
            results.add(t);
        });

        // 3. return list of modified values
        return results;

    }
    
}
