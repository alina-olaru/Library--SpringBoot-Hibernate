package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.ComplaintDao;
import com.alina.mylibrary.model.Complaint;
import com.alina.mylibrary.repository.Guest.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ComplaintDaoImp implements ComplaintDao {

    @Autowired
    ComplaintRepository complaintRepository;



    @Override
    public List<Complaint> getComplaints() {
      return this.complaintRepository.findAll();
    }

    @Override
    public Complaint addComplaint(Complaint complaint) {

        if(complaint==null) {
            return null;
        }
        return this.complaintRepository.save(complaint);
    }
}
