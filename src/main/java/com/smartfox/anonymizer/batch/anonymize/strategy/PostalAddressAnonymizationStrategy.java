package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.smartfox.anonymizer.batch.dummy.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymisationAsyncManager;

@Component
public class PostalAddressAnonymizationStrategy implements Converter<List<SourceValue>, List<SourceValue>> {

    @Autowired
    private AnonymisationAsyncManager anonymisationAsyncMgr;
    
    @Autowired
    PersonDAO personRepository;

    
    @Override
    public List<SourceValue> convert(List<SourceValue> source) {
    	
    	Function<SourceValue, Object> poco = cp -> {
    		Random random = new Random();
    		String newPostalCode = null;
    		String postalCode = (String) cp.getValue();
    		
    		if(postalCode==null) {
    			int ran = random.nextInt(100000);
    			newPostalCode = Integer.toString(ran);
    		}else {
    		int randonNum = random.nextInt(900) + 100;
    		
    		String firstTwo = postalCode.substring(0, Math.min(postalCode.length(), 2));
    		newPostalCode = (firstTwo + randonNum);
    		}
    			
    		return newPostalCode;
    	};
    	
        return this.anonymisationAsyncMgr.executeAsync(source, poco);

    }
}
