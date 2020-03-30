package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

 List<Publisher> findrBypublisherTitle(String publisherTitle);
    Publisher findrBypublisherId(int publisherId);

}
