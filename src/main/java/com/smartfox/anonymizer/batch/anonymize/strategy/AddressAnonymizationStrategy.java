package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.List;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.smartfox.anonymizer.batch.anonymize.strategy.base.AnonymisationAsyncManager;
import com.smartfox.anonymizer.batch.anonymize.strategy.base.BaseStrategy;
import org.springframework.stereotype.Component;

@Component
public class AddressAnonymizationStrategy extends BaseStrategy {

    AnonymisationAsyncManager anonymisationAsyncMgr;

    @Override
    public List<SourceValue> convert(List<SourceValue> source) {
        return this.anonymisationAsyncMgr.executeAsync(source, () ->
            {
                return this.faker.address().secondaryAddress();
            });
    }
}
