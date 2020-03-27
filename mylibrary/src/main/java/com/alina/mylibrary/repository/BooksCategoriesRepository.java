package com.alina.mylibrary.repository;

import com.alina.mylibrary.model.BooksCategories;
import com.alina.mylibrary.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BooksCategoriesRepository  extends JpaRepository<BooksCategories, Integer> {
}
