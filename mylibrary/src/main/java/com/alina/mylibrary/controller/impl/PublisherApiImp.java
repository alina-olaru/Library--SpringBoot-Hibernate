package com.alina.mylibrary.controller.impl;

import com.alina.mylibrary.controller.PublisherApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Publisher;
import com.alina.mylibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublisherApiImp implements PublisherApi {



    @Autowired
    private PublisherService publisherService;


    @Override
    public ApiResponse<Publisher> inserPublisher(Publisher publisher) {
       if(publisher!=null){
           Publisher response=this.publisherService.addPublisher(publisher);
           return new ApiResponse<Publisher>(ApiResponseType.SUCCESS,response);
       }

        return new ApiResponse<Publisher>(ApiResponseType.ERROR,null,"Editura nu poate fi adaugata");
    }

    @Override
    public ApiResponse<Publisher> updatePublisher(Publisher publisher) {
        if(publisher!=null){
            Publisher response=this.publisherService.updatePublisher(publisher);
            return new ApiResponse<Publisher>(ApiResponseType.SUCCESS,response);
        }

        return new ApiResponse<Publisher>(ApiResponseType.ERROR,null,"Editura nu poate fi editata");
    }


    @Override
    public ApiResponse<Boolean> deletePublisher(int id) {
        if(id>0){
           Boolean response=this.publisherService.deletePublisher(id);
            return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,response);
        }

        return new ApiResponse<Boolean>(ApiResponseType.SUCCESS,null,"Editura nu poate fi stearsa");
    }

    @Override
    public ApiResponse<List<Publisher>> getPublishers() {

            List<Publisher> response =  this.publisherService.getPublishers();
        System.out.println(response);
            return new ApiResponse<List<Publisher>>(ApiResponseType.SUCCESS,response);

    }

}



