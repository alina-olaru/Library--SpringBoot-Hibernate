package com.alina.mylibrary.dao.impl.Admin;

import com.alina.mylibrary.dao.Interfaces.Admin.BooksCategoriesDao;
import com.alina.mylibrary.model.db.Book;
import com.alina.mylibrary.model.db.BooksCategories;
import com.alina.mylibrary.model.db.Category;
import com.alina.mylibrary.repository.Admin.BooksCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksCategoriesDaoImpl implements BooksCategoriesDao {

    @Autowired
    private BooksCategoriesRepository booksCategoriesRepository;

    @Override
    public List<BooksCategories> getBooksCategories() {
        return this.booksCategoriesRepository.findAll();
    }

    @Override
    public List<BooksCategories> getBooksCategoriesByCategory(Category category) {
        return this.booksCategoriesRepository.findByCategories(category);
    }

    @Override
    public List<BooksCategories> getBooksCategoriesByBook(Book book) {
        return this.booksCategoriesRepository.findByBooksC(book);
    }

    @Override
    public BooksCategories addBooksCategories(BooksCategories booksCategories) {
        if(booksCategories!=null){
            this.booksCategoriesRepository.save(booksCategories);
            return booksCategories;
        }
        return null;
    }

    @Override
    public boolean deleteBooksCategories(BooksCategories booksCategories) {
        if(booksCategories!=null){
            this.booksCategoriesRepository.delete(booksCategories);
            return true;
        }
        return false;
    }
}
