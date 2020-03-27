package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.Publisher;
import com.alina.mylibrary.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
