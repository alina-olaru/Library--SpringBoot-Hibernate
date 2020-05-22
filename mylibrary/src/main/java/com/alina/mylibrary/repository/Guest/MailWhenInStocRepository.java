package com.alina.mylibrary.repository.Guest;


import com.alina.mylibrary.model.db.Complaint;
import com.alina.mylibrary.model.db.MailWhenInStoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface MailWhenInStocRepository extends JpaRepository<MailWhenInStoc, Integer> {


}
