package com.smartfox.anonymizer.batch.dummy.component;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import com.smartfox.anonymizer.batch.dummy.service.DummyDataService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.smartfox.anonymizer.batch.dummy.dto.RootResponse;

public class DummyDataReader implements ItemReader<RootResponse> {

    private AtomicInteger counter;

    @Autowired
    public DummyDataService dummyDataService;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        this.counter = new AtomicInteger(Integer.valueOf(this.env.getProperty("times")));
    }

    @Override
    public RootResponse read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (this.counter.decrementAndGet() >= 0) {
            return this.dummyDataService.saveDummyData();
        }
        return null;
    }

}
