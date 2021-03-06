package com.alina.mylibrary.repository.Admin;

import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksCategories;
import com.alina.mylibrary.model.db.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BooksCategoriesRepository  extends JpaRepository<BooksCategories, Integer> {
    public List<BooksCategories> findByCategories(Category category);
    public List<BooksCategories> findByBooksC(Book book);
}
