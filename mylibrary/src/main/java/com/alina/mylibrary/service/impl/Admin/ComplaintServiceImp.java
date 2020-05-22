package com.alina.mylibrary.service.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.ComplaintDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.exception.ServiceExceptions.DBExceptions;
import com.alina.mylibrary.model.db.Complaint;
import com.alina.mylibrary.service.Interfaces.Admin.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComplaintServiceImp implements ComplaintService {

    @Autowired
    ComplaintDao complaintDao;



    @Override
    public Complaint addComplaint(Complaint complaint)  throws DBExceptions,Exception {
        if(complaint==null) {
            throw new DBExceptions("Obiectul trimis este gol", 404, this.getClass().getName(), "Complaint obj", "Insert");
        }

        Complaint rez=null;
        try {
           rez= this.complaintDao.addComplaint(complaint);
           if(rez!=null)
           {
               return rez;
           }
        }catch (DaoException e){
            throw new DBExceptions(e.getMessage(), 400, this.getClass().getName(), "complaint obj", "Insert");
        }
        catch(Exception e){
            throw  new Exception(e.getMessage());
        }
        return rez;
    }

    @Override
    public List<Complaint> getComplaints() {
        return this.complaintDao.getComplaints();
    }

    @Override
    public List<Complaint> getComplaintsbyId(Integer userId) {
        return this.complaintDao.getComplaintsbyId(userId);
    }
}
