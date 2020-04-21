package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.model.Publisher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherDao {

    public Publisher getPublisherBypublisherId(int publisherId);

    public List<Publisher> getPublishers();

    public   List< Publisher> getPublisherBypublisherTitle(String publisherTitle);

    public Publisher addPublisher(Publisher publisher);

    public Publisher updatePublisher(Publisher publisher);

    public Boolean deletePublusher(int publisherId);

}
