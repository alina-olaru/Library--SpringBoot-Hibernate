package com.alina.mylibrary.repository.Guest;

import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.db.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
    List<Complaint> getComplaintByComplaintId(Integer complaintId);
    List<Complaint> getComplaintByCom(BookUser com);
}



