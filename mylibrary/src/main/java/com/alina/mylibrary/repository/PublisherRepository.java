package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
