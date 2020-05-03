package com.alina.mylibrary.service.Interfaces.Admin;


import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PublisherService {

    public Publisher addPublisher(Publisher publisher)  throws DBExceptions;
    public Boolean deletePublisher(int publisherId)  throws DBExceptions;
    public Publisher updatePublisher(Publisher publisher)  throws DBExceptions;
    public List<Publisher> getPublishers() ;
}

