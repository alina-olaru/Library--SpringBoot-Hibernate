package com.alina.mylibrary.dao.Interfaces.Admin;


import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.Complaint;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintDao {
    public List<Complaint> getComplaints();
    Complaint addComplaint(Complaint complaint)  throws DaoException;
}
