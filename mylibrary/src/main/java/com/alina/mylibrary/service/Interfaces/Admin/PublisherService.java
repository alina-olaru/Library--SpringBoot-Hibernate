package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {

    public Publisher addPublisher(Publisher publisher);
    public Boolean deletePublisher(int publisherId);
    public Publisher updatePublisher(Publisher publisher);
    public List<Publisher> getPublishers();
}

