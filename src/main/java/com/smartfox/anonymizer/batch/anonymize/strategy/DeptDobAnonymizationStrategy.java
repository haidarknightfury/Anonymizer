package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymisationAsyncManager;

@Component
public class DeptDobAnonymizationStrategy implements Converter<List<SourceValue>, List<SourceValue>> {

    @Autowired
    private AnonymisationAsyncManager anonymisationAsyncMgr;
    
    @Override
    public List<SourceValue> convert(List<SourceValue> source) {
    	
    	Function<SourceValue, Object> deptDob = dept -> {
    		Random random = new Random();
    		String newDeptCode = null;
    		String deptcode = (String) dept.getValue();
    		
    		if(deptcode == null) {
    			int ran = random.nextInt(10000)+1000;
    			newDeptCode = Integer.toString(ran);
    		}
    		else if(deptcode.length()==2){
    			newDeptCode = deptcode;
    		}else{
    			int ran = random.nextInt(1000)+1000;
    			newDeptCode = Integer.toString(ran);
    		}
    			
    		return newDeptCode;
    	};
    	
        return this.anonymisationAsyncMgr.executeAsync(source, deptDob);
    }
}
