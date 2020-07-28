package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.List;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.smartfox.anonymizer.batch.dummy.entity.Person;
import com.smartfox.anonymizer.batch.dummy.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymisationAsyncManager;

@Component
public class PobAnonymizationStrategy implements Converter<List<SourceValue>, List<SourceValue>> {

    @Autowired
    private AnonymisationAsyncManager anonymisationAsyncMgr;

    @Autowired
    PersonDAO personRepository;

    @Override
    public List<SourceValue> convert(List<SourceValue> source) {
        return this.anonymisationAsyncMgr.<Person>executeAsync(source, this.personRepository, Person::getCity);
    }

}
