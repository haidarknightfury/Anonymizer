package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.List;
import java.util.function.Function;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymisationAsyncManager;

@Component
public class TelephoneAnonymizationStrategy implements Converter<List<SourceValue>, List<SourceValue>> {

	@Autowired
	private AnonymisationAsyncManager anonymisationAsyncMgr;

	@Override
	public List<SourceValue> convert(List<SourceValue> source) {

		// Function return val based on sourceval if null or not
		Function<SourceValue, Object> anonTelNum = num -> {

			String sourceval = (String)num.getValue();
			String val = " ";
	

			if (sourceval != null) {
				val = "1111111111";

			} else {
				val = "0000000000";
			}

			return val;
		};

		// the source(true val), telephoneRepository(dummy type)
		// return this.anonymisationAsyncMgr.<Telephone>executeAsync(source,
		// this.telephoneRepository, Telephone::getPhone);

		return this.anonymisationAsyncMgr.executeAsync(source, anonTelNum);
	}

}
