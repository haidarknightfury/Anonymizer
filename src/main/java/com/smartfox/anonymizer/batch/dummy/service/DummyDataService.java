package com.smartfox.anonymizer.batch.dummy.service;

import com.smartfox.anonymizer.batch.dummy.repository.CommentDAO;
import com.smartfox.anonymizer.batch.dummy.repository.EmailDAO;
import com.smartfox.anonymizer.batch.dummy.repository.PersonDAO;
import com.smartfox.anonymizer.batch.dummy.repository.PhoneDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.smartfox.anonymizer.batch.dummy.dto.RootResponse;

@Component
public class DummyDataService {

    private static final Logger log = LoggerFactory.getLogger(DummyDataService.class);

    EmailDAO emailRepository;

    PersonDAO personRepository;

    CommentDAO commentRepository;

    @Value("${randomurl}")
    private String randomUrl;

    PhoneDAO telephoneRepository;

    @Autowired
    public DummyDataService(EmailDAO emailRepository, PersonDAO personRepository, PhoneDAO telephoneRepository, CommentDAO commentRepository) {
        this.emailRepository = emailRepository;
        this.personRepository = personRepository;
        this.telephoneRepository = telephoneRepository;
        this.commentRepository = commentRepository;
    }

    public RootResponse saveDummyData() {

        RestTemplate restTemplate = new RestTemplate();
        RootResponse rootResponse = restTemplate.getForObject(this.randomUrl, RootResponse.class);
        log.info("result is {}", rootResponse.toString());

        return rootResponse;

    }
}
