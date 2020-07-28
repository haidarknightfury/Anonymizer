package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.List;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymisationAsyncManager;

@Component
public class eraseAnonymizationStrategy implements Converter<List<SourceValue>, List<SourceValue>> {
	  @Autowired
	    private AnonymisationAsyncManager anonymisationAsyncMgr;
	  
	  @Override
	    public List<SourceValue> convert(List<SourceValue> source) {
	        return this.anonymisationAsyncMgr.executeAsync(source,() -> null);
	    }

}
