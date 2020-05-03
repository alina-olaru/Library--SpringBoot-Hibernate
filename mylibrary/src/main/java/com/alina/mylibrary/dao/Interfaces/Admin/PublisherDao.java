package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Publisher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherDao {

    public Publisher getPublisherBypublisherId(int publisherId);

    public List<Publisher> getPublishers();

    public   List< Publisher> getPublisherBypublisherTitle(String publisherTitle);

    public Publisher addPublisher(Publisher publisher)  throws DaoException;

    public Publisher updatePublisher(Publisher publisher)  throws DaoException;

    public Boolean deletePublusher(int publisherId)  throws DaoException;

}
