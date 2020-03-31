package com.alina.mylibrary.service.impl;

import com.alina.mylibrary.dao.ComplaintDao;
import com.alina.mylibrary.model.Complaint;
import com.alina.mylibrary.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComplaintServiceImp implements ComplaintService {

    @Autowired
    ComplaintDao complaintDao;



    @Override
    public Complaint addComplaint(Complaint complaint) {
        if(complaint==null) {
            return null;
        }
        return this.complaintDao.addComplaint(complaint);
    }

    @Override
    public List<Complaint> getComplaints() {
        return this.complaintDao.getComplaints();
    }
}
