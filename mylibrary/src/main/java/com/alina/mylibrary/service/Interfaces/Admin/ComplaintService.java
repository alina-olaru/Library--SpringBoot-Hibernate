package com.alina.mylibrary.service.Interfaces.Admin;

import com.alina.mylibrary.model.Complaint;

import java.util.List;

public interface ComplaintService {

    public Complaint addComplaint(Complaint complaint);
    public List<Complaint> getComplaints();
}
