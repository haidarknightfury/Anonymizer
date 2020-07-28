package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.ArrayList;
import java.util.List;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.smartfox.anonymizer.batch.dummy.entity.Email;
import com.smartfox.anonymizer.batch.dummy.repository.EmailDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailAnonymizationStrategyTest {

    @Mock
    EmailDAO emailRepository;

    @InjectMocks
    public EmailAnonymizationStrategy mockEmail;

    @Test
    public void should_replace_good_values_with_fake() {

        List<Email> fakeEmails = new ArrayList<Email>();
        fakeEmails.add(new Email("jason@mail.com"));
        fakeEmails.add(new Email("test@mail.com"));
        Mockito.when(this.emailRepository.findAll(Mockito.anyInt())).thenReturn(fakeEmails);

        List<SourceValue> sourceValues = new ArrayList<>();
        sourceValues.add(new SourceValue("1", "haidardargaye@dsogroup.com"));
        sourceValues.add(new SourceValue("2", "jasonchong@dsogroup.com"));

        List<SourceValue> modifiedSourceValues = this.mockEmail.convert(sourceValues);
        Assert.assertTrue(modifiedSourceValues.stream().filter(v -> v.getValue().equals("jasonchong@dsogroup.com")).count() == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_fake_email_empty() {
        List<Email> fakeEmails = new ArrayList<Email>();
        Mockito.when(this.emailRepository.findAll(Mockito.anyInt())).thenReturn(fakeEmails);
        List<SourceValue> sourceValues = new ArrayList<>();
        sourceValues.add(new SourceValue("1", "haidardargaye@dsogroup.com"));
        List<SourceValue> modifiedSourceValues = this.mockEmail.convert(sourceValues);
    }

}
