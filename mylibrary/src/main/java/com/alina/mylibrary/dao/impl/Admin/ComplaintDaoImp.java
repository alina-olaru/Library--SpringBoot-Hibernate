package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.ComplaintDao;
import com.alina.mylibrary.exception.DaoException;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Complaint;
import com.alina.mylibrary.repository.Admin.BookUserRepository;
import com.alina.mylibrary.repository.Guest.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ComplaintDaoImp implements ComplaintDao {

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    BookUserRepository bookUserRepository;



    @Override
    public List<Complaint> getComplaints() {
      return this.complaintRepository.findAll();
    }

    @Override
    public List<Complaint> getComplaintsbyId(Integer userId) {
        BookUser user = null;
        List<BookUser> all = this.bookUserRepository.findAll();
        for(BookUser b : all){
            if(b.getUserId() == userId){
                user = b;
            }
        }
        if(user==null){
            return null;
        }
        return this.complaintRepository.getComplaintByCom(user);
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
