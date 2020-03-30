package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.dao.PublisherDao;
import com.alina.mylibrary.model.Publisher;
import com.alina.mylibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublisherDaoImpl implements PublisherDao {


    @Autowired
    PublisherRepository publisherRepository;



    @Override
    public Publisher getPublisherBypublisherId(int publisherId) {
      Publisher result=new Publisher();
        result=this.publisherRepository.findBypublisherId(publisherId);
        return result;
    }

    @Override
    public List<Publisher> getPublishers() {
        return this.publisherRepository.findAll();
    }

    @Override
    public   List< Publisher> getPublisherBypublisherTitle(String publisherTitle) {
      List< Publisher> results=this.publisherRepository.findBypublisherTitle(publisherTitle);
       return results;
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        if(publisher!=null){
             this.publisherRepository.save(publisher);
             return publisher;
        }
        return null;
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        if(publisher!=null){
            this.publisherRepository.save(publisher);
            return publisher;
        }
        return null;
    }

    @Override
    public Boolean deletePublusher(int publisherId) {
        if(publisherId==0){
            return false;
        }
        this.publisherRepository.deleteById(publisherId);
        return true;
    }
}
