package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.List;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.smartfox.anonymizer.batch.dummy.repository.PersonDAO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymisationAsyncManager;
import com.smartfox.anonymizer.batch.dummy.entity.Person;

@Component
public class AddressAnonymizationStrategy implements Converter<List<SourceValue>, List<SourceValue>> {

    AnonymisationAsyncManager anonymisationAsyncMgr;

    PersonDAO personRepository;
	@Override
	public List<SourceValue> convert(List<SourceValue> source) {
		// replace source with a random value (street, city)
		return this.anonymisationAsyncMgr.executeAsync(source, this.personRepository, Person::getAddress);
	}
}
