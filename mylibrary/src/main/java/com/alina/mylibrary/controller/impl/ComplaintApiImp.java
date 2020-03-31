package com.alina.mylibrary.controller.impl;

import com.alina.mylibrary.controller.ComplaintApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Complaint;
import com.alina.mylibrary.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ComplaintApiImp implements ComplaintApi {

    @Autowired
    ComplaintService complaintService;


    @Override
    public ApiResponse<List<Complaint>> getComplaints() {
     List<Complaint> response=this.complaintService.getComplaints();
     return new ApiResponse<List<Complaint>>(ApiResponseType.SUCCESS,response);
    }


}
