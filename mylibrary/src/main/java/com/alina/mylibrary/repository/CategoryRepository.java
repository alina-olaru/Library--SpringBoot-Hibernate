package com.alina.mylibrary.repository;


import com.alina.mylibrary.model.Author;
import com.alina.mylibrary.model.Category;
import com.alina.mylibrary.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<com.alina.mylibrary.model.Category> findBycategoryTitle (String categoryTitle);

}
