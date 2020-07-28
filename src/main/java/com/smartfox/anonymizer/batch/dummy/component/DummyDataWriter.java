package com.smartfox.anonymizer.batch.dummy.component;

import java.util.ArrayList;
import java.util.List;

import com.smartfox.anonymizer.batch.dummy.entity.Email;
import com.smartfox.anonymizer.batch.dummy.entity.Person;
import com.smartfox.anonymizer.batch.dummy.entity.Telephone;
import com.smartfox.anonymizer.batch.dummy.repository.EmailDAO;
import com.smartfox.anonymizer.batch.dummy.repository.PersonDAO;
import com.smartfox.anonymizer.batch.dummy.repository.PhoneDAO;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smartfox.anonymizer.batch.dummy.dto.RootResponse;

@Component
public class DummyDataWriter implements ItemWriter<RootResponse> {

    @Autowired
    EmailDAO emailRepository;

    @Autowired
    PersonDAO personRepository;

    @Autowired
    PhoneDAO telephoneRepository;

    @Override
    public void write(List<? extends RootResponse> items) throws Exception {

        for (RootResponse rootResponse : items) {
            List<Person> persons = new ArrayList<Person>();
            List<Telephone> phones = new ArrayList<Telephone>();
            List<Email> emails = new ArrayList<Email>();

            rootResponse.getResults().stream().forEach(result -> {
                persons.add(new Person(result.getGender(), result.getName().getFirst(), result.getName().getLast(), result.getLocation().getCity(), result.getLocation().getStreet()));
                emails.add(new Email(result.getEmail()));
                phones.add(new Telephone(result.getPhone()));
            });

            System.out.println(emails);
            System.out.println(persons);
            System.out.println(phones);

            this.personRepository.insertAll(persons);
            this.emailRepository.insertAll(emails);
            this.telephoneRepository.insertAll(phones);
        }

    }

}
