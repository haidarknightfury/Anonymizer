package com.smartfox.anonymizer.batch.anonymize.strategy;

import java.util.ArrayList;
import java.util.List;

import com.smartfox.anonymizer.batch.anonymize.model.SourceValue;
import com.smartfox.anonymizer.batch.dummy.entity.Person;
import com.smartfox.anonymizer.batch.dummy.repository.PersonDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NameAnonymizationStrategyTest {

    @InjectMocks
    public NameAnonymizationStrategy mockName;

    @Mock
    PersonDAO personRepository;

    @Test
    public void should_replace_good_values_with_fake() {
        List<Person> fakePerson = new ArrayList<Person>();
        fakePerson.add(new Person("Port-Louis", "Jason", "Male", "Chong", "Newton"));
        fakePerson.add(new Person("Tombeau", "Jane", "Female", "Doe", "Swan"));
        Mockito.when(personRepository.findAll(Mockito.anyInt())).thenReturn(fakePerson);

        List<SourceValue> sourceValues = new ArrayList<SourceValue>();
        sourceValues.add(new SourceValue("1", "jason"));
        sourceValues.add(new SourceValue("2", "haidar"));

        List<SourceValue> modifiedSourceValues = this.mockName.convert(sourceValues);
        Assert.assertTrue(modifiedSourceValues.stream().filter(v -> v.getValue().equals("jason")).count() == 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_when_fake_person_empty() {
        List<Person> fakePerson = new ArrayList<Person>();
        Mockito.when(personRepository.findAll(Mockito.anyInt())).thenReturn(fakePerson);
        List<SourceValue> sourceValues = new ArrayList<>();
        sourceValues.add(new SourceValue("1", "haidar"));
        List<SourceValue> modifiedSourceValues = this.mockName.convert(sourceValues);

    }

}
