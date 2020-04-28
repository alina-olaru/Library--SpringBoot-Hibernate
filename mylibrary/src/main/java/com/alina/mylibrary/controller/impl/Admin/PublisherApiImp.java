package com.alina.mylibrary.controller.impl.Admin;

import com.alina.mylibrary.controller.Interfaces.Admin.PublisherApi;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Publisher;
import com.alina.mylibrary.service.Interfaces.Admin.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublisherApiImp implements PublisherApi {



    @Autowired
    private PublisherService publisherService;


    @Override
    public ApiResponse<Publisher> inserPublisher(Publisher publisher) {
        Publisher response=null;
       if(publisher!=null){
           try{
           response=this.publisherService.addPublisher(publisher);
           }catch (DBExceptions e){
               return new ApiResponse<Publisher>(ApiResponseType.ERROR,null,e.message);
           }
           if(response!=null) {
               return new ApiResponse<Publisher>(ApiResponseType.SUCCESS, response);
           }
       }

        return new ApiResponse<Publisher>(ApiResponseType.ERROR,null,"Editura nu poate fi adaugata");
    }

    @Override
    public ApiResponse<Publisher> updatePublisher(Publisher publisher) {
        Publisher response=null;
        if(publisher!=null){
            try {
               response = this.publisherService.updatePublisher(publisher);
            }catch (DBExceptions e){
                return new ApiResponse<Publisher>(ApiResponseType.ERROR,null,e.message);

            }
            if(response!=null) {
                return new ApiResponse<Publisher>(ApiResponseType.SUCCESS, response);
            }
            else{
                return new ApiResponse<Publisher>(ApiResponseType.ERROR,null,"Editura exista deja");

            }
        }

        return new ApiResponse<Publisher>(ApiResponseType.ERROR,null,"Editura nu poate fi editata");
    }


    @Override
    public ApiResponse<Boolean> deletePublisher(int id) {
        Boolean response=false;
        try {
      response = this.publisherService.deletePublisher(id);
    }catch (DBExceptions e){
        return new ApiResponse<Boolean>(ApiResponseType.ERROR,null,e.message);

    }
            return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);




    }

    @Override
    public ApiResponse<List<Publisher>> getPublishers() {

            List<Publisher> response =  this.publisherService.getPublishers();
        System.out.println(response);
            return new ApiResponse<List<Publisher>>(ApiResponseType.SUCCESS,response);

    }

}



