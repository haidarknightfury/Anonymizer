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
 * Anonymisation - return specific string  if value null or not
 * 
 * @author snathire
 *
 */
public class AsyncAnonymizationFunc<E> implements Callable<List<SourceValue>> {

	private Logger LOGGER = LoggerFactory.getLogger(AsyncAnonymizationFunc.class);

	private Spliterator<SourceValue> spliterator;
	private Function<SourceValue, String> transformationFunction;

	public AsyncAnonymizationFunc(Spliterator<SourceValue> spliterator,
			Function<SourceValue, String> transformationalFunction) {

		super();
		this.spliterator = spliterator;
		this.transformationFunction = transformationalFunction;

	}

	public List<SourceValue> call() throws Exception {

		this.LOGGER.info("{} Modifying values (tel Num and email so far) ", Thread.currentThread().getName());

		List<SourceValue> results = new ArrayList<>();

		this.spliterator.forEachRemaining(sourceval -> {
			
			//Passing the sourcevalue object to the func 
			//value changed
			String val = this.transformationFunction.apply(sourceval);
			
			//Original sourcevalue replaced 
			sourceval.setValue(val);
			
			//the updated Sourcevalue is added to the list
			results.add(sourceval);

		});

		//return list of modified values
		return results;
	}

}
