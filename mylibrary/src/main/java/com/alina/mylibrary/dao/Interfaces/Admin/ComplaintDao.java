package com.alina.mylibrary.dao.Interfaces.Admin;


import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Complaint;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintDao {
    public List<Complaint> getComplaints();
    public List<Complaint> getComplaintsbyId(Integer userId);
    public Complaint addComplaint(Complaint complaint)  throws DaoException;
}
