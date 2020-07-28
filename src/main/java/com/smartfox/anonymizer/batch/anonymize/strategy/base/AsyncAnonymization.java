package com.smartfox.anonymizer.batch.anonymize.strategy.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.function.Function;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartfox.anonymizer.batch.dummy.repository.base.BaseDAOOperation;

/**
 * Asynchronously anonymise the list of source values - callable class for asynchronous performance
 * @author hdargaye
 *
 */

//USED FOR NAME/SURNAME
public class AsyncAnonymization<E> implements Callable<List<SourceValue>> {

    private BaseDAOOperation<E> daoOperation;
    private Logger LOGGER = LoggerFactory.getLogger(AsyncAnonymization.class);
    private Integer offset;
    private Spliterator<SourceValue> spliterator;
    private Function<E, String> transformationFunction;

    public AsyncAnonymization(Spliterator<SourceValue> spliterator, BaseDAOOperation<E> daoOperation, Integer offset, Function<E, String> transformationFunction) {
        this.spliterator = spliterator;
        this.daoOperation = daoOperation;
        this.offset = offset;
        this.transformationFunction = transformationFunction;
    }

    @Override
    public List<SourceValue> call() throws Exception {

        this.LOGGER.info("{} Modifying values ", Thread.currentThread().getName());

        List<SourceValue> results = new ArrayList<>();

        if (this.spliterator == null) {
            return new ArrayList<SourceValue>();
        }
        
         // 1. Find list of fake values
        List<E> fakeValue = this.daoOperation.findAll((int) this.spliterator.estimateSize(), this.offset);
        Stack<E> fakeValuesStack = new Stack<>();
        fakeValuesStack.addAll(fakeValue); // TODO : may need another data structure

        // 2. Loop on spliterator and replace,
        this.spliterator.forEachRemaining(t ->
            {
                String value = this.transformationFunction.apply(fakeValuesStack.pop());
				t.setValue(value);
                results.add(t);
            });

        // 3. return list of modified values
        return results;
    }

}
