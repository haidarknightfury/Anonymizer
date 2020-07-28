package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.smartfox.anonymizer.batch.anonymize.strategy.base.BaseStrategy;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;

@Component
public class DeptDobAnonymizationStrategy extends BaseStrategy {

    @Override
    public List<SourceValue> convert(List<SourceValue> source) {

        Function<SourceValue, Object> deptDob = dept ->
            {
                Random random = new Random();
                String newDeptCode = null;
                String deptcode = (String) dept.getValue();

                if (deptcode == null) {
                    int ran = random.nextInt(10000) + 1000;
                    newDeptCode = Integer.toString(ran);
                } else if (deptcode.length() == 2) {
                    newDeptCode = deptcode;
                } else {
                    int ran = random.nextInt(1000) + 1000;
                    newDeptCode = Integer.toString(ran);
                }

                return newDeptCode;
            };

        return this.anonymisationAsyncMgr.executeAsync(source, deptDob);
    }
}
