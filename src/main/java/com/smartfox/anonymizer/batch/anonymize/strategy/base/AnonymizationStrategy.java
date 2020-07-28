package com.smartfox.anonymizer.batch.anonymize.strategy.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.anonymize.model.SourceItem;
import com.smartfox.anonymizer.batch.anonymize.model.SourceType;
import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.smartfox.anonymizer.batch.anonymize.strategy.AddressAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.CityAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.CommentAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.DeptDobAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.DobAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.EmailAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.EraseFieldAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.NameAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.PobAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.PostalAddressAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.SurnameAnonymizationStrategy;
import com.smartfox.anonymizer.batch.anonymize.strategy.TelephoneAnonymizationStrategy;

/**
 * Anonymization Strategy class - Selects the algorithm based on the type of
 * data
 *
 * @author hdargaye
 *
 */
@Component
public class AnonymizationStrategy implements InitializingBean {

    private Map<SourceType, Converter<List<SourceValue>, List<SourceValue>>> convertorMap = new HashMap<SourceType, Converter<List<SourceValue>, List<SourceValue>>>();

    @Autowired
    private EmailAnonymizationStrategy emailAnonymization;

    @Autowired
    private NameAnonymizationStrategy nameAnonymization;

    @Autowired
    private TelephoneAnonymizationStrategy phoneAnonymization;

    @Autowired
    private AddressAnonymizationStrategy addressAnonymization;

    @Autowired
    private PobAnonymizationStrategy pobAnonymization;

    @Autowired
    private CommentAnonymizationStrategy commentAnonymization;

    @Autowired
    private SurnameAnonymizationStrategy surnameAnonymization;

    @Autowired
    private PostalAddressAnonymizationStrategy postalAddressAnonymization;

    @Autowired
    private DobAnonymizationStrategy dobAnonymization;

    @Autowired
    private EraseFieldAnonymizationStrategy eraseFieldAnonymization;

    @Autowired
    private DeptDobAnonymizationStrategy deptDobAnonymization;

    @Autowired
    private CityAnonymizationStrategy cityAnonymization;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.convertorMap.put(SourceType.NAME, this.nameAnonymization);
        this.convertorMap.put(SourceType.EMAIL, this.emailAnonymization);
        this.convertorMap.put(SourceType.PHONE, this.phoneAnonymization);
        this.convertorMap.put(SourceType.ADDRESS, this.addressAnonymization);
        this.convertorMap.put(SourceType.POB, this.pobAnonymization);
        this.convertorMap.put(SourceType.COMMENT, this.commentAnonymization);
        this.convertorMap.put(SourceType.SURNAME, this.surnameAnonymization);
        this.convertorMap.put(SourceType.POCO, this.postalAddressAnonymization);
        this.convertorMap.put(SourceType.DOB, this.dobAnonymization);
        this.convertorMap.put(SourceType.ERASE, this.eraseFieldAnonymization);
        this.convertorMap.put(SourceType.DEPTDOB, this.deptDobAnonymization);
        this.convertorMap.put(SourceType.CITY, this.cityAnonymization);

    }

    public List<SourceValue> anonymize(List<SourceValue> original, SourceItem sourceItem) {
        if (!this.convertorMap.containsKey(sourceItem.getSourceType())) {
            throw new IllegalArgumentException(sourceItem.getSourceType() + " does not have any implementation yet");
        }
        return this.convertorMap.get(sourceItem.getSourceType()).convert(original);
    }

}
