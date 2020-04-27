package com.alina.mylibrary.repository.Guest;

import com.alina.mylibrary.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
}



