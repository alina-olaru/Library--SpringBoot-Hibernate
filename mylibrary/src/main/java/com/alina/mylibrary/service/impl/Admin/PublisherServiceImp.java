package com.alina.mylibrary.service.impl.Admin;


import com.alina.mylibrary.config.DBCheck;
import com.alina.mylibrary.dao.Interfaces.Admin.PublisherDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Publisher;
import com.alina.mylibrary.service.Interfaces.Admin.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImp implements PublisherService {

    @Autowired
    PublisherDao publisherDao;


    @Override
    public Publisher addPublisher(Publisher publisher)  throws DBExceptions {

        if(publisher==null){
            throw new DBExceptions("Obiectul trimis este gol", 404, this.getClass().getName(), "Publisher obj", "Insert");

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
            publisher.setPublisherTitle(DBCheck.Stringtify(publisher.getPublisherTitle()));
            Publisher response=null;
            try {


               response= this.publisherDao.addPublisher(publisher);
            }catch (DaoException e){
                throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "publisher obj", "Insert");
            }
            return response;
        }

    }

    @Override
    public Boolean deletePublisher(int publisherId) throws DBExceptions {
      //  this.publisherRepository.deleteP
        try{
       return this.publisherDao.deletePublusher(publisherId);
        }catch (DaoException e){
            throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "publisher obj", "delete");

        }
    }

    @Override
    public Publisher updatePublisher(Publisher publisher)  throws DBExceptions{
        Publisher response=null;
        if(publisher!=null) {
            List<Publisher> publishersWithSameName=this.publisherDao.getPublisherBypublisherTitle(publisher.getPublisherTitle());
            //this.publisherRepository.findrBypublisherTitle(publisher.getPublisherTitle());
            if(publishersWithSameName.size()>=1){
                return null;
                //you already inserted this publisher.
                //but you can add books to it
            }

            else {
                try{
                response=this.publisherDao.updatePublisher(publisher);
                if(response!=null){
                    return response;
                }
                }catch (DaoException e){
                    throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "quizzr obj", "update");

                }

                return response;
            }
        }
        return null;
    }

    @Override
    public List<Publisher> getPublishers() {
        return this.publisherDao.getPublishers();
    }
}
