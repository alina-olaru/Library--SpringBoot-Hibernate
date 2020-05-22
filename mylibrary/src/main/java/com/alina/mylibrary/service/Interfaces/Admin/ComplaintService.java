package com.alina.mylibrary.service.Interfaces.Admin;

import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Complaint;

import java.util.List;

public interface ComplaintService {

    public Complaint addComplaint(Complaint complaint)  throws DBExceptions,Exception;
    public List<Complaint> getComplaints();
    public List<Complaint> getComplaintsbyId(Integer userId);

}
