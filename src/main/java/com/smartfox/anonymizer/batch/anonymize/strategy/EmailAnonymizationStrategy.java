package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.List;
import java.util.function.Function;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymisationAsyncManager;

/**
 * Email Anonymization Strategy
 * 
 * @author hdargaye
 *
 */
@Component
public class EmailAnonymizationStrategy implements Converter<List<SourceValue>, List<SourceValue>> {

	@Autowired
	AnonymisationAsyncManager anonymisationAsyncMgr;

	@Override
	public List<SourceValue> convert(List<SourceValue> source) {

		Function<SourceValue, Object> anonEmail = num -> {

			String val = " ";
			String sourceval = (String)num.getValue();
			
			if (sourceval!= null) {
				val = "oui@dso-capital.com";

			} else {
				val = "non@dso-capital.com";
			}

			return val;
		};

		return this.anonymisationAsyncMgr.executeAsync(source, anonEmail);
	}


}
