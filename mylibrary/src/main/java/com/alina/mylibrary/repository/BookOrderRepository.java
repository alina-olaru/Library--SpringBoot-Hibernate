package com.alina.mylibrary.repository;


import com.alina.mylibrary.model.BookOrder;
import com.alina.mylibrary.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public interface BookOrderRepository extends JpaRepository<BookOrder, Integer> {
}
