package com.alina.mylibrary.controller.impl;

import com.alina.mylibrary.controller.PublisherApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublisherApiImp implements PublisherApi {
    @Override
    public ApiResponse<Publisher> inserPublisher(Publisher publisher) {
        return null;
    }

    @Override
    public ApiResponse<Publisher> updatePublisher(Publisher publisher) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> deletePublisher(int id) {
        return null;
    }

    @Override
    public ApiResponse<List<Publisher>> getPublishers() {
        return null;
    }
}
