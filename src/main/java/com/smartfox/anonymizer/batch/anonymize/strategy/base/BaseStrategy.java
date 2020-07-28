package com.smartfox.anonymizer.batch.anonymize.strategy.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.github.javafaker.Faker;

public abstract class BaseStrategy implements Converter<List<SourceValue>, List<SourceValue>> {

    @Autowired
    protected AnonymisationAsyncManager anonymisationAsyncMgr;

    @Autowired
    protected Faker faker;

    public BaseStrategy() {
        super();
    }

}