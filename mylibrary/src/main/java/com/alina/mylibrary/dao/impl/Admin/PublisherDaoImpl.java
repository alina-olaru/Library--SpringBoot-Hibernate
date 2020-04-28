package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.PublisherDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.Publisher;
import com.alina.mylibrary.repository.Admin.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Publisher addPublisher(Publisher publisher) throws DaoException {
       Publisher response=null;
        if(publisher!=null){
            try{
            response= this.publisherRepository.save(publisher);
            if(response.equals(null)){
                throw new DaoException(1);
            }
             return response;
            }catch (Exception e){
                throw new DaoException(1);
            }
        }
        return null;
    }

    @Override
    public Publisher updatePublisher(Publisher publisher)  throws DaoException  {
        Publisher response=null;
        if(publisher!=null){
            try {
                response=this.publisherRepository.save(publisher);
                if(response.equals(null)){
                    throw new DaoException(2);
                }
                return response;
            }catch (Exception e){
                throw new DaoException(2);
            }

        }
        return null;
    }

    @Override
    public Boolean deletePublusher(int publisherId)   throws DaoException  {

        try
        {
        this.publisherRepository.deleteById(publisherId);
        }catch (Exception e){
            throw  new DaoException(4);
        }
        return true;
    }
}
