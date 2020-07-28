package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.ArrayList;
import java.util.List;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.smartfox.anonymizer.batch.dummy.entity.Telephone;
import com.smartfox.anonymizer.batch.dummy.repository.PhoneDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TelephoneAnonymizationStrategyTest {

    @InjectMocks
    public TelephoneAnonymizationStrategy mockTelephone;

    @Mock
    PhoneDAO telephoneRepository;

    @Test
    public void should_replace_good_values_with_fake() {

        List<Telephone> fakePhones = new ArrayList<Telephone>();
        fakePhones.add(new Telephone("59290104"));
        fakePhones.add(new Telephone("58292390"));
        Mockito.when(telephoneRepository.findAll(Mockito.anyInt())).thenReturn(fakePhones);

        List<SourceValue> sourceValues = new ArrayList<>();
        sourceValues.add(new SourceValue("1", "5522341"));
        sourceValues.add(new SourceValue("2", "3452529"));

        List<SourceValue> modifiedSourceValues = this.mockTelephone.convert(sourceValues);
        Assert.assertTrue(modifiedSourceValues.stream().filter(v -> v.getValue().contentEquals("3452529")).count() == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_fake_phone_empty() {
        List<Telephone> fakePhones = new ArrayList<Telephone>();
        Mockito.when(telephoneRepository.findAll(Mockito.anyInt())).thenReturn(fakePhones);
        List<SourceValue> sourceValues = new ArrayList<>();
        sourceValues.add(new SourceValue("1", "25426663"));
        List<SourceValue> modifySourceValues = this.mockTelephone.convert(sourceValues);
    }
}
