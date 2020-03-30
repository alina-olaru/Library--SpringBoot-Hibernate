package com.alina.mylibrary.dao.impl;

import com.alina.mylibrary.model.Publisher;
import com.alina.mylibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.util.List;

public class PublisherDao implements com.alina.mylibrary.dao.PublisherDao {


    @Autowired
    PublisherRepository publisherRepository;



    @Override
    public Publisher getPublisherBypublisherId(int publisherId) {
      Publisher result=new Publisher();
        result=this.publisherRepository.findrBypublisherId(publisherId);
        return result;
    }

    @Override
    public List<Publisher> getPublishers() {
        return this.publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisherBypublisherTitle(String publisherTitle) {
       Publisher result=new Publisher();
       result=this.publisherRepository.findrBypublisherTitle(publisherTitle);
       return result;
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
