package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.db.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

 List<Publisher> findBypublisherTitle(String publisherTitle);
    Publisher findBypublisherId(int publisherId);

}
