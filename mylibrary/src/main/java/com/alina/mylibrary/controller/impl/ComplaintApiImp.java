package com.alina.mylibrary.controller.impl;

import com.alina.mylibrary.controller.ComplaintApi;
import com.alina.mylibrary.model.ApiResponse;
import com.alina.mylibrary.model.ApiResponseType;
import com.alina.mylibrary.model.Complaint;
import com.alina.mylibrary.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ComplaintApiImp implements ComplaintApi {

    @Autowired
    ComplaintService complaintService;


    @Override
    public ApiResponse<List<Complaint>> getComplaints() {
     List<Complaint> response=this.complaintService.getComplaints();
     return new ApiResponse<List<Complaint>>(ApiResponseType.SUCCESS,response);
    }

    @Override
    public ApiResponse<Complaint> insertComplaint(Complaint complaint) {
        if(complaint==null){
            return new ApiResponse<Complaint>(ApiResponseType.ERROR,null,"Nu s-a putut adauga plangerea,ne pare rau.");
        }

        Complaint reponse=this.complaintService.addComplaint(complaint);
        return new ApiResponse<Complaint>(ApiResponseType.ERROR,reponse,"Cererea s-a trimis.");
    }
}
