package com.alina.mylibrary.service.impl;


import com.alina.mylibrary.dao.PublisherDao;
import com.alina.mylibrary.model.Publisher;
import com.alina.mylibrary.repository.PublisherRepository;
import com.alina.mylibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherServiceImp implements PublisherService {

    @Autowired
    PublisherDao publisherDao;


    @Override
    public Publisher addPublisher(Publisher publisher) {

        if(publisher==null){
            return null;
        }
        List<Publisher> publishersWithSameName=this.publisherDao.getPublisherBypublisherTitle(publisher.getPublisherTitle());
                //this.publisherRepository.findrBypublisherTitle(publisher.getPublisherTitle());
        if(publishersWithSameName.size()>=1){
            return null;
            //you already inserted this publisher.
            //but you can add books to it
        }
        else{
            //you add that now
          this.publisherDao.addPublisher(publisher);
            return publisher;
        }

    }

    @Override
    public Boolean deletePublisher(int publisherId) {
      //  this.publisherRepository.deleteP
       return this.publisherDao.deletePublusher(publisherId);
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        if(publisher!=null) {
            this.publisherDao.updatePublisher(publisher);
            return publisher;
        }
        return null;
    }

    @Override
    public List<Publisher> getPublishers() {
        return this.publisherDao.getPublishers();
    }
}
