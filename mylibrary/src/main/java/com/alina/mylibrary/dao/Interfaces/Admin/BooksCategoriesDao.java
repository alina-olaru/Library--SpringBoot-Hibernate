package com.alina.mylibrary.dao.Interfaces.Admin;

import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.Category;
import com.alina.mylibrary.model.db.BooksCategories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksCategoriesDao {
    public List<BooksCategories> getBooksCategories();
    public List<BooksCategories> getBooksCategoriesByCategory(Category category);
    public List<BooksCategories> getBooksCategoriesByBook(Book book);
    public BooksCategories addBooksCategories(BooksCategories booksCategories);
    public boolean deleteBooksCategories(BooksCategories booksCategories);
}
