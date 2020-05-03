package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.ComplaintDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Complaint;
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
    public Complaint addComplaint(Complaint complaint)  throws DaoException {

        if(complaint==null) {
          throw  new DaoException(1);
        }
        try {
            return this.complaintRepository.save(complaint);
        }catch(Exception e){
            throw  new DaoException(1);
        }
    }
}
